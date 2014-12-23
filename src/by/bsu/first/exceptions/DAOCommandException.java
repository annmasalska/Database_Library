package by.bsu.first.exceptions;


public class DAOCommandException extends Exception {
    public DAOCommandException(String message) {
        super(message);
    }

    public DAOCommandException(String message, Throwable e) {
        super(message, e);
    }

    public DAOCommandException(Throwable e) {
        super(e);
    }
}
