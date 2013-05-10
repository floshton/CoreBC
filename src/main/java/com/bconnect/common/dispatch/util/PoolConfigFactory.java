package com.bconnect.common.dispatch.util;

import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author jorge.rodriguez
 */
public class PoolConfigFactory {

    public static GenericObjectPool.Config createConfig(int maxIdle, int maxActive,
            boolean testOnBorrow, boolean testOnReturn, int maxWait) {
        return createConfig(maxIdle, maxActive, false, testOnBorrow, testOnReturn,
                maxWait, GenericObjectPool.WHEN_EXHAUSTED_BLOCK);
    }

    public static GenericObjectPool.Config createDefaultConfig() {
        return createConfig(6, -1, false, true, true,
                5000, GenericObjectPool.WHEN_EXHAUSTED_BLOCK);
    }

    private static GenericObjectPool.Config createConfig(int maxIdle, int maxActive,
            boolean lifo, boolean testOnBorrow, boolean testOnReturn,
            int maxWait, byte whenExhaustedAction) {
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxIdle = maxIdle;
 //       config.lifo = lifo;
        config.maxActive = maxActive; // negativo para no tener limite
        config.testOnBorrow = testOnBorrow;
        config.testOnReturn = testOnReturn;
        config.whenExhaustedAction = whenExhaustedAction;
        config.maxWait = maxWait;
        return config;
    }
}
