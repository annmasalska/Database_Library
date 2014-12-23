package by.bsu.first.command;


import by.bsu.first.DAO.BookDAO;
import by.bsu.first.DAO.BorrowInfoDAO;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

public class AddBorrowInfoCommand implements Command {

    private static final String PARAM_IDCARD = "idcard";
    private static final String PARAM_IDBOOK = "idbook";
    private static final String PARAM_DATE = "date";


    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        String idcard = request.getParameter(PARAM_IDCARD);
        String idbook = request.getParameter(PARAM_IDBOOK);
        String date = request.getParameter(PARAM_DATE);
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("locale");
        if (locale == null)
            locale = "ru";

        if (idcard == null || idcard.isEmpty() || idbook == null || idbook.isEmpty() || date == null || date.isEmpty()) {
            request.setAttribute("errorFillMessage", MessageManager.getMessage("message.fillerror", locale));
            page = ConfigManager.getProperty("path.page.addborrow");
        } else {
            try {
                BookDAO bookdao = new BookDAO();
                if (bookdao.checkExistence(idbook)) {
                    Random rand = new Random();
                    BorrowInfoDAO dao = new BorrowInfoDAO();
                    dao.addBorrowBook(rand.nextInt(200), idbook, idcard, date);
                    request.setAttribute("successMessage", MessageManager.getMessage("message.borrowerror_success", locale));
                    page = ConfigManager.getProperty("path.page.addborrow");
                } else {
                    request.setAttribute("borrowError", MessageManager.getMessage("message.borrowerror", locale));
                    page = ConfigManager.getProperty("path.page.addborrow");
                }

            } catch (DAOCommandException e) {
                throw new CommandException(e.getCause());
            }


        }


        return page;
    }
}
