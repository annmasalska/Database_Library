package by.bsu.first.command;

import by.bsu.first.DAO.BookDAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommand;
import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class DeleteBookCommand implements Command  {

    @Override
    public String execute(HttpServletRequest request) throws CommandException{

        String select[] = request.getParameterValues("id");

        if (select != null && select.length != 0) {
            //out.println("You have selected: ");
            for (int i = 0; i < select.length; i++) {

                BookDAO dao = new BookDAO();
                try {
                    dao.deleteBook(select[i]);
                } catch (DAOCommand e) {
                    throw new CommandException(e.getCause());
                }
                //  out.println(select[i]);
            }
        }
        BookDAO dao = new BookDAO();
        List<Book> lst = null;
        try {
            lst = dao.findAll();
        } catch (DAOCommand e) {
            throw new CommandException(e.getCause());
        }
        request.setAttribute("lst", lst);
        String page = ConfigManager.getProperty("path.page.deletebook");
        return page;
    }
}
