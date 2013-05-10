package com.bconnect.common.db;

import com.bconnect.common.logging.CommonLogger;
import org.apache.log4j.Logger;

/**
 * Esta clase se emplea para encapsular todas aquellas propiedades asociadas
 * con un DataSource
 * @author Jorge Rodriguez
 */
public class CommonDataSource {

    /**
     * Nombre del DataSource. Este nombre es pasado como parámetro en el proceso
     * de instanciación de conexiones 
     */
    private String name = null;
    /**
     * El nombre completo de la clase driver de la DB
     */
    private String dbDriverName = null;
    /**
     * Nombre de usuario de la DB
     */
    private String dbUser = null;
    /**
     * Password de la DB
     */
    private String dbPassword = null;
    /**
     * URI que identifica y localiza la instancia de DB a ser utilizada
     */
    private String dbURI = null;
    /**
     * Número mínimo de conexiones a ser conservadas por el pool de conexiones
     */
    private int dbPoolMinSize = 0;
    /**
     * Número máximo de conexiones a ser conservadas al mismo tiempo en el pool
     * de conexiones
     */
    private int dbPoolMaxSize = 0;
    /**
     * Número máximo de conexiones permitidas
     */
    private int dbMaxActive = 0;
    /**
     * Número máximo de conexiones que pueden permanecer en espera
     */
    private int dbMaxIdle = 0;
    /**
     * Número de milisegundos que el pool debe esperar para recibir una conexión.
     * Si este tiempo se excede, se lanza una excepción.
     */
    private int dbMaxWait = 0;

    private String project;

    /**
     * Constructor de la clase
     * @param name
     * @param dbDriverName
     * @param dbUser
     * @param dbPassword
     * @param dbURI
     * @param dbPoolMinSize
     * @param dbPoolMaxSize
     * @param dbMaxActive
     * @param dbMaxIdle
     * @param dbMaxWait
     */
    public CommonDataSource(String name, String dbDriverName, String dbUser,
            String dbPassword, String dbURI, int dbPoolMinSize, int dbPoolMaxSize,
            int dbMaxActive, int dbMaxIdle, int dbMaxWait) {

        this.name = name;
        this.dbDriverName = dbDriverName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.dbURI = dbURI;
        this.dbPoolMinSize = dbPoolMinSize;
        this.dbPoolMaxSize = dbPoolMaxSize;
        this.dbMaxActive = dbMaxActive;
        this.dbMaxIdle = dbMaxIdle;
        this.dbMaxWait = dbMaxWait;
    }

    public String getName() {
        return name;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbURI() {
        return dbURI;
    }

    public int getDbPoolMinSize() {
        return dbPoolMinSize;
    }

    public int getDbPoolMaxSize() {
        return dbPoolMaxSize;
    }

    public int getDbMaxActive() {
        return dbMaxActive;
    }

    public int getDbMaxIdle() {
        return dbMaxIdle;
    }

    public int getDbMaxWait() {
        return dbMaxWait;
    }

    /**
     * @return the proyecto
     */
    public String getProject() {
        return project;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProject(String project) {
        this.project = project;
    }
}
