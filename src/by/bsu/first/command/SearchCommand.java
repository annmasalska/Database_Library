package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class SearchCommand implements Command {
    static Logger logger = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_AUTHOR = "author";


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String author = request.getParameter(PARAM_AUTHOR);


        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("locale");
        if (locale == null)
            locale = "ru";

        if (author == null || author.isEmpty()) {
            request.setAttribute("errorFillMessage", MessageManager.getMessage("message.fillerror", locale));
            page = ConfigManager.getProperty("path.page.addborrow");
        } else {
            BookDAO dao = new BookDAO();
            List<Book> lst = null;
            try {
                lst = dao.searchByAuthor(author);
            } catch (DAOCommandException e) {
                throw new CommandException(e.getCause());
            }
            if (lst.isEmpty()) {
                request.setAttribute("errorSearchMessage", MessageManager.getMessage("message.searcherror", locale));
                page = ConfigManager.getProperty("path.page.addborrow");
            } else {
                request.setAttribute("lst", lst);
                page = ConfigManager.getProperty("path.page.addborrow");
            }


        }

        return page;
    }
}
