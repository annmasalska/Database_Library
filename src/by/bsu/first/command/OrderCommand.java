package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.DAO.OrderDAO;
import by.bsu.first.DAO.UserDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderCommand implements Command {
    private static final String PARAM_LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String select[] = request.getParameterValues("id");
        HttpSession session = request.getSession(true);
        String currentRole = (String) session.getAttribute("role");
        String locale = (String) session.getAttribute(PARAM_LOCALE);


        if (select != null && select.length != 0) {
            for (int i = 0; i < select.length; i++) {

                OrderDAO dao = new  OrderDAO();
                try {
                    dao.addOrder(currentRole,Integer.parseInt(select[i]));
                } catch (DAOException e) {
                    throw new CommandException(e.getCause());
                }

            }
        }
        BookDAO dao = new BookDAO();
        List<Book> lst = null;
        try {
            lst = dao.findAll();
        } catch (DAOException e) {
            throw new CommandException(e.getCause());
        }
        if (select != null && select.length != 0)
        {
            request.setAttribute("successMessage", MessageManager.getMessage("message.order_success", locale));
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.catalogue");
        return page;
    }
}
