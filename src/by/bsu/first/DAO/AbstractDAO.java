package by.bsu.first.DAO;

import by.bsu.first.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO<T extends Entity> {

    static Logger logger = Logger.getLogger(AbstractDAO.class);


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