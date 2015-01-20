package by.bsu.first.command;

import by.bsu.first.DAO.OrderDAO;
import by.bsu.first.DAO.UserDAO;

import by.bsu.first.entity.Order;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SelectOrdersByUsername implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {


        HttpSession session = request.getSession(true);
        String currentRole = (String) session.getAttribute("role");

        OrderDAO dao = new OrderDAO();
        List<Order> lst = null;
        try {
            lst = dao.selectOrdersByUsername(currentRole);
        } catch (DAOException e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.orders");
        return page;
    }
}