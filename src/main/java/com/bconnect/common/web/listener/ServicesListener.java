package com.bconnect.common.web.listener;

import com.bconnect.common.db.ConnectionFactory;
import com.bconnect.common.util.ResourcesManager;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.mail.DefaultMailManager;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.net.SyslogAppender;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Esta clase implementa ServletContextListener y se usa para inicializar o 
 * terminar oportunamente los servicios internos de la aplicacion.
 * Tales servicios incluyen:
 * <ul>
 * <li>Logger de la aplicación</li>
 * <li>Pool de conexiones para las DB utilizadas</li>
 * <li>Carga y lectura de archivos de configuración</li><li></li>
 * </ul>
 * @author Jorge Rodriguez
 */
public class ServicesListener implements ServletContextListener {

    protected Logger logger;

    /** Creates a new instance of ServicesListener */
    public ServicesListener() {
    }
    
    protected void initializeApplicationServices(ServletContext context) {
        try {
            this.initializeResources();
            this.initializeLogger(context);

            String nombreAplicacion = ResourcesManager.getApplicationResource().getString(
                    CommonConstants.PROJECT_NAME);

            logger = CommonLogger.getLogger(this.getClass());
            logger.info("< ======================================================= >");
            logger.info("Aplicación " + nombreAplicacion + " inicializando...");
            logger.info("Recursos de la aplicación inicializados exitosamente");
            logger.info("Logger inicializado exitosamente.");

            this.initializeConnectionFactory(context);

            this.initializeMailManager();
            logger.info("MailManager inicializado exitosamente");

            this.initializeDate(context);
            logger.debug("El atributo de fecha de inicializó correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa los servicios necesarios en la aplicacion
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.initializeApplicationServices(servletContextEvent.getServletContext());
    }

    /**
     * Inicializa el Logger, asignando un nombre de archivo
     */
    protected void initializeLogger(ServletContext context) {
        URL url = null;
        try {
            url = Loader.getResource(CommonConstants.LOG4J_XML_RESOURCE_FILE);
            if (url == null) {
                url = Loader.getResource(CommonConstants.LOG4J_XML_DEFAULT_RESOURCE_FILE);
            }
        } catch (Exception e) {
            url = Loader.getResource(CommonConstants.LOG4J_XML_DEFAULT_RESOURCE_FILE);
        }
        DOMConfigurator.configure(url);

        Enumeration appenders = Logger.getRootLogger().getAllAppenders();
        while (appenders.hasMoreElements()) {
            Appender theAppender = (Appender) appenders.nextElement();
            try {
                if (theAppender instanceof FileAppender) {
                    FileAppender fileAppender = (FileAppender) theAppender;
                    fileAppender.setFile(context.getRealPath("../../logs/"
                            + ResourcesManager.getApplicationResource().getString(
                            CommonConstants.PROJECT_LOG_FILE_NAME)
                            + "/" + fileAppender.getFile()));
                    fileAppender.activateOptions();
                } else if (theAppender instanceof SyslogAppender) {
                    SyslogAppender sysLogAppender = (SyslogAppender) theAppender;
                    sysLogAppender.activateOptions();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error en cast de appender");
            }
        }
    }

    /**
     * Inicializa los recursos (archivos properties) de la aplicacion para ser utilizados
     */
    protected void initializeResources() {
        ResourcesManager.initializeResources();

    }

    protected void initializeMailManager() {
        DefaultMailManager.initialize();
    }

    /**
     * Inicializa el ConnectionFactory para poder procesar
     * todos los requests a BD
     */
    protected void initializeConnectionFactory(ServletContext context) {
        ConnectionFactory.initialize(context);
    }

    protected void initializeDate(ServletContext context) {
        Date currentDate = DateUtil.getSystemDate();
        context.setAttribute(CommonConstants.ATRIBUTO_FECHA_ACTUAL, currentDate);
    }

    /**
     * Implemented by ServletContextListener
     * @param servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("La aplicación ha sido finalizada");
        logger.info("< ======================================================= >");
    }
}
