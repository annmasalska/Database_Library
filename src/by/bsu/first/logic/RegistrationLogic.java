package by.bsu.first.logic;
import by.bsu.first.DAO.UserDAO;

import by.bsu.first.entity.User;
import by.bsu.first.exceptions.DAOException;

import java.util.List;

public class RegistrationLogic {
    public static boolean addUser(String username) {

        boolean result=false;
        UserDAO dao=new UserDAO();
        List<User> lst= null;
        try {
            lst = dao.findAll();
        } catch (DAOException daoException) {
            daoException.printStackTrace();
        }

        for(int i=0; i<lst.size() && result==false; i++) {

            User row=lst.get(i);
            if(row.getUsername().equals(username)){
                result=true;
            }
        }
       return result;
    }


}
