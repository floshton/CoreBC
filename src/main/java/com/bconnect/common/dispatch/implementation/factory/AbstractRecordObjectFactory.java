package com.bconnect.common.dispatch.implementation.factory;

import com.bconnect.common.dispatch.core.interfaces.RecordDispatcherConfigInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordObjectFactoryInterface;
import com.bconnect.common.dispatch.implementation.DefaultRecordDispatcherConfig;
import com.bconnect.common.logging.CommonLogger;
import org.apache.log4j.Logger;

/**
 * Esta clase la usa el pool para crear, validar y destruir los registros que
 * pone en el pool
 * @author Jorge Rodriguez
 */
public abstract class AbstractRecordObjectFactory
        implements RecordObjectFactoryInterface {

    protected static int objectCount;
    protected static Logger logger;
    protected RecordDispatcherConfigInterface dispatcherConfig;

    static {
        AbstractRecordObjectFactory.objectCount = 0;
        AbstractRecordObjectFactory.logger = CommonLogger.getLogger(AbstractRecordObjectFactory.class);
    }

    public AbstractRecordObjectFactory(RecordDispatcherConfigInterface dispatcherConfig) {
        this.dispatcherConfig = dispatcherConfig;
    }

    public AbstractRecordObjectFactory() {
    }

    public RecordDispatcherConfigInterface getConfig() {
        return this.dispatcherConfig;
    }

    public void setConfig(DefaultRecordDispatcherConfig config) {
        this.dispatcherConfig = config;
    }

    public Object makeObject() {
        Object obj = this.createNewInstance();

        if (obj == null) {
            obj = this.createEmptyInstance();
        }
        return obj;
    }

    public abstract Object createNewInstance();

    public abstract Object createEmptyInstance();

    public abstract void destroyObject(Object obj);

    public abstract boolean validateObject(Object obj);

    public abstract void activateObject(Object obj);

    /**
     * Desinicializa el objeto. Dado que el pool automáticamente llama a este
     * método al agregar un objeto al pool, verificamos que ya haya sido calificado
     * para poder pasivarlo. De lo contrario, no se hace nada, de tal forma que
     * no se altere el estado del bitReservado, y evitar que otro hilo lo tome.
     * @param objeto
     */
    public abstract void passivateObject(Object objeto);

    public void terminate() {
        logger.info("Finalizando RecordFactory " + this);
    }
}
