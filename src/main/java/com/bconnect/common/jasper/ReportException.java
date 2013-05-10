package com.bconnect.common.jasper;

/**
 *
 * @author jorge.rodriguez
 */
public class ReportException extends Exception {

    public ReportException(String message) {
        super(message);
    }

    public ReportException(Throwable cause) {
        super(cause);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
