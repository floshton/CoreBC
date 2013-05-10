package com.bconnect.common.dispatch.core.interfaces;

import org.apache.commons.pool.PoolableObjectFactory;

/**
 *
 * @author jorge.rodriguez
 */
public interface RecordObjectFactoryInterface extends PoolableObjectFactory {

    RecordDispatcherConfigInterface getConfig();

    public void terminate();
}
