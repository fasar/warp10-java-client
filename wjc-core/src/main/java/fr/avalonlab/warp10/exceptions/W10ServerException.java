package fr.avalonlab.warp10.exceptions;

public class W10ServerException extends RuntimeException {
    public W10ServerException() {
        super();
    }

    public W10ServerException(String message) {
        super(message);
    }

    public W10ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public W10ServerException(Throwable cause) {
        super(cause);
    }

    protected W10ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
