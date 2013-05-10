package com.bconnect.common.exception;

/**
 *
 * @author Jorge Rodriguez
 */
public class IncompleteUserDataException extends BConnectException {

    public IncompleteUserDataException(String message) {
        super(message);
    }

    public IncompleteUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompleteUserDataException(Throwable cause) {
        super(cause);
    }
}