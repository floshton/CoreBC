package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.interfaces.RecordRequestInterface;
import com.bconnect.common.logging.CommonLogger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class DispatchersManager {

    private static Map dispatchers;
    private static boolean initialized;
    private static Logger logger;

    static {
        logger = CommonLogger.getLogger(DispatchersManager.class);
    }

    public static void initializeDispatchers(Map dispatchers) {
        if (!initialized) {
            DispatchersManager.dispatchers = dispatchers;
            initialized = true;
        } else {
            logger.info("Attempted to initialize dispatchers when they are already running");
        }
    }

    public static void finalizeDispatchers() {
        if (initialized) {
            logger.info("Terminating dispatchers...");
            Iterator itr = dispatchers.values().iterator();
            while (itr.hasNext()) {
                DefaultRecordDispatcher dispatcher = (DefaultRecordDispatcher) itr.next();
                dispatcher.terminate();
            }
            initialized = false;
        }
    }

    public static synchronized void addRequest(RecordRequestInterface request, String projectCode) {
        try {
            DefaultRecordDispatcher despachador =
                    (DefaultRecordDispatcher) DispatchersManager.dispatchers.get(projectCode);
            if (despachador != null) {
                despachador.addRequest(request);
            }
        } catch (Exception e) {
            logger.error("Error when submitting a new Record Request", e);
        }
    }

    public static Collection getDispatchers() {
        return dispatchers.values();
    }
}
