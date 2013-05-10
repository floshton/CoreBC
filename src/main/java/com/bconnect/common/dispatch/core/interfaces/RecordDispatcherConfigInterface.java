package com.bconnect.common.dispatch.core.interfaces;

import org.apache.commons.pool.impl.GenericObjectPool;

/**
 *
 * @author floshton
 */
public interface RecordDispatcherConfigInterface {

    public GenericObjectPool.Config getPoolConfig();

}
