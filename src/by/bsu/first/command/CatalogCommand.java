package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CatalogCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        BookDAO dao = new BookDAO();
        String page = null;
        List<Book> lst = null;
        try {
            lst = dao.findAll();
        } catch (DAOCommandException e) {
            throw new CommandException(e.getCause());
        }
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("locale");
        if (locale == null)
            locale = "ru";
        if (lst.isEmpty()) {
            request.setAttribute("errorSearchMessage", MessageManager.getMessage("message.searcherror", locale));
            page = ConfigManager.getProperty("path.page.catalogue");
        } else {
            request.setAttribute("lst", lst);
            page = ConfigManager.getProperty("path.page.catalogue");
        }


        return page;
    }
}
