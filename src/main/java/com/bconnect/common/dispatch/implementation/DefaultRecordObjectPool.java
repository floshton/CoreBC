package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.exception.DispatchException;
import com.bconnect.common.dispatch.core.interfaces.RecordInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordObjectFactoryInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordObjectPoolInterface;
import com.bconnect.common.logging.CommonLogger;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 *
 * @author floshton
 */
public class DefaultRecordObjectPool extends GenericObjectPool
        implements RecordObjectPoolInterface {

    // Thread que envuelve a la clase del hilo
    private Thread poolSizeMonitorThread;
    // La clase del hilo
    private DefaultPoolMonitorThread poolSizeMonitorRunnable;
    // factory trae métodos de creacion y validacion de registros para ponerlos en el pool
    private RecordObjectFactoryInterface factory;
    private GenericObjectPool.Config poolConfig;
    private DefaultRecordDispatcherConfig dispatcherConfig;
    private Logger logger;

    public DefaultRecordObjectPool(RecordObjectFactoryInterface objFactory)
            throws DispatchException {
        super(objFactory, objFactory.getConfig().getPoolConfig());

        this.dispatcherConfig = (DefaultRecordDispatcherConfig) objFactory.getConfig();
        this.poolConfig = dispatcherConfig.getPoolConfig();

        logger = CommonLogger.getLogger(this.getClass());
        logger.debug("Se intenta instanciar objeto DefaultRecordObjectPool");

        if (!this.isClosed()) {
            this.initializePoolMonitorThreads();
        } else {
            throw new DispatchException("El pool no se encuentra abierto. "
                    + "Falló intento de crear Hilos de Monitoreo de Pool");
        }
    }

    private void initializePoolMonitorThreads() {
        try {
            poolSizeMonitorRunnable = new DefaultPoolMonitorThread(this);
            poolSizeMonitorThread = new Thread(poolSizeMonitorRunnable);
            poolSizeMonitorThread.start();
            logger.info("Creación de instancia de DefaultRecordObjectPool Completada.");
        } catch (Exception e) {
            logger.fatal("Se prodjo un error al momento de iniciar los hilos de monitoreo del pool", e);
        }
    }

    private void terminatePoolMonitorThreads() {
        try {
            logger.debug("Terminando hilos de monitoreo de pool");
            this.poolSizeMonitorRunnable.terminate();
        } catch (Exception e) {
            logger.error("Se prodjo un error al momento de terminar los hilos de monitoreo del pool", e);
        }
    }

    @Override
    public Object borrowObject() throws Exception {
        logger.debug("Pidiendo registro del pool...");
        Object obj = null;
        try {
            obj = super.borrowObject();
            logger.debug("Entregando registro " + obj);
            if (obj instanceof RecordInterface) {
                ((RecordInterface) obj).setPool(this);
            }
        } catch (Exception e) {
            logger.error("Error al entregar registro del pool", e);
        }
        return obj;
    }

    @Override
    public void returnObject(Object obj) throws Exception {
        logger.debug("Se intenta regresar al pool el registro " + obj);
        super.returnObject(obj);
        obj = null;
    }

    @Override
    public synchronized void clear() {
        logger.debug("Se intenta limpiar el pool");
        if (!this.isClosed()) {
            super.clear();
        }
    }

    public void initialize() {
    }

    public void suspend() throws Exception {
        this.close();
    }

    public void terminate() {
        try {
            logger.info("Terminando pool");
            this.terminatePoolMonitorThreads();
            this.close();
        } catch (Exception e) {
            logger.error("Error al terminar el despachador " + this, e);
        }
    }
}
