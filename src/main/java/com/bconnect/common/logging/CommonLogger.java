package com.bconnect.common.logging;

import org.apache.log4j.Logger;

/**
 * Esta clase extiende la clase Logger del API log4j.
 * Proporciona un m?todo que sobreescribe el tipo de factory del Logger
 * @author jorge.rodriguez
 */
public class CommonLogger extends Logger {

    private static CommonLoggerFactory loggerFactory = new CommonLoggerFactory();

    /**
     * Constructor
     * @param name el nombre del Logger
     */
    public CommonLogger(String name) {
        super(name);
    }

    /**
     * Regresa el Logger
     * @param className la clase a la que har? referencia esta instancia de Logger
     */
    public static Logger getLogger(Class className) {
        return getLogger(className.getName());
    }

    /**
     * Sobreescribe Logger.getLogger proporcionando
     * su propio tipo de factory como par?metro
     * @param name el nombre del Logger
     * @return una instancia de Logger
     */
    public static Logger getLogger(String name) {

        Logger logger = Logger.getLogger(name, loggerFactory);
        return logger;
    }
}
