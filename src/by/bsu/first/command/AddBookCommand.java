package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddBookCommand implements Command {

    private static final String PARAM_NAME = "name";
    private static final String PARAM_AUTHOR = "author";
    private static final String PARAM_GENREID = "genreID";
    private static final String PARAM_AMOUNT = "amount";
    private static final String PARAM_INFORMATION = "information";
    private static final String PARAM_LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String name = request.getParameter(PARAM_NAME);
        String author = request.getParameter(PARAM_AUTHOR);
        String genreID = request.getParameter(PARAM_GENREID);
        String amount = request.getParameter(PARAM_AMOUNT);
        String information = request.getParameter(PARAM_INFORMATION);
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(PARAM_LOCALE);

        if ( name == null || name.isEmpty() || author == null || author.isEmpty() || genreID == null || genreID.isEmpty() || amount == null || amount.isEmpty() || information == null || information.isEmpty()) {
            request.setAttribute("errorFillMessage", MessageManager.getMessage("message.fillerror", locale));
            page = ConfigManager.getProperty("path.page.addbook");
        } else {
            BookDAO dao = new BookDAO();
            try {
                dao.addBook(name, author, genreID, amount, information);
            } catch (DAOException e) {
                throw new CommandException(e.getCause());
            }
            request.setAttribute("successMessage", MessageManager.getMessage("message.success_addbook", locale));
            page = ConfigManager.getProperty("path.page.addbook");
        }
        return page;
    }
}
