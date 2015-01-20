package by.bsu.first.DAO;

import by.bsu.first.entity.Book;
import by.bsu.first.entity.Order;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends AbstractDAO<Book> {

    static Logger logger = Logger.getLogger(OrderDAO.class);

    public static final String SQL_INSERT_ORDER = "INSERT INTO orders (username, idbook,status) VALUES(?, ?,?)";
    public static final String SQL_UPDATE_BOOKS = "UPDATE books SET amount = amount - 1 WHERE id = ?";
    public static final String SQL_SELECT_ORDERS_BY_USERNAME = " SELECT orders.idorder, books.name,books.author,books.information,orders.date FROM orders INNER JOIN books ON books.id=orders.idbook INNER JOIN users ON users.username=orders.username WHERE orders.username = ? AND orders.status = 0";
    public static final String SQL_CONFIRM_ORDER = " UPDATE orders SET status = 1 WHERE idorder = ?";
    public static final String SQL_SELECT_ORDERS = " SELECT orders.idorder,orders.username, books.name,books.author,books.information,orders.date FROM orders INNER JOIN books ON books.id=orders.idbook INNER JOIN users ON users.username=orders.username WHERE orders.status = 0";
    public static final String SQL_SELECT_ISSUED_BOOKS = " SELECT orders.idorder,orders.username, books.name,books.author,books.information,orders.date FROM orders INNER JOIN books ON books.id=orders.idbook INNER JOIN users ON users.username=orders.username WHERE orders.status = 1 AND orders.username = ?";
    public static final String SQL_DELETE_RETURNED_BOOK = "DELETE FROM orders WHERE idorder = ? ";
    public static final String SQL_ISSUED_BOOKS = " SELECT orders.idorder,orders.username, books.name,books.author,books.information,orders.date FROM orders INNER JOIN books ON books.id=orders.idbook WHERE orders.status = 1";
    public static final String SQL_UPDATE_RETURNED_BOOK = "UPDATE books SET amount = amount + 1 WHERE id = ?";
    public static final String SQL_IDBOOK_BY_IDORDER = "SELECT idbook FROM orders WHERE idorder = ?";

    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<Order>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_ORDERS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdorder(resultSet.getInt("orders.idorder"));
                order.setUsername(resultSet.getString("orders.username"));
                order.setName(resultSet.getString("books.name"));
                order.setAuthor(resultSet.getString("books.author"));
                order.setInformation(resultSet.getString("books.information"));
                order.setDate(resultSet.getString("orders.date"));
                orders.add(order);
            }
            st.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return orders;
    }

    public List<Order> findAllIssues() throws DAOException {
        List<Order> orders = new ArrayList<Order>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_ISSUED_BOOKS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdorder(resultSet.getInt("orders.idorder"));
                order.setUsername(resultSet.getString("orders.username"));
                order.setName(resultSet.getString("books.name"));
                order.setAuthor(resultSet.getString("books.author"));
                order.setInformation(resultSet.getString("books.information"));
                order.setDate(resultSet.getString("orders.date"));
                orders.add(order);
            }
            st.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return orders;
    }

    public void returnBook(String idorder) throws DAOException {

        int id1 = Integer.parseInt(idorder);
        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();

            ps = cn.prepareStatement(SQL_IDBOOK_BY_IDORDER);
            ps.setInt(1, id1);
            ResultSet resultSet = ps.executeQuery();
            int idbook = 0;
            while (resultSet.next()) {

                idbook = resultSet.getInt("idbook");
                System.out.println(idbook);
            }
            ps = cn.prepareStatement(SQL_DELETE_RETURNED_BOOK);
            ps.setInt(1, id1);
            ps.executeUpdate();

            ps = cn.prepareStatement(SQL_UPDATE_RETURNED_BOOK);
            ps.setInt(1, idbook);
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

    public List<Order> selectOrdersByUsername(String username) throws DAOException {
        List<Order> orders = new ArrayList<Order>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_ORDERS_BY_USERNAME);
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdorder(resultSet.getInt("orders.idorder"));
                order.setName(resultSet.getString("books.name"));
                order.setAuthor(resultSet.getString("books.author"));
                order.setInformation(resultSet.getString("books.information"));
                order.setDate(resultSet.getString("orders.date"));

                orders.add(order);
            }
            st.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return orders;
    }

    public List<Order> selectIssuedBooks(String username) throws DAOException {
        List<Order> orders = new ArrayList<Order>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_ISSUED_BOOKS);
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdorder(resultSet.getInt("orders.idorder"));
                order.setName(resultSet.getString("books.name"));
                order.setAuthor(resultSet.getString("books.author"));
                order.setInformation(resultSet.getString("books.information"));
                order.setDate(resultSet.getString("orders.date"));

                orders.add(order);
            }
            st.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return orders;
    }

    public void confirmOrder(int idOrder) throws DAOException {
        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;

        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_CONFIRM_ORDER);
            ps.setInt(1, idOrder);
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


    public void addOrder(String username, int idbook) throws DAOException {


        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;

        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_ORDER);
            ps.setString(1, username);
            ps.setInt(2, idbook);
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps = null;
            ps = cn.prepareStatement(SQL_UPDATE_BOOKS);
            ps.setInt(1, idbook);
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