package by.bsu.first.command;

import by.bsu.first.DAO.BorrowInfoDAO;
import by.bsu.first.DAO.UserDAO;
import by.bsu.first.entity.BorrowInfo;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SelectBorrowInfoByUsername implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {


        HttpSession session = request.getSession(true);
        String currentRole = (String) session.getAttribute("role");
        UserDAO daoUser = new UserDAO();
        int idCard;
        try {

            idCard = daoUser.findIdByUsername(currentRole);
        } catch (DAOCommandException e) {
            throw new CommandException(e.getCause());
        }
        BorrowInfoDAO dao = new BorrowInfoDAO();
        List<BorrowInfo> lst = null;
        try {
            lst = dao.selectBorrowInfo(idCard);
        } catch (DAOCommandException e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.borrowinfo");
        return page;
    }
}