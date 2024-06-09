package utils;

public class LocalSizeException extends RuntimeException {
    public LocalSizeException(String message) {
        super(message);
    }

    public LocalSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
