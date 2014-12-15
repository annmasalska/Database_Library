package by.bsu.first.logic;

import by.bsu.first.DAO.ReaderDAO;
import by.bsu.first.entity.Reader;
import by.bsu.first.exceptions.LogicException;
import javafx.fxml.LoadException;

import java.util.List;

public class AddReaderLogic {

    public static boolean addReader(String Idcard) throws LogicException {

        int idcard=Integer.parseInt(Idcard );
        boolean result=false;
        ReaderDAO dao=new ReaderDAO();
        List<Reader> lst=dao.findAll();

        for(int i=0; i<lst.size() && result==false; i++) {

            Reader row=lst.get(i);
            if(row.getIdcard()==idcard){
                result=true;
            }
        }
        return result;
    }
}
