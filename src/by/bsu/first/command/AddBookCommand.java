package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommand;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AUTHOR = "author";
    private static final String PARAM_GENREID = "genreID";
    private static final String PARAM_AMOUNT = "amount";
    private static final String PARAM_INFORMATION = "information";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String id = request.getParameter(PARAM_ID);
        String name = request.getParameter(PARAM_NAME);
        String author = request.getParameter(PARAM_AUTHOR);
        String genreID = request.getParameter(PARAM_GENREID);
        String amount = request.getParameter(PARAM_AMOUNT);
        String information = request.getParameter(PARAM_INFORMATION);
        if (id == null || id.isEmpty() || name == null || name.isEmpty() || author == null || author.isEmpty() || genreID == null || genreID.isEmpty() || amount == null || amount.isEmpty() || information == null || information.isEmpty()) {
            request.setAttribute("errorMessage", "message.adderror");
            page = ConfigManager.getProperty("path.page.addbook");
        }

        BookDAO dao = new BookDAO();
        try {
            dao.addBook(id, name, author, genreID, amount, information);
        } catch (DAOCommand e) {
            throw new CommandException(e.getCause());
        }
        page = ConfigManager.getProperty("path.page.index");
        return page;
    }
}
