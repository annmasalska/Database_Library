package by.bsu.first.exceptions;


public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable e) {
        super(message, e);
    }

    public ConnectionPoolException(Throwable e) {
        super(e);
    }
}
