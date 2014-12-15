package by.bsu.first.command;
import by.bsu.first.DAO.UserDAO;
import by.bsu.first.exceptions.CommandException;
import by.bsu.first.exceptions.DAOCommand;
import by.bsu.first.hashing.HashCustom;
import by.bsu.first.logic.RegistrationLogic;
import by.bsu.first.manager.ConfigManager;
import by.bsu.first.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_IDCARD = "idcard";
    private static final String PARAM_PASSWORD = "pass";
    private static final String PARAM_PASSWORD_REPEAT = "pass_repeat";

    @Override
    public String execute(HttpServletRequest request)  throws CommandException {

        String page = null;
        String username = request.getParameter(PARAM_USERNAME);
        String idcard = request.getParameter(PARAM_IDCARD);
        String passValue = request.getParameter(PARAM_PASSWORD);
        String passValueRepeat = request.getParameter(PARAM_PASSWORD_REPEAT);


        if (username == null || passValue.isEmpty() || idcard == null || idcard.isEmpty() || passValue == null || username.isEmpty() || passValueRepeat.isEmpty() || passValueRepeat == null) {
            request.setAttribute("errorLoginPassMessage", "message.login.empty");
            page = ConfigManager.getProperty("path.page.registration");
        }
        int IDcard = Integer.parseInt(idcard);
        if (passValue.equals(passValueRepeat)) {
            passValue = HashCustom.getHashingCustom(passValue);
            if (!RegistrationLogic.addUser(username) && RegistrationLogic.checkReaderId(IDcard)) {

                request.setAttribute("user", username);
                HttpSession session = request.getSession(true);
                session.setAttribute("role", username);
                page = ConfigManager.getProperty("path.page.index");
                UserDAO dao = new UserDAO();
                try {
                    dao.addUser(IDcard, username, passValue);
                } catch (DAOCommand e) {
                    throw new CommandException(e.getCause());
                }

            } else {
                request.setAttribute("errorLoginMessage", MessageManager.EN.getMessage("message.loginexist"));
                page = ConfigManager.getProperty("path.page.registration");
            }

        } else {
            request.setAttribute("wrongAction", MessageManager.EN.getMessage("message.loginerror"));
            page = ConfigManager.getProperty("path.page.registration");
        }
        return page;
    }
}
