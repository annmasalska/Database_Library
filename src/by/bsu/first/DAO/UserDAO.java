package by.bsu.first.DAO;

import by.bsu.first.entity.User;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommand;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_INSERT_NEW_USER = "INSERT INTO users(id, username, password) VALUES(?, ?, ?)";
    static Logger logger = Logger.getLogger(UserDAO.class);

    @Override
    public List<User> findAll() throws DAOCommand {
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
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        }
        catch (ConnectionPoolException e) {
            throw new DAOCommand(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        }
        finally {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error("SQL exception (request or table failed): " + e);

            }

            pool.returnConnection(cn);

        }
        return users;
    }

    public void addUser(int IDcard, String username, String passValue) throws DAOCommand {

        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();

        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_USER);
            ps.setInt(1, IDcard);
            ps.setString(2, username);
            ps.setString(3, passValue);
            ps.executeUpdate();
            cn.commit();

        } catch (SQLException e) {

            logger.error("SQL exception (request or table failed): " + e);
        }
        catch (ConnectionPoolException e) {
            throw new DAOCommand(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        }
        finally {
            close(ps);
            pool.returnConnection(cn);

        }

    }
}