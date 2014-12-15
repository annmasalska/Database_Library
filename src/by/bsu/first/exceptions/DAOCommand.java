package by.bsu.first.exceptions;


public class DAOCommand extends Exception {
    public DAOCommand(String message) {
        super(message);
    }

    public DAOCommand(String message, Throwable e) {
        super(message, e);
    }

    public DAOCommand(Throwable e) {
        super(e);
    }
}
