package com.bconnect.common.logging;

import com.bconnect.common.util.CommonConstants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class LoggerUtil {

    private static Logger logger;

    static {
        logger = CommonLogger.getLogger(LoggerUtil.class);
    }

    public LoggerUtil() {
    }

    /**
     * Este método recibe un nivel de Logging en formato String y regresa
     * una instancia de org.apache.log4j.Level correspondiente al nivel especificado
     * @param paramLoggerlevel
     * @return
     */
    public static Level getLoggerLevel(String paramLoggerlevel) {
        Level theLevel = null;
        if (CommonConstants.LOGGER_LEVEL_TRACE.equals(paramLoggerlevel)) {
            theLevel = Level.TRACE;
        } else if (CommonConstants.LOGGER_LEVEL_DEBUG.equals(paramLoggerlevel)) {
            theLevel = Level.DEBUG;
        } else if (CommonConstants.LOGGER_LEVEL_INFO.equals(paramLoggerlevel)) {
            theLevel = Level.INFO;
        } else if (CommonConstants.LOGGER_LEVEL_WARN.equals(paramLoggerlevel)) {
            theLevel = Level.WARN;
        } else if (CommonConstants.LOGGER_LEVEL_ERROR.equals(paramLoggerlevel)) {
            theLevel = Level.ERROR;
        } else if (CommonConstants.LOGGER_LEVEL_FATAL.equals(paramLoggerlevel)) {
            theLevel = Level.FATAL;
        } else if (CommonConstants.LOGGER_LEVEL_OFF.equals(paramLoggerlevel)) {
            theLevel = Level.OFF;
        }
        return theLevel;
    }

    public static void logEveryLevel(Logger logger, String mensaje) {
        logger.trace(mensaje);
        logger.debug(mensaje);
        logger.info(mensaje);
        logger.warn(mensaje);
        logger.error(mensaje);
        logger.fatal(mensaje);
    }

    public static void logEveryLevel(Logger logger, String mensaje, Throwable t) {
        logger.trace(mensaje, t);
        logger.debug(mensaje, t);
        logger.info(mensaje, t);
        logger.warn(mensaje, t);
        logger.error(mensaje, t);
        logger.fatal(mensaje, t);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }
}
