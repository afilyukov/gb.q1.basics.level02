package utils;

public class LocalDataException extends Exception {
    public LocalDataException(String message) {
        super(message);
    }

    public LocalDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
