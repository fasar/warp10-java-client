package fr.avalonlab.warp10.exceptions;

public class W10FormatNotUnderstand extends RuntimeException {
    public W10FormatNotUnderstand() {
        super();
    }

    public W10FormatNotUnderstand(String message) {
        super(message);
    }

    public W10FormatNotUnderstand(String message, Throwable cause) {
        super(message, cause);
    }

    public W10FormatNotUnderstand(Throwable cause) {
        super(cause);
    }

    protected W10FormatNotUnderstand(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
