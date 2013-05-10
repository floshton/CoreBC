package com.bconnect.common.dispatch.core.interfaces;

/**
 *
 * @author floshton
 */
public interface RecordDispatcherInterface {

    public void addRequest(RecordRequestInterface request);

    public RecordDispatcherConfigInterface getConfig();

    public void terminate();
}
