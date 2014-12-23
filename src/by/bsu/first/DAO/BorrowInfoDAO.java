package by.bsu.first.DAO;

import by.bsu.first.entity.Book;
import by.bsu.first.entity.BorrowInfo;
import by.bsu.first.exceptions.ConnectionPoolException;
import by.bsu.first.exceptions.DAOCommandException;
import by.bsu.first.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowInfoDAO extends AbstractDAO<BorrowInfo> {

    static Logger logger = Logger.getLogger(BorrowInfoDAO.class);
    public static final String SQL_SELECT_BORROW_INFO_BY_ID = "SELECT borrowinfo.idissue, books.name,books.author,books.information,borrowinfo.date, borrowinfo.status FROM borrowinfo INNER JOIN books ON books.id=borrowinfo.idbook INNER JOIN readers ON readers.idcard=borrowinfo.idcard WHERE borrowinfo.idcard = ? ";
    public static final String SQL_INSERT_NEW_BORROW_INFO = "INSERT INTO borrowinfo (idissue, idbook, idcard, date, status) VALUES(?, ?, ? , ? , ?)";
    public static final String SQL_UPDATE_BOOKS = "UPDATE books SET amount = amount - 1 WHERE id = ?";

    //  public static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id=?";
    //   public static final String SQL_SELECT_BOOKS_BY_GENRE = "SELECT books.id,books.name,books.author,books.amount,books.information, genres.genre FROM books LEFT JOIN genres ON books.genreID=genres.genreID  WHERE books.genreID = ? ";


    //@Override
    public List<BorrowInfo> findAll() throws DAOCommandException {
        List<BorrowInfo> books = new ArrayList<BorrowInfo>();
        Connection cn = null;
        Statement st = null;
        ConnectionPool pool = ConnectionPool.getPool();
        try {
            cn = pool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_BORROW_INFO_BY_ID);
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return books;
    }

    public List<BorrowInfo> selectBorrowInfo(int idcard) throws DAOCommandException {
        List<BorrowInfo> borrowInfo = new ArrayList<BorrowInfo>();
        Connection cn = null;
        ConnectionPool pool = ConnectionPool.getPool();
        PreparedStatement st = null;
        try {
            cn = pool.getConnection();
            st = cn.prepareStatement(SQL_SELECT_BORROW_INFO_BY_ID);
            st.setInt(1, idcard);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                BorrowInfo borrowinfo = new BorrowInfo();
                borrowinfo.setIdissue(resultSet.getInt("borrowinfo.idissue"));
                borrowinfo.setName(resultSet.getString("books.name"));
                borrowinfo.setAuthor(resultSet.getString("books.author"));
                borrowinfo.setInformation(resultSet.getString("books.information"));
                borrowinfo.setDate(resultSet.getString("borrowinfo.date"));
                borrowinfo.setStatus(resultSet.getInt("borrowinfo.status"));
                borrowInfo.add(borrowinfo);
            }
            st.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOCommandException(e.getCause());

        } finally {
            close(st);
            pool.returnConnection(cn);

        }
        return borrowInfo;
    }

    public void addBorrowBook(int idissue, String idbook, String idcard, String date) throws DAOCommandException {

        ConnectionPool pool = ConnectionPool.getPool();
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = pool.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_NEW_BORROW_INFO);
            ps.setInt(1, idissue);
            ps.setInt(2, Integer.parseInt(idbook));
            ps.setInt(3, Integer.parseInt(idcard));
            ps.setString(4, date);
            ps.setInt(5, 1);
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

    }


}


