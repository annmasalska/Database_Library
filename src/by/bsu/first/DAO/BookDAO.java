package by.bsu.first.DAO;

import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommand;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends AbstractDAO<Book> {

    static Logger logger = Logger.getLogger(BookDAO.class);
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT books.id,books.name,books.author,books.amount,books.information, genres.genre FROM books LEFT JOIN genres ON books.genreID=genres.genreID ";
    public static final String SQL_INSERT_NEW_BOOK = "INSERT INTO books(id, name, author, genreID, amount, information) VALUES(?, ?, ? , ? , ? , ?)";
    public static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id=?";
    public static final String SQL_SELECT_BOOKS_BY_GENRE = "SELECT books.id,books.name,books.author,books.amount,books.information, genres.genre FROM books LEFT JOIN genres ON books.genreID=genres.genreID  WHERE books.genreID = ? ";

    @Override
    public List<Book> findAll() throws DAOCommand {
        List<Book> books = new ArrayList<Book>();
        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();


        try {
            cn = pool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("books.id"));
                book.setName(resultSet.getString("books.name"));
                book.setAuthor(resultSet.getString("books.author"));
                book.setAmount(resultSet.getInt("books.amount"));
                book.setInformation(resultSet.getString("books.information"));
                book.setGenreID(resultSet.getString("genres.genre"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        }
        catch (ConnectionPoolException e) {
            throw new DAOCommand(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        }
        finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }

    public List<Book> selectByGenre(String genreID) throws DAOCommand {
        List<Book> books = new ArrayList<Book>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();

        int genreid1 = Integer.parseInt(genreID);
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BOOKS_BY_GENRE);
            st.setInt(1, genreid1);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("books.id"));
                book.setName(resultSet.getString("books.name"));
                book.setAuthor(resultSet.getString("books.author"));
                book.setAmount(resultSet.getInt("books.amount"));
                book.setInformation(resultSet.getString("books.information"));
                book.setGenreID(resultSet.getString("genres.genre"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        }
        catch (ConnectionPoolException e) {
            throw new DAOCommand(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        }
        finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }

    public void addBook(String id, String name, String author, String genreID, String amount, String information) throws DAOCommand {

        ConnectionPool pool = ConnectionPool.getPool();
        Connection  cn= null;
        PreparedStatement ps = null;
        try {
            cn=pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_BOOK);
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setInt(4, Integer.parseInt(genreID));
            ps.setString(5, amount);
            ps.setString(6, information);
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

    public void deleteBook(String idDelete) throws DAOCommand {
        int id1 = Integer.parseInt(idDelete);


        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn=null;
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_DELETE_BOOK);
            ps.setInt(1, id1);
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