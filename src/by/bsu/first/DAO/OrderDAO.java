package by.bsu.first.DAO;
import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDAO extends AbstractDAO<Book> {

    static Logger logger = Logger.getLogger(OrderDAO.class);

    public static final String SQL_INSERT_ORDER = "INSERT INTO orders (idorder, idbook, idcard, status) VALUES(?, ?, ? , ? )";
    public static final String SQL_UPDATE_BOOKS = "UPDATE books SET amount = amount - 1 WHERE id = ?";


    public void addOrder(String idbook, int idcard) throws DAOCommandException {


        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;
        Random rand = new Random();
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_ORDER);
            ps.setInt(1, rand.nextInt(200));

            ps.setInt(2, Integer.parseInt(idbook));
            ps.setInt(3, idcard);
            int status=2;
            ps.setInt(4, status);

            ps.executeUpdate();
            ps = null;
            ps = cn.prepareStatement(SQL_UPDATE_BOOKS);
            ps.setInt(1, Integer.parseInt(idbook));
            ps.executeUpdate();

            cn.commit();
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());

        } finally {
            close(ps);
            pool.returnConnection(cn);

        }

    }}