package com.bconnect.common.db;

import com.bconnect.common.db.util.DynamicPreparedStatement;
import com.bconnect.common.db.util.CommonCallableStatement;
import com.bconnect.common.db.util.CommonPreparedStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.exception.DBException;

/**
 * Esta clase encapsula las acciones mas comunes cuando se usa una
 * conexion a base de datos. Implementa la interfaz HttpSessionBindingListener
 * @author Jorge Rodriguez
 */
public class DBConnection implements HttpSessionBindingListener {

    private Connection connection;
    private String dbInstance;
    private Map<String, PreparedStatement> preparedStatementCache;
    private Map<String, CallableStatement> callableStatementCache;
    private boolean cache;
    private Logger logger;

    /**
     * Constructor
     * @param connection una instancia de Connection
     * @param dbInstance la instancia a la que se asociara la conexion
     */
    public DBConnection(Connection connection, String dbInstance) {
        this.connection = connection;
        preparedStatementCache = new HashMap<String, PreparedStatement>();
        callableStatementCache = new HashMap<String, CallableStatement>();
        this.dbInstance = dbInstance;
        this.cache = true;
        this.logger = CommonLogger.getLogger(this.getClass());
    }

    /**
     * Metodo get para la conexion
     * @return la conexion misma
     */
    private Connection getConnection() {
        while (isConnectionClosed()) {
            try {
                this.connection =
                        ConnectionFactory.newConnection(this.getConnectionInstance());
                this.connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                this.logger.error("La conexion a la instancia "
                        + this.getConnectionInstance() + " no se pudo crear", sqle);
            }
        }
        return connection;
    }

    /**
     * Regresa el nombre de la instancia a la que se relaciona esta conexion
     * @return el nombre de la instancia de la DB
     */
    public String getConnectionInstance() {
        return dbInstance;
    }

    /**
     * Evalua si la conexion se encuentra cerrada
     * @return true si la conexion se encuentra cerrada
     */
    private boolean isConnectionClosed() {

        boolean isClosed = true;

        try {
            if (this.connection == null) {
                isClosed = false;
            } else {
                this.connection.createStatement();
                isClosed = false;
            }
        } catch (SQLException sqle) {
            this.logger.debug("No se pudo crear el Statement, la conexion a "
                    + "la base de datos se encuentra cerrada", sqle);
        } catch (NullPointerException npe) {
            this.logger.error("El objeto CONNECTION es nulo", npe);
        }

        return isClosed;
    }

    /**
     * Recibe una sentencia SQL y regresa un PreparedStatement para ser
     * usado en cualquier query
     * @param sqlStatement la sentencia SQL con placeholders
     * @return una instancia de PreparedStatement
     */
    public PreparedStatement getPreparedStatement(String sqlStatement)
            throws DBException {
        try {
            PreparedStatement stmt = null;

            if (this.cache) {
                stmt =
                        this.preparedStatementCache.get(sqlStatement);
            }

            if (stmt == null) {
                stmt = this.getConnection().prepareStatement(sqlStatement);
                this.preparedStatementCache.put(sqlStatement, stmt);
            }
            return stmt;
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
        }
    }

    public CommonPreparedStatement getCommonPreparedStatement(String sqlStatement)
            throws DBException {
        try {
            PreparedStatement stmt = null;

            if (this.cache) {
                stmt =
                        this.preparedStatementCache.get(sqlStatement);
            }

            if (stmt == null) {
                stmt = this.getConnection().prepareStatement(sqlStatement);
                this.preparedStatementCache.put(sqlStatement, stmt);
            }
            return new CommonPreparedStatement(stmt);
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
        }
    }

    public Statement getStatement()
            throws DBException {
        try {
            Statement stmt = null;

            if (stmt == null) {
                stmt = this.getConnection().createStatement();
                //this.preparedStatementCache.put(sqlStatement, stmt);
            }
            return stmt;
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error cuando se intentaba crear el PreparedStatement", sqle);
        }
    }

    /**
     * Recibe una sentencia SQL y regresa un CallableStatement para ser usado
     * en cualquier Stored procedure
     * @param sqlStatement la sentencia SQL con placeholders
     * @return una instancia de CallableStatement
     * @throws DBException
     */
    public CallableStatement getCallableStatement(String sqlStatement)
            throws DBException {
        try {
            CallableStatement stmt = null;

            if (this.cache) {
                stmt =
                        this.callableStatementCache.get(sqlStatement);
            }

            if (stmt == null) {
                stmt = this.getConnection().prepareCall(sqlStatement);
                this.callableStatementCache.put(sqlStatement, stmt);
            }
            return stmt;
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error cuando se intentaba crear el CallableStatement", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error cuando se intentaba crear el CallableStatement", sqle);
        }
    }

    public CommonCallableStatement getCommonCallableStatement(String sqlStatement)
            throws DBException {
        CommonCallableStatement sCallStmt = null;
        try {
            CallableStatement stmt = null;

            if (this.cache) {
                stmt =
                        this.callableStatementCache.get(sqlStatement);
            }

            if (stmt == null) {
                stmt = this.getConnection().prepareCall(sqlStatement);
                this.callableStatementCache.put(sqlStatement, stmt);
            }
            sCallStmt = new CommonCallableStatement(stmt);
            return sCallStmt;
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error cuando se intentaba crear el CallableStatement", sqle);
            sqle.printStackTrace();
            throw new DBException(
                    "Ocurrio un error cuando se intentaba crear el CallableStatement", sqle);
        }
    }

    /**
     * Lleva a cabo la operacion Commit de la BD
     * @throws DBException
     */
    public void commit() throws DBException {
        try {
            if (this.connection != null) {
                this.connection.commit();
            }
        } catch (SQLException sqle) {
            this.logger.error("Imposible llevar acabo la operacion Commit", sqle);
            throw new DBException("Imposible llevar acabo la operacion Commit", sqle);
        }
    }

    /**
     * Lleva a cabo la operacion Rollback de la BD
     * @throws DBException
     */
    public void rollback() throws DBException {
        try {
            if (this.connection != null) {
                this.connection.rollback();
            }
        } catch (SQLException sqle) {
            this.logger.error("Imposible llevar acabo la operacion Rollback", sqle);
            throw new DBException("Imposible llevar acabo la operacion Rollback", sqle);
        }
    }

    /**
     * Cierra la conexion a la BD y no se pueden ejecutar mas consultas
     * @throws DBException
     */
    public void close() throws DBException {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException sqle) {
            this.logger.error("Ocurrio un error al intentar cerrar la conexion a la BD", sqle);
            throw new DBException("Ocurrio un error al intentar cerrar la conexion a la BD", sqle);
        }
    }

    /**
     * Limpia los caches de PreparedStatement y CallableStatement
     * @throws DBException
     */
    private void releaseStatementCache() throws DBException {
        this.clearStatementCache(this.preparedStatementCache);
        this.clearStatementCache(this.callableStatementCache);
    }

    /**
     * Limpia un cache en especifico
     * @param cache el cache a limpiar
     * @throws DBException
     */
    private void clearStatementCache(Map cache) throws DBException {
        Set keySet = cache.keySet();
        Iterator iter = keySet.iterator();
        String sqlStatement = "";
        Statement stmt = null;
        while (iter.hasNext()) {
            sqlStatement = (String) iter.next();
            try {
                stmt = (Statement) cache.get(sqlStatement);
                stmt.close();
            } catch (SQLException sqle) {
                this.logger.error("Ocurrio un error al intentar cerrar las sentencias SQL", sqle);
                throw new DBException(
                        "Ocurrio un error al intentar cerrar las sentencias SQL", sqle);
            }
        }
        cache.clear();
    }

    private void release() throws DBException {
        this.releaseStatementCache();
        this.close();
    }

    /**
     * Crea un objeto DBConnection al crearse una sesion
     */
    public void valueBound(HttpSessionBindingEvent event) {
        this.logger.debug("Se ha creado un objecto DBConnection");
    }

    /**
     * Destruye una conexion a la BD cuando se termina una sesion
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
        this.logger.debug("Se ha destruido un objecto DBConnection");

        try {
            this.release();
        } catch (DBException dbe) {
            this.logger.error("La conexion no se pudo cerrar", dbe);
        }
    }

    /**
     * Asigna la propiedad del cache a true o false
     * @param cache el nuevo valor del cache
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }
}
