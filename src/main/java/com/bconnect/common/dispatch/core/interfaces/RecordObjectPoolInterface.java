package com.bconnect.common.dispatch.core.interfaces;

/**
 *
 * @author floshton
 */
public interface RecordObjectPoolInterface {

    public void initialize() throws Exception;

    public void terminate() throws Exception;

    public void suspend() throws Exception;

    public void returnObject(Object obj) throws Exception;
}
