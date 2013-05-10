package com.bconnect.common.db;

import com.bconnect.common.exception.DBException;
import com.bconnect.common.logging.CommonLogger;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author jorge.rodriguez
 * Esta clase unicamente incluye el metodo estatico getDBConnection para
 * regresar una instancia de conexion
 */
public class _ConnectionManager
{
   
    /**
     * The class constructor
     */
    private _ConnectionManager()
    {
        
    }
    
    /**
     * Regresa una instancia de DBConnection basado en los parametros
     * @param dbInstance la instancia de DBConnection
     * @param cache define si la conexion usara cache de sentencias SQL
     * @return una DBConnection lista para usarse
     * @throws DBException
     */
    public static DBConnection getDBConnection(String dbInstance)
    throws DBException
    {
        Connection conn = null;
        Logger logger = CommonLogger.getLogger(ConnectionManager.class);
        
        try
        {
            conn = ConnectionFactory.newConnection(dbInstance);
        }
        catch(SQLException sqle)
        {
            logger.error("Ocurrio un error al crear la conexion", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error al crear la conexion",sqle);
        }
        
        DBConnection dbConn = new DBConnection(conn, dbInstance);
        
        return dbConn;
    }
}
