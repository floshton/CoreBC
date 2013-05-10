package com.bconnect.common.exception;

/**
 *
 * @author Jorge Rodriguez
 */
public class BConnectException extends Exception {

    public BConnectException(String message) {
        super(message);
    }

    public BConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public BConnectException(Throwable cause) {
        super(cause);
    }
}