package com.bconnect.common.dispatch.implementation.factory;

import java.util.List;
import java.util.Queue;

/**
 *
 * @author jorge.rodriguez
 */
public abstract class AbstractRecordObjectQueueFactory extends AbstractRecordObjectFactory {

    protected Queue instances;

    protected Object getNextInstance() {
        if (instances.isEmpty()) {
            repopulateInstances();
        }
        return instances.poll();
    }

    private void repopulateInstances() {
        instances.addAll(retrieveNewInstances());
    }

    protected abstract List retrieveNewInstances();

    @Override
    public Object createNewInstance() {
        return getNextInstance();
    }

    @Override
    public void terminate() {
        super.terminate();
        this.purgeInstances();
    }

    private void purgeInstances() {
        while (!instances.isEmpty()) {
            Object instance = instances.poll();
            if (instance != null) {
                this.destroyObject(instance);
            }
        }
    }
}
