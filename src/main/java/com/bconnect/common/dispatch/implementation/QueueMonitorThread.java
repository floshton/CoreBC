package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.interfaces.RecordInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordRequestInterface;
import com.bconnect.common.logging.CommonLogger;
import java.util.Queue;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * Esta clase es encargada de revisar la cola de solicitudes hechas por los agentes
 * y atenderlas obteniendo registros del pool al que va asignada la solicitud
 * @author floshton
 */
public class QueueMonitorThread implements Runnable {

    private Queue requests;
    private DefaultRecordObjectPool pool;
    private boolean activo;
    private boolean busy;
    private Object request;
    private static int instanceCount;
    private static int recordCount;
    private int instanceNumber;
    private int sleepTime;
    private Logger logger;
    private static final int THREAD_SLEEP_TIME_BASE = 2000;

    static {
        instanceCount = 0;
        recordCount = 0;
    }

    public QueueMonitorThread(DefaultRecordObjectPool pool, Queue requests) {
        this.logger = CommonLogger.getLogger(this.getClass());
        this.requests = requests;
        this.pool = pool;
        this.activo = true;

        this.busy = false;
        this.request = null;
        this.instanceNumber = ++QueueMonitorThread.instanceCount;

        Random generator = new Random();
        this.sleepTime = Math.abs(generator.nextInt(THREAD_SLEEP_TIME_BASE) + 1000);

        logger.debug("Creando " + this + " con sleepTime de " + sleepTime);
    }

    /**
     * Se atiende la solicitud, mediante la obtenci�n de un registro de los disponibles
     * en el pool al que est� asignado este hilo.
     * Ya que es posible que el despachador haya creado instancias vac�as por no
     * encontrar registros en la BD, se obtiene el n�mero de objetos que pueden estar
     * formados en el pool. Este n�mero de veces es el maximo posible de intentos
     * por traer un registro del pool. Si en todas ellas se cuenta con instancias
     * vac�as, se procede a entregar una instancia vac�a. Si por el contrario,
     * en uno de esos intentos el despachador entrega un registro v�lido, esto es,
     * que por venir de la BD s� cuente con ID, �ste es el que se le entrega
     * al agente.
     * @param request
     * @throws Exception
     */
    private void assignRecord(RecordRequestInterface request) throws Exception {
        Object obj = null;
        int maxAttempts = this.pool.getMaxIdle() + 1;

        for (int i = 0; i < maxAttempts; i++) {
            obj = pool.borrowObject();
            RecordInterface record = (RecordInterface) obj;
            if (!record.isEmptyInstance()) {
                break;
            }
        }
        request.setRecord(obj);

        if (obj != null) {
            logger.info("Proporcionando registro #" + recordCount + " ---- " + this + ", " + obj);
            recordCount++;
        }
    }

    public void run() {
        while (activo) {
            try {
                // Si no est� ocupado el hilo
                if (!this.busy) {
                    Object object = requests.poll();
                    if (object != null) {
                        this.busy = true;
                        logger.debug("Atendiendo Request");
                        RecordRequestInterface request = (RecordRequestInterface) object;
                        this.assignRecord(request);
                        this.busy = false;
                    } else {
                        Thread.sleep(this.sleepTime);
                    }
                } else {
                    Thread.sleep(this.sleepTime);
                }
            } catch (Exception e) {
                logger.error("Error durante la ejecuci�n del QueueMonitorThread", e);
            }
        }
    }

    @Override
    public String toString() {
        return "QueueMonitorThread_" + this.instanceNumber;
    }
}
