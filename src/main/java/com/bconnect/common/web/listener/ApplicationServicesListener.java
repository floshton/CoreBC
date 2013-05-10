package com.bconnect.common.web.listener;

import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author jorge.rodriguez
 */
public class ApplicationServicesListener extends ServicesListener implements ServletContextListener {

    @Override
    protected void initializeApplicationServices(ServletContext context) {
        System.out.println("Initializing BCONNECT CORE SERVICES");
        super.initializeResources();
        super.initializeLogger(context);

        String nombreAplicacion = ResourcesManager.getApplicationResource().getString(
                CommonConstants.PROJECT_NAME);

        logger = CommonLogger.getLogger(this.getClass());
        logger.info("< ======================================================= >");
        logger.info("Aplicación " + nombreAplicacion + " inicializando...");
        logger.info("Recursos de la aplicación inicializados exitosamente");
        logger.info("Logger inicializado exitosamente.");

        super.initializeConnectionFactory(context);

        super.initializeDate(context);
    }
}
