package by.bsu.first.logic;
import by.bsu.first.DAO.UserDAO;
import by.bsu.first.entity.User;
import by.bsu.first.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;


public class LoginLogic {
    public static boolean checkUser(String login, String password) throws SQLException, DAOException {

        boolean result=false;
        UserDAO dao=new UserDAO();
        List<User> lst=dao.findAll();

        for(int i=0; i<lst.size() && result==false; i++) {

            User row=lst.get(i);
            if(row.getUsername().equals(login) && row.getPassword().equals(password)){
                result=true;
            }
        }

        return result;
    }
}
