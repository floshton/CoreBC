package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.logging.CommonLogger;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 * Esta clase es la encargada de estar monitoreando el pool al que está asociada
 * y en caso de que el tamaño del pool sea menor al requerido, lo llena con nuevos
 * objetos del tipo especificado
 * @author floshton
 */
public class DefaultPoolMonitorThread implements Runnable {

    private GenericObjectPool pool;
    private boolean active;
    private Logger logger;
    private final int SLEEP_TIME = 5000;

    public DefaultPoolMonitorThread(GenericObjectPool pool) {
        this.pool = pool;
        this.active = true;

        this.logger = CommonLogger.getLogger(this.getClass());
    }

    public void run() {
        logger.debug("Inicializando Hilo de monitoreo de tamaño de pool");
        while (active) {
            try {
                int missingCount = pool.getMaxIdle() - pool.getNumIdle();
                this.printIdleStats();
                if (missingCount > 0) {
                    for (int i = missingCount; i > 0; i--) {
                        pool.addObject();
                    }
                }
                Thread.sleep(SLEEP_TIME);
            } catch (IllegalStateException e) {
                logger.error("Error al agregar objeto al pool " + this.pool, e);
                try {
//                    DefaultRecordObjectPool myPool = (DefaultRecordObjectPool) this.pool;
//                    myPool.terminate();
                } catch (Exception ex) {
                    logger.error("Error al cerrar el pool", ex);
                } finally {
                    active = false;
                }
            } catch (InterruptedException ie) {
                logger.info("Se finaliza el hilo de monitoreo para el pool " + this.pool);
            } catch (Exception e) {
                logger.error("Error en la ejecuci[on de hilo de monitoreo de pool" + this.pool, e);
                e.printStackTrace();
            }
        }
        if (!active) {
            logger.info("Se concluye la ejecución del hilo de monitoreo del Pool");
        }
    }

    public void terminate() {
        this.active = false;
    }

    public void initialize() {
        this.active = true;
    }

    private void printIdleStats() {
        //logger.debug("Registros en reposo: " + pool.getNumIdle() + "/" + pool.getMaxIdle());
    }

    public void setActive(boolean active) {
        logger.info("Se cambia a " + active + " el status del DefaultMonitorThread");
        this.active = active;
    }
}
