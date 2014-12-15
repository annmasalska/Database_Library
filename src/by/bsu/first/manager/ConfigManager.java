package by.bsu.first.manager;

import by.bsu.first.command.Command;

import java.util.ResourceBundle;


public class ConfigManager {
    private static ResourceBundle bundle= ResourceBundle.getBundle("resources.config");
    private ConfigManager() {}

    public static String getProperty(String key) {


        return bundle.getString(key);
    }
}
