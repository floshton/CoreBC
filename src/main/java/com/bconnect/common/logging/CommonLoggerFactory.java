package com.bconnect.common.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Esta clase implementa la interfaz LoggerFactory para tener
 * una clase del tipo Logger Factory especifica para la aplicacion
 * @author Jorge Rodriguez
 */
public class CommonLoggerFactory implements LoggerFactory {

    /**
     * Constructor
     */
    public CommonLoggerFactory() {
    }

    /**
     * Crea una nueva instancia del Logger de esta aplicacion
     * @param name el nombre del Logger
     * @return una instancia de Logger
     */
    public Logger makeNewLoggerInstance(String name) {
        return new CommonLogger(name);
    }
}
