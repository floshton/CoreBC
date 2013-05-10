package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.interfaces.RecordInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordRequestInterface;
import com.bconnect.common.logging.CommonLogger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author floshton
 */
public class DefaultRecordRequest implements RecordRequestInterface {

    private BlockingQueue<Object> queue;
    private RecordInterface registro;
    private Logger logger;

    public DefaultRecordRequest() {
        this.logger = CommonLogger.getLogger(this.getClass());
        this.queue = new LinkedBlockingQueue<Object>();
    }

    public void setRecord(Object object) {
        try {
            queue.put(object);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            logger.error("Error al agregar el objeto a la Queue", ex);
        } catch (Exception ex) {
            logger.error("Error al agregar el objeto a la Queue", ex);
        }
    }

    public Object getRecord() {
        try {
            this.registro = (RecordInterface) queue.take();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            logger.error("Error al obtener el registro", ex);
        }
        return registro;
    }
}
