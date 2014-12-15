package by.bsu.first.command;

import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
        return ConfigManager.getProperty("path.page.index");
    }
}
