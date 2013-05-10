package com.bconnect.common.dispatch.implementation;

import com.bconnect.common.dispatch.core.interfaces.RecordDispatcherConfigInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordObjectFactoryInterface;
import com.bconnect.common.dispatch.core.interfaces.RecordRequestInterface;
import com.bconnect.common.logging.CommonLogger;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author floshton
 */
public class DefaultRecordDispatcher {

    protected Queue requests;
    protected DefaultRecordObjectPool pool;
    protected ThreadGroup requestMonitorThreads;
    protected static int MONITOR_THREAD_COUNT;
    protected static int MIN_THREAD_COUNT = 1;
    protected static int DEFAULT_RECORD_COUNT_PER_THREAD = 8;
    protected DefaultRecordDispatcherConfig dispatcherConfig;
    protected Logger logger;
    protected RecordObjectFactoryInterface recordFactory;
    protected String nombre;

    public DefaultRecordDispatcher(RecordObjectFactoryInterface objectFactory) {
        this.logger = CommonLogger.getLogger(this.getClass());
        this.recordFactory = objectFactory;

        initializeDispatcherConfig(objectFactory);

        try {
            pool = new DefaultRecordObjectPool(objectFactory);
            requests = new ConcurrentLinkedQueue();
            initializeMonitorThreads(getMonitorThreadCount());
        } catch (Exception e) {
            logger.fatal("Error initializing dispatcher " + this, e);
        }
    }

    public DefaultRecordDispatcher(RecordObjectFactoryInterface objectFactory, String nombre) {
        this(objectFactory);
        this.nombre = nombre;
    }

    private void initializeDispatcherConfig(RecordObjectFactoryInterface objectFactory) {
        if (objectFactory.getConfig() instanceof RecordDispatcherConfigInterface) {
            this.dispatcherConfig = (DefaultRecordDispatcherConfig) objectFactory.getConfig();
        }
        logger.info("Initializing dispatcher for project Code " + this.dispatcherConfig.getProjectCode());
    }

    private int getMonitorThreadCount() {
        int maxIdle = this.dispatcherConfig.getPoolConfig().maxIdle;
        MONITOR_THREAD_COUNT = maxIdle > DEFAULT_RECORD_COUNT_PER_THREAD
                ? maxIdle / DEFAULT_RECORD_COUNT_PER_THREAD : MIN_THREAD_COUNT;
        return MONITOR_THREAD_COUNT;
    }

    private void initializeMonitorThreads(int monitorThreadCount) {
        logger.debug("Initializing Request monitor threads...");
        requestMonitorThreads = new ThreadGroup("RequestMonitorThreads");
        for (int i = 0; i < monitorThreadCount; i++) {
            new Thread(requestMonitorThreads, new QueueMonitorThread(pool, requests), "THREAD_" + i).start();
        }
    }

    public synchronized void addRequest(RecordRequestInterface request) {
        requests.add(request);
    }

    public DefaultRecordDispatcherConfig getConfig() {
        return this.dispatcherConfig;
    }

    public void terminate() {
        pool.terminate();
        recordFactory.terminate();
    }

    public DefaultRecordObjectPool getPool() {
        return pool;
    }

    @Override
    public String toString() {
        return "Dispatcher_" + this.dispatcherConfig.getProjectCode();
    }

    public void setPoolMaxIdle(int maxIdle) {
        logger.debug("Nuevo valor maxIdle = " + maxIdle + " para el despachador " + this);
        pool.setMaxIdle(maxIdle);
    }

    public void setPoolMinIdle(int minIdle) {
        logger.debug("Nuevo valor minIdle = " + minIdle + " para el despachador " + this);
        pool.setMinIdle(minIdle);
    }

    public void setPoolMaxActive(int maxActive) {
        logger.debug("Nuevo valor maxActive = " + maxActive + " para el despachador " + this);
        pool.setMaxActive(maxActive);
    }

    public void setPoolMaxWait(long maxWait) {
        logger.debug("Nuevo valor maxWait = " + maxWait + " para el despachador " + this);
        pool.setMaxWait(maxWait);
    }

    public void setPoolNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        logger.debug("Nuevo valor numTestsPerEvictionRun = " + numTestsPerEvictionRun
                + " para el despachador " + this);
        pool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
    }

    public int getPoolMaxIdle() {
        return pool.getMaxIdle();
    }

    public int getPoolMinIdle() {
        return pool.getMinIdle();
    }

    public int getPoolNumIdle() {
        return pool.getNumIdle();
    }

    public int getPoolMaxActive() {
        return pool.getMaxActive();
    }

    public long getPoolMaxWait() {
        return pool.getMaxWait();
    }

    public int getPoolNumActive() {
        return pool.getNumActive();
    }

    public int getPoolNumTestsPerEvictionRun() {
        return pool.getNumTestsPerEvictionRun();
    }

    public String getNombre() {
        return nombre;
    }

    public void clearPool() {
        logger.info("Depurando el pool del despachador " + this);
        pool.clear();
    }
}
