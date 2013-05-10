package com.bconnect.common.db;

import com.bconnect.common.logging.CommonLogger;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @author jorge.rodriguez
 * This class is in charge of returning DB Connection instances depending on the
 * specified type. It only implements static methods
 */
public class _ConnectionFactory
{
    
    private static Context initialContext;
    private static Context envContext;
    private static DataSource ds;
    private static Logger logger = CommonLogger.getLogger(ConnectionFactory.class);
    /**
     * This method initializes the Factory by setting the Context
     */
    public static void initialize()
    {        
        try
        {
            initialContext = new InitialContext();
            envContext = (Context)initialContext.lookup("java:/comp/env");
        }
        catch(NamingException ne)
        {
            logger.error("No se pudo inicializar el Factory", ne);
            ne.printStackTrace();
        }
    }
    
    /**
     * regresa una instancia de Connection
     * @param dbInstance la instancia de BD a la que se refiere
     * @return una instancia de Connection
     * @throws SQL Exception
     */
    public static Connection newConnection(String dbInstance) throws SQLException
    {
        Connection conn = null;
        
        try
        {
                ds = getDataSource(dbInstance);
                conn = ds.getConnection();
                
                //conn.setAutoCommit(false);
        }
        catch(NamingException ne)
        {
            logger.error("No se pudo crear uina instancia de la conexion", ne);
            ne.printStackTrace();
        }
        
        return conn;
    }
    
    /**
     * Regresa una instancia de DataSource de acuerdo a la instancia definida
     * @param dbInstance la instancia de BD a la que se conectar
     * @return un objeto DataSource del tipo especificado
     * @throws namingException
     */
    private static DataSource getDataSource(String dbInstance) throws NamingException
    {
        return (DataSource)envContext.lookup(dbInstance);
    }
}
