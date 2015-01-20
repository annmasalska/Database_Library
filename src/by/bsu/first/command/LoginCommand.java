package by.bsu.first.command;

import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.hashing.HashCustom;
import by.bsu.first.logic.LoginLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class LoginCommand implements Command {

    static Logger logger = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);

        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(PARAM_LOCALE);

        if (loginValue == null || loginValue.isEmpty() || passValue == null || passValue.isEmpty()) {

            request.setAttribute("errorFillMessage", MessageManager.getMessage("message.fillerror", locale));
            page = ConfigManager.getProperty("path.page.login");
        } else {
            try {
                passValue = HashCustom.getHashingCustom(passValue);
                if (LoginLogic.checkUser(loginValue, passValue)) {
                    request.setAttribute("user", loginValue);
                    session.setAttribute("role", loginValue);
                    page = ConfigManager.getProperty("path.page.home");
                } else {
                    request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.loginerror", locale));
                    page = ConfigManager.getProperty("path.page.login");
                }
            } catch (SQLException e) {
                logger.error(e);

            } catch (DAOException e) {
                throw new CommandException(e.getCause());
            }
        }
        return page;
    }
}
