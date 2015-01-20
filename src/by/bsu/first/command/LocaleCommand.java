package by.bsu.first.command;

import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {

    private static final String PARAM_LOCALE = "id";

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        String localeValue = request.getParameter(PARAM_LOCALE);
        HttpSession session = request.getSession(true);
        session.setAttribute("locale", localeValue);
        page = ConfigManager.getProperty("path.page.home");
        return page;
    }
}
