package org.example.domaine.exceptions;

public class InputMissingException extends RuntimeException{
    public InputMissingException() {
        super();
    }

    public InputMissingException(String message) {
        super(message);
    }

    public InputMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputMissingException(Throwable cause) {
        super(cause);
    }

    protected InputMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
