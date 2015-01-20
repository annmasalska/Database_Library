package by.bsu.first.controller;
import by.bsu.first.command.ActionFactory;
import by.bsu.first.command.Command;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class Controller extends HttpServlet {

    static Logger logger = Logger.getLogger(Controller.class);

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
      }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        Command command = ActionFactory.defineCommand(request);
        HttpSession session = request.getSession(true);

        String locale=(String)session.getAttribute("locale");
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error(e);
            request.setAttribute("errorCause", e.getCause());
            // добавить переход на страницу эрроров
            response.sendRedirect(request.getContextPath() + ConfigManager.getProperty("path.page.error"));
        }
        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);


            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("nullPage", MessageManager.getMessage("message.nullpage",locale));
            response.sendRedirect(request.getContextPath() + ConfigManager.getProperty("path.page.home"));
        }
    }

    @Override
    public void destroy() {
        ConnectionPool pool = ConnectionPool.getPool();
        try {
            pool.closePool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }

    }
}