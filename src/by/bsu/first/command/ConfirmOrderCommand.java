package by.bsu.first.command;

import by.bsu.first.DAO.OrderDAO;
import by.bsu.first.entity.Order;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConfirmOrderCommand implements Command {

    private static final String PARAM_CONFIRM = "id";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String select[] = request.getParameterValues(PARAM_CONFIRM);

        if (select != null && select.length != 0) {
            for (int i = 0; i < select.length; i++) {
                OrderDAO dao = new OrderDAO();
                try {
                    dao.confirmOrder(Integer.parseInt(select[i]));
                } catch (DAOException e) {
                    throw new CommandException(e.getCause());
                }
            }
        }
        OrderDAO dao = new OrderDAO();
        List<Order> lst = null;
        try {
            lst = dao.findAll();
        } catch (DAOException e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.confirmorder");
        return page;
    }
}