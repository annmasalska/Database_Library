package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.DAO.OrderDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.entity.Order;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReturnBookCommand implements Command  {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        String currentRole = (String) session.getAttribute("role");

        String select[] = request.getParameterValues("idissue");

        if (select != null && select.length != 0) {
            for (int i = 0; i < select.length; i++) {

                OrderDAO dao = new OrderDAO();
                try {
                    dao.returnBook(select[i]);
                } catch (DAOException e) {
                    throw new CommandException(e.getCause());
                }

            }
        }
        OrderDAO dao = new OrderDAO();
        List<Order> lst = null;
        try {
            lst = dao.findAllIssues();
        } catch (DAOException e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);

        String page = ConfigManager.getProperty("path.page.returnbook");
        return page;
    }
}
