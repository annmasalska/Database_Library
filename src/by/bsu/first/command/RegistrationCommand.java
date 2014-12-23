package by.bsu.first.command;

import by.bsu.first.DAO.UserDAO;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.hashing.HashCustom;
import by.bsu.first.logic.RegistrationLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {

    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_IDCARD = "idcard";
    private static final String PARAM_PASSWORD = "pass";
    private static final String PARAM_PASSWORD_REPEAT = "pass_repeat";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;
        String username = request.getParameter(PARAM_USERNAME);
        String idcard = request.getParameter(PARAM_IDCARD);
        String passValue = request.getParameter(PARAM_PASSWORD);
        String passValueRepeat = request.getParameter(PARAM_PASSWORD_REPEAT);

        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("locale");
        if (locale == null)
            locale = "ru";

        if (username == null || username.isEmpty() || passValue.isEmpty() || idcard == null || idcard.isEmpty() || passValue == null || passValueRepeat.isEmpty() || passValueRepeat == null) {
            request.setAttribute("errorFillMessage", MessageManager.getMessage("message.fillerror", locale));
            page = ConfigManager.getProperty("path.page.registration");
        } else {
            Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
            Matcher m_username = p.matcher(username);
            Matcher m_passValue = p.matcher(passValue);

            if (!m_username.matches() || !m_passValue.matches()) {
                request.setAttribute("passwordError", MessageManager.getMessage("message.password_enter", locale));
                page = ConfigManager.getProperty("path.page.registration");
            } else {
                int IDcard = Integer.parseInt(idcard);
                if (passValue.equals(passValueRepeat)) {
                    passValue = HashCustom.getHashingCustom(passValue);
                    if (!RegistrationLogic.addUser(username) && RegistrationLogic.checkReaderId(IDcard)) {

                        request.setAttribute("user", username);
                        session.setAttribute("role", username);
                        request.setAttribute("successMessage", MessageManager.getMessage("message.success_registration", locale));
                        page = ConfigManager.getProperty("path.page.index");
                        UserDAO dao = new UserDAO();
                        try {
                            dao.addUser(IDcard, username, passValue);
                        } catch (DAOCommandException e) {
                            throw new CommandException(e.getCause());
                        }

                    } else {
                        request.setAttribute("errorLoginMessage", MessageManager.getMessage("message.registration_logic", locale));
                        page = ConfigManager.getProperty("path.page.registration");
                    }

                } else {
                    request.setAttribute("repeatPasswordError", MessageManager.getMessage("message.repeat_password_error", locale));
                    page = ConfigManager.getProperty("path.page.registration");
                }
            }
        }
        return page;
    }

}
