package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectBooksByGenreCommand implements Command {
    private static final String PARAM_GENREID = "genreID";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {


        String genreID = request.getParameter(PARAM_GENREID);
        if (genreID == null) {
            BookDAO dao = new BookDAO();
            List<Book> lst = null;
            try {
                lst = dao.findAll();
            } catch (DAOException e) {
                throw new CommandException(e.getCause());
            }
            request.setAttribute("lst", lst);
            return ConfigManager.getProperty("path.page.catalogue");
        }

        BookDAO dao = new BookDAO();
        List<Book> lst = null;
        try {
            lst = dao.selectByGenre(genreID);
        } catch (DAOException e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.catalogue");
        return page;
    }
}
