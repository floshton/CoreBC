package com.bconnect.common.dao;

import com.bconnect.common.logging.CommonLogger;
import java.util.Collection;
import org.apache.log4j.Logger;

/**
 * Esta clase abstracta define los metodos minimamente requeridos por cualquier
 * DAO de la aplicacion. Implementa el uso de un Logger, para registrar todas
 * las transacciones
 * @author Jorge Rodriguez
 */
public abstract class BaseDao {

    /**
     * Una instancia de Logger, para registrar cualquier evento
     */
    protected Logger logger;
    protected String dbInstance;
    protected Collection dbInstances;

    /**
     * Constructor. Manda llamar al metodo initializeDBHelper()
     * @param dbConnection una instancia de DBConnection
     */
    protected BaseDao(String dbInstance) {
        logger = CommonLogger.getLogger(this.getClass());
        this.dbInstance = dbInstance;
        this.initializeDBHelper();
    }

    public BaseDao() {
    }

    /**
     * Metodo abstracto que inicializa las propiedades necesarias en el
     * DBHelper
     * @param dbConnection una instancia de DBConnection
     */
    protected abstract void initializeDBHelper();

    /**
     *
     * @param dbInstance
     */
    public void setDbInstance(String dbInstance) {
        this.dbInstance = dbInstance;
        this.initializeDBHelper();
    }

    /**
     * 
     * @param dbInstances
     */
    public void setDbInstances(Collection dbInstances) {
        if (!dbInstances.isEmpty()) {
            this.dbInstances = dbInstances;
        }
    }
}
