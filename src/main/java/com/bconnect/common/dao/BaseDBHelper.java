package com.bconnect.common.dao;

import com.bconnect.common.db.ConnectionManager;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.ResourcesManager;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 * Esta clase abstracta define metodos amonimos para ser usados por subclases
 * @author Jorge Rodriguez
 */
public abstract class BaseDBHelper {

    protected ResourceBundle resource;
    protected ResourceBundle commonResource;
    protected String dbInstance;
    /**
     * Una instancia de Logger, para regustrar cualquier transaccion
     */
    protected Logger logger;

    /**
     * Constructor
     * @param dbConnection una instancia de DBConnection
     */
    protected BaseDBHelper(String dbInstance) {
        logger = CommonLogger.getLogger(this.getClass());
        this.dbInstance = dbInstance;
        this.commonResource = ResourcesManager.getCommonSQLResource();
        try {
            this.setResource(ResourcesManager.getSQLResource());
        } catch (Exception e) {
            logger.info("Se inicializa DBHelper sin archivo SQL específico de la aplicación", e);
        }
    }

    /**
     * Carga los registros del archivo especificado
     * @param resourceName el archivo del que se cargaran los recursos
     */
    protected void loadResources(String resourceName) {
        this.resource = ResourceBundle.getBundle(resourceName);
    }

    protected void setResource(ResourceBundle resource) {
        this.resource = resource;
    }

    /**
     * Construye una instancia de DBConnection de acuerdo a la instancia de DB
     * especificada en el constuctor
     * @return una instancia de DBConnection lista para usarse
     */
    protected DBConnection getConnection() {
        DBConnection connection = null;
        try {
            connection = ConnectionManager.getDBConnection(this.dbInstance);
        } catch (DBException ex) {
            logger.error("Se produjo un error al crear la conexion a la BD");
            logger.error(ex);
            ex.printStackTrace();
        }
        return connection;
    }
}
