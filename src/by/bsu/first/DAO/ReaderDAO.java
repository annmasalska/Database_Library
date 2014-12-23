package by.bsu.first.DAO;

import by.bsu.first.entity.Reader;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReaderDAO extends AbstractDAO<Reader> {

    public static final String SQL_SELECT_ALL_READERS = "SELECT * FROM readers";
    public static final String SQL_INSERT_NEW_READER = "INSERT INTO readers(idcard, lastname,name,secondname,address,phone) VALUES(?, ?, ?, ?,?,?)";
    static Logger logger = Logger.getLogger(ReaderDAO.class);

   // @Override
    public List<Reader> findAll() {
        List<Reader> readers = new ArrayList<Reader>();
        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();
        try {
            cn = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try {

            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_READERS);

            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setIdcard(resultSet.getInt("idcard"));
                reader.setLastname(resultSet.getString("lastname"));
                reader.setName(resultSet.getString("name"));
                reader.setSecondname(resultSet.getString("secondname"));
                reader.setAddress(resultSet.getString("address"));
                reader.setPhone(resultSet.getString("phone"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error("SQL exception (request or table failed): " + e);

            }
            pool.returnConnection(cn);
            close(st);

        }
        return readers;
    }

    public void addReader(int idcard, String lastname, String name, String secondname, String address, String phone) throws DAOCommandException {

        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();

        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_READER);
            ps.setInt(1, idcard);
            ps.setString(2, lastname);
            ps.setString(3, name);
            ps.setString(4, secondname);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.executeUpdate();
            cn.commit();

        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(ps);
            pool.returnConnection(cn);

        }

    }
}
