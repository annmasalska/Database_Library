package by.bsu.first.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public  class  MessageManager {

    private static ResourceBundle bundle;

    public static String getMessage(String key,String locale) {
        if(locale==null ) bundle= ResourceBundle.getBundle("resources.messages",new Locale("ru"));
        bundle= ResourceBundle.getBundle("resources.messages",new Locale(locale));
        return bundle.getString(key);

    }
}
