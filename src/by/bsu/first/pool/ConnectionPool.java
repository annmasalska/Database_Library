package by.bsu.first.pool;

import by.bsu.first.exceptions.ConnectionPoolException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.database");

    private ConnectionPool() {

        String path = resourceBundle.getString("database.path");
        String login = resourceBundle.getString("database.user");
        String pass = resourceBundle.getString("database.password");
        int poolSize = Integer.parseInt(resourceBundle.getString("database.poolsize"));

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("problem with database connection");


        }
        connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
        try {

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(path, login, pass);
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
            throw new ConnectionPoolException("problem with  connection", e);
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
            } finally {
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
        for (int i = 0; i < connectionQueue.size(); i++) {
            try {
                connection = connectionQueue.take();
                if (connection != null)
                    connection.close();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("problem with  connection", e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}


