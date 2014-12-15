package by.bsu.first.command;

import by.bsu.first.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return ConfigManager.getProperty("path.page.index");
    }
}
