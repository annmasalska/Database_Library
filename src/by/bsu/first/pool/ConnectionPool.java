package by.bsu.first.pool;

import by.bsu.first.exceptions.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    static Logger logger = Logger.getLogger(ConnectionPool.class);
    private BlockingQueue<Connection> connectionQueue;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool pool;
    private final int POOL_SIZE = 10;
    private final String DATABASE_PATH = "jdbc:mysql://localhost:3306/library";

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("problem with database connection");


        }
        connectionQueue = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        try {

            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DATABASE_PATH, "root", "2286890");
                connectionQueue.offer(connection);
            }
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("problem with  connection");


        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;

        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("problem with  connection",e);
        }
        return connection;
    }

    public static ConnectionPool getPool() {

        if (!isCreated.get()) {
            try {
                lock.lock();
                if (pool == null) {
                    pool = new ConnectionPool();
                    isCreated.set(true);
                }
        }finally{
            lock.unlock(); // снятие блокировки
        }
    }
        return pool;
    }

    public void returnConnection(Connection connection) {
        connectionQueue.offer(connection);

    }

    public void closePool() throws ConnectionPoolException {
        Connection connection = null;
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                connection = connectionQueue.take();
                if (connection != null)
                    connection.close();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("problem with  connection",e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}


