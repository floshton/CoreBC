package com.bconnect.common.web.controller.jsf;

import com.bconnect.common.dispatch.implementation.DispatchersManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class DispatcherManagerController implements Serializable {

    protected List dispatchers;

    public DispatcherManagerController() {
    }

    public List getActiveDispatchers() {
        if (dispatchers == null) {
            dispatchers = new ArrayList();
            Iterator dispatchersIterator = DispatchersManager.getDispatchers().iterator();
            while (dispatchersIterator.hasNext()) {
                dispatchers.add(dispatchersIterator.next());
            }
        }
        return dispatchers;
    }
}
