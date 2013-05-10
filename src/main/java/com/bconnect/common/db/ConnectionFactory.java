package com.bconnect.common.db;

import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.log4j.Logger;

/**
 * Esta clase lleva a cabo la creación de conexiones a DB dependiendo de la
 * instancia solicitada
 * @author Jorge Rodriguez
 */
public class ConnectionFactory {

    private static Logger logger = CommonLogger.getLogger(ConnectionFactory.class);
    public static Map<String, DataSource> dataSources = null;
    private static Set<String> connectionNames = null;
    private static GenericObjectPool pool = null;
    private static int CONNECTION_POOL_TYPE = 0;

    /**
     * Constructor de la clase
     * @param dataSources una lista de DataSources leídas de un archivo de
     * configuración, usualmente context.xml
     */
    public ConnectionFactory(List<CommonDataSource> dataSources) {
        try {
            connectToDB(dataSources);
        } catch (Exception e) {
            logger.error("Error al construir el ConnectionFactory", e);
        }
    }

    /**
     * Método estático que es invocado mediante una clase que implementa la
     * interfaz ContextListener, de tal forma que se ejecute al inicio de la carga
     * de la aplicación.
     * Este método se encarga de crear los pool de conexiones para todos
     * los DataSource definidos es un archivo de configuración (context.xml)
     * @param context una instancia del contexto de la aplicación, para poder
     * encontrar la ruta al archivo xml
     */
    public static void initialize(ServletContext context) {
        try {
            String poolTypeString = context.getInitParameter(CommonConstants.CONNECTION_POOL_TYPE_PARAMETER);
            if (poolTypeString != null) {
                CONNECTION_POOL_TYPE = Integer.parseInt(poolTypeString);
            }
            switch (CONNECTION_POOL_TYPE) {
                case CommonConstants.CONNECTION_POOL_TYPE_APPLICATION:
                    logger.info("Se inicializa el pool de conexiones manejado por la aplicación");
                    ConnectionFactory.initializeApplicationDBPool(context);
                    break;
                case CommonConstants.CONNECTION_POOL_TYPE_CONTAINER:
                    ConnectionFactory.initializeContainerDBPool(context);
                    logger.info("Se inicializa el pool de conexiones manejado por el contenedor");
                    break;
                default:
                    logger.fatal("Error al inicializar el ConnectionFactory debido a que no se"
                            + "asignó un tipo de pool");
                    throw new RuntimeException("No se puede inicializar la aplicación "
                            + "debido a que no se asignó un tipo de pool de conexiones");
            }
        } catch (Exception e) {
            logger.fatal("Error al inicializar el ConnectionFactory", e);
        }
    }

    private static void initializeApplicationDBPool(ServletContext context) {
        try {
            dataSources = new HashMap<String, DataSource>();
            connectToDB(ConfigurationLoader.getDataSources(context));

            ConnectionFactory.loadConnectionNames();
        } catch (Exception e) {
            logger.fatal("Error al inicializar el pool en la aplicación", e);
        }
    }

    private static void initializeContainerDBPool(ServletContext context) {
        logger.info("Se inicializa la aplicación con pool manejado por el contenedor");
    }

    /**
     * Llena un objeto set con los nombres de las conexiones disponibles dentro
     * de la aplicación. i.e. jdbc/catalogo
     */
    private static void loadConnectionNames() {
        if (!ConnectionFactory.dataSources.isEmpty()) {
            ConnectionFactory.connectionNames = ConnectionFactory.dataSources.keySet();
        }
    }

    public static Set getConnectionNames() {
        return connectionNames;
    }

    /**
     * Libera los recursos usados por la clase. No debe llamarse explícitamente, 
     * pues se ejecuta por el garbage collector
     */
    @Override
    protected void finalize() {
        logger.debug("Finalizando ConnectionFactory");
        try {
            super.finalize();
        } catch (Throwable ex) {
            logger.error("Fallo en finalizacion de ConnectionFactory", ex);
        }
    }

    /**
     * Recibe una lista con objetos del tipo CommonDataSource y crea objetos
     * del tipo DataSource para ser utilizados en la aplicacion, para crear
     * conexiones a DB
     * @param dataSources una lista con objetos del tipo CommonDataSource
     */
    private static void connectToDB(List<CommonDataSource> dataSources) {
        for (CommonDataSource dataSource : dataSources) {
            ConnectionFactory.connectToDB(dataSource);
        }
    }

    public static void connectToDB(CommonDataSource dataSource) {
        if (!ConnectionFactory.dataSources.containsKey(dataSource.getName())) {
            DataSource newDataSource = null;
            try {
                java.lang.Class.forName(dataSource.getDbDriverName()).newInstance();
            } catch (Exception e) {
                logger.error("Error al obtener el driver: " + dataSource.getDbDriverName(), e);
            }
            logger.debug("Intentando conectar a la DB...");
            try {
                newDataSource = setupDataSource(dataSource);
                ConnectionFactory.dataSources.put(dataSource.getName(), newDataSource);

                logger.debug("Conexion exitosa a la DB");
            } catch (Exception e) {
                logger.error("Error al conectarse a la DB ", e);
            }
        }
    }

    public static DataSource setupDataSource(CommonDataSource ds) throws Exception {
        logger.debug("intentando insanciar genericObjectPool");
        GenericObjectPool connectionPool = new GenericObjectPool(null);

        logger.debug("Asignando propiedades de objeto de conexión");
        connectionPool.setMaxIdle(ds.getDbMaxIdle());
        connectionPool.setMaxActive(ds.getDbMaxActive());
        connectionPool.setMaxWait(ds.getDbMaxWait());
        connectionPool.setTestOnBorrow(true);
        ConnectionFactory.pool = connectionPool;

        logger.debug("Creando objeto ConnectionFactory");
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                ds.getDbURI(), ds.getDbUser(), ds.getDbPassword());

        String validationQuery = ConnectionFactory.getValidationQuery(ds.getDbDriverName());

        logger.debug("Inicializando PoolableConnectionFactory");
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, connectionPool,
                null, validationQuery, false, true);

        logger.debug("Asignando PoolableConnectionFactory");
        connectionPool.setFactory(poolableConnectionFactory);
        PoolingDataSource dataSource = new PoolingDataSource(connectionPool);
        return dataSource;
    }

    private static String getValidationQuery(String driverName) {
        String query = null;
        try {
            logger.debug("VALIDATION QUERY DRIVER" + driverName);
            ResourceBundle rb = ResourcesManager.getCommonSQLResource();
//            if (rb.containsKey(driverName)) {
            logger.debug("FOUND");
            query = rb.getString(driverName);
//            }
        } catch (Exception e) {
            logger.error("Error al crear el ValidationQuery para el driver " + driverName, e);
        }
        return query;
    }

    /**
     * Imprime el estatus actual del pool de conexiones (conexiones activas /
     * conexiones en espera)
     * @throws java.lang.Exception
     */
    public static void printDriverStats() throws Exception {
        ObjectPool connectionPool = ConnectionFactory.pool;
        logger.trace("Conexiones activas: " + connectionPool.getNumActive());
        logger.trace("Conexiones en espera: " + connectionPool.getNumIdle());
    }

    /**
     * Obtiene una nueva conexion del pool. 
     * @param dbInstance la instancia de la DB a la que hara referencia la conexion
     * Este parametro es la llave del mapa que alberga los objetos del tipo DataSource
     * @return una instancia de Connection
     * @throws java.sql.SQLException
     */
    public static Connection newConnection(String dbInstance) throws SQLException {
        Connection conn = null;
        try {
            switch (CONNECTION_POOL_TYPE) {
                case CommonConstants.CONNECTION_POOL_TYPE_APPLICATION:
                    conn = ConnectionFactory.newConnectionApplicationPool(dbInstance);
                    break;
                case CommonConstants.CONNECTION_POOL_TYPE_CONTAINER:
                    conn = ConnectionFactory.newConnectionContainerPool(dbInstance);
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return conn;
    }

    private static Connection newConnectionApplicationPool(String dbInstance) throws SQLException {
        Connection conn = null;
        try {
            conn = dataSources.get(dbInstance).getConnection();
            ConnectionFactory.printDriverStats();
        } catch (Exception e) {
            logger.fatal("Error al conectarse al pool de la aplicación", e);
        }
        return conn;
    }

    private static Connection newConnectionContainerPool(String dbInstance) throws SQLException {
        Connection conn = null;
        try {
            InitialContext initialContext = new InitialContext();
            logger.debug("Se intenta crear una nueva conexión para la instancia " + dbInstance);
            DataSource dataSource = (javax.sql.DataSource) (initialContext.lookup(dbInstance));
            logger.debug("Se creó el objeto DataSource. Ahora se intenta crear una conexión a partir de ese objeto");
            conn = dataSource.getConnection();
            logger.debug("Instancia de Connection creada correctamente");
        } catch (Exception e) {
            logger.fatal("Error al crear nueva conexión para la instancia " + dbInstance, e);
        }
        return conn;
    }
}
