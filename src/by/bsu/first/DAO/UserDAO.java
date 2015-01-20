package by.bsu.first.DAO;

import by.bsu.first.entity.User;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    static Logger logger = Logger.getLogger(UserDAO.class);

    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";

    public static final String SQL_INSERT_NEW_USER = "INSERT INTO users ( username,e-mail,phone, password) VALUES( ?, ?, ?, ?)";
    public static final String SQL_SELECT_ID = "SELECT id FROM users WHERE username = ? ";

    //@Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<User>();
        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();

        try {
            cn = pool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("e-mail"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error("SQL exception (request or table failed): " + e);

            }

            pool.returnConnection(cn);

        }
        return users;
    }

    public int findIdByUsername(String currentRole) throws DAOException {


        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement ps = null;
        int id = 0;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_ID);
            ps.setString(1, currentRole);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());

        } finally {
            close(ps);
            pool.returnConnection(cn);
        }
        return id;
    }

    public void addUser(String username, String email, String phone, String passValue) throws DAOException {

        Connection cn = null;

        ConnectionPool pool = ConnectionPool.getPool();

        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_USER);

            ps.setString(1,username);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, passValue);
            ps.executeUpdate();

        } catch (SQLException e) {

            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());
        } finally {
            close(ps);
            pool.returnConnection(cn);

        }

    }
}