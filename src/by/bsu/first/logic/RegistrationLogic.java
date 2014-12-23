package by.bsu.first.logic;
import by.bsu.first.DAO.ReaderDAO;
import by.bsu.first.DAO.UserDAO;
import by.bsu.first.entity.Reader;
import by.bsu.first.entity.User;
import by.bsu.first.exceptions.DAOCommandException;

import java.util.List;

public class RegistrationLogic {
    public static boolean addUser(String username) {

        boolean result=false;
        UserDAO dao=new UserDAO();
        List<User> lst= null;
        try {
            lst = dao.findAll();
        } catch (DAOCommandException daoCommandException) {
            daoCommandException.printStackTrace();
        }

        for(int i=0; i<lst.size() && result==false; i++) {

            User row=lst.get(i);
            if(row.getUsername().equals(username)){
                result=true;
            }
        }
       return result;
    }

    public static boolean checkReaderId(int IDcard) {


        boolean result=false;
        ReaderDAO dao=new ReaderDAO();
        List<Reader> lst=dao.findAll();

        for(int i=0; i<lst.size() && result==false; i++) {

            Reader row=lst.get(i);
            if(row.getIdcard()==IDcard){
                result=true;
            }
        }
        return result;
    }
}
