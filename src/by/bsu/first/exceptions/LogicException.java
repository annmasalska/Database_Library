package by.bsu.first.exceptions;

public class LogicException extends Exception {
    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable e) {
        super(message, e);
    }

    public LogicException(Throwable e) {
        super(e);
    }
}

