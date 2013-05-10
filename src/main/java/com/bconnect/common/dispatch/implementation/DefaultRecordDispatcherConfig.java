package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.interfaces.RecordDispatcherConfigInterface;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author floshton
 */
public class DefaultRecordDispatcherConfig implements RecordDispatcherConfigInterface {

    protected int index;
    protected String projectCode;
    protected GenericObjectPool.Config config;

    public DefaultRecordDispatcherConfig(String projectCode) {
        this.projectCode = projectCode;
        this.config = this.getDefaultConfig();
    }

    public DefaultRecordDispatcherConfig() {
        this.config = this.getDefaultConfig();
    }

    public DefaultRecordDispatcherConfig(String projectCode, GenericObjectPool.Config config) {
        this.projectCode = projectCode;
        this.config = config;
    }

    public DefaultRecordDispatcherConfig(GenericObjectPool.Config config) {
        this.config = config;
    }

    protected GenericObjectPool.Config getDefaultConfig() {
        config = new GenericObjectPool.Config();
        // máximos en reposo
        config.maxIdle = 6;
        //config.lifo = false;
        config.maxActive = -1; // negativo para no tener limite
        //config.minEvictableIdleTimeMillis = 30000; // cada cuando se revisa la velidez del registro
        config.testOnBorrow = true;
        config.testOnReturn = true;
        // When whenExhaustedAction is WHEN_EXHAUSTED_BLOCK, borrowObject() will block
        // (invoke Object.wait()) until a new or idle object is available.
        // If a positive maxWait value is supplied, then borrowObject() will block for
        // at most that many milliseconds, after which a NoSuchElementException will be thrown.
        // If maxWait is non-positive, the borrowObject() method will block indefinitely.
        config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
        config.maxWait = 2000;

        return config;
    }

    /**
     * Las propiedades de configuración de tamaños del pool
     * @return
     */
    public GenericObjectPool.Config getPoolConfig() {
        return this.config;
    }

    public String getProjectCode() {
        return projectCode;
    }
}
