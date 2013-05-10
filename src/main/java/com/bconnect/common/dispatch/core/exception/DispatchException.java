package com.bconnect.common.dispatch.core.exception;

/**
 *
 * @author floshton
 */
public class DispatchException extends Exception {

    public DispatchException() {
        super();
    }

    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatchException(Throwable cause) {
        super(cause);
    }

    public DispatchException(String message) {
        super(message);
    }
}
