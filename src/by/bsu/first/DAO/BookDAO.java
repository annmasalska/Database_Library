package by.bsu.first.DAO;

import by.bsu.first.entity.Book;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAO extends AbstractDAO<Book> {

    static Logger logger = Logger.getLogger(BookDAO.class);
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT books.id,books.name,books.author,books.amount,books.information, genres.genre FROM books LEFT JOIN genres ON books.genreID=genres.genreID ";
    public static final String SQL_INSERT_NEW_BOOK = "INSERT INTO books(id, name, author, genreID, amount, information) VALUES(?, ?, ? , ? , ? , ?)";
    public static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id=?";
    public static final String SQL_SELECT_BOOKS_BY_GENRE = "SELECT books.id,books.name,books.author,books.amount,books.information, genres.genre FROM books LEFT JOIN genres ON books.genreID=genres.genreID  WHERE books.genreID = ? ";
    public static final String SQL_SELECT_BOOK = "SELECT amount FROM books WHERE id=?";
   // public static final String SQL_INSERT_ORDER = "INSERT INTO books(id, name, author, genreID, amount, information) VALUES
   // @Override
    public List<Book> findAll() throws DAOCommandException {
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
               // int amount=resultSet.getInt("books.amount");

                book.setAmount(resultSet.getInt("books.amount"));
                book.setInformation(resultSet.getString("books.information"));
                book.setGenreID(resultSet.getString("genres.genre"));
                if(resultSet.getInt("books.amount")>0)
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }

    public List<Book> selectByGenre(String genreID) throws DAOCommandException {
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
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }

    public void addBook(String id, String name, String author, String genreID, String amount, String information) throws DAOCommandException {

        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
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
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(ps);
            pool.returnConnection(cn);

        }

    }

    public void deleteBook(String idDelete) throws DAOCommandException {
        int id1 = Integer.parseInt(idDelete);


        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_DELETE_BOOK);
            ps.setInt(1, id1);
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

    public List<Book> searchByAuthor(String author) throws DAOCommandException {

        List<Book> books = new ArrayList<Book>();
        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();

       // PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            //st = cn.prepareStatement(SQL_FIND_BY_AUTHOR);
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_BOOKS);
            //st.setString(1,author );
            //ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("books.id"));
                book.setName(resultSet.getString("books.name"));
                book.setGenreID(resultSet.getString("genres.genre"));
                book.setAmount(resultSet.getInt("books.amount"));
                book.setInformation(resultSet.getString("books.information"));
                book.setAuthor(resultSet.getString("books.author"));
                Pattern pt = Pattern.compile(author);
                Matcher mt = pt.matcher(resultSet.getString("books.author"));
                if (mt.find()) {
                    books.add(book);

                }



            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }
    public Boolean checkExistence(String idbook) throws DAOCommandException {

        Connection cn = null;
        PreparedStatement st = null;
        Boolean check=false;
        ConnectionPool pool = ConnectionPool.getPool();
       try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BOOK);
             st.setInt(1, Integer.parseInt(idbook));

            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {

                int amount=resultSet.getInt("amount");
                if(amount >0) check=true;
                else check=false;
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());
            //logger.error("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return check;
    }
}