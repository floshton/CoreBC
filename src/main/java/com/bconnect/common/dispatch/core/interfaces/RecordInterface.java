package com.bconnect.common.dispatch.core.interfaces;

/**
 *
 * @author floshton
 */
public interface RecordInterface {

    public void setPool(RecordObjectPoolInterface pool);

    public RecordObjectPoolInterface getPool();

    public void returnToPool() throws Exception;

    public boolean isEmptyInstance();
}
