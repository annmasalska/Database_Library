package by.bsu.first.DAO;

import by.bsu.first.entity.Entity;
import by.bsu.first.exceptions.DAOCommand;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {

   static Logger logger = Logger.getLogger(AbstractDAO.class);

    public abstract List<T> findAll() throws DAOCommand;
    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.error("cannot close statement" + e);
        }
    }

}