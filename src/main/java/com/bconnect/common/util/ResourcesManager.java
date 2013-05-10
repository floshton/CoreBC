package com.bconnect.common.util;

/**
 * Esta clase incluye metodos estaticos para obtener la instancia de la aplicacion
 * que contiene los recursos tanto SQL como de la aplicacion en general
 * @author Jorge Rodriguez
 */
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Esta clase se usa para accesar estaticamente a los recursos de la aplicacion,
 * mediante propiedades estáticas
 */
public class ResourcesManager {

    /**
     * Esta propiedad está asociada con el archivo properties que contiene
     * los recursos generales de la aplicación
     */
    private static ResourceBundle baseResources;

    /**
     * Constructorr
     */
    public ResourcesManager() {
    }

    public static void initializeResources() {
        baseResources = ResourceBundle.getBundle(CommonConstants.BASE_RESOURCE_FILE);
    }

    public static ResourceBundle getResource(String bundle) {
        return ResourceBundle.getBundle(baseResources.getString(bundle));
    }

    public static ResourceBundle getApplicationResource() {
        return ResourcesManager.getResource(CommonConstants.APPLICATION_RESOURCE_FILE);
    }

    public static ResourceBundle getBaseResource() {
        return baseResources;
    }

    public static ResourceBundle getMailResource() {
        return ResourcesManager.getResource(CommonConstants.MAIL_RESOURCE_FILE);
    }

    public static ResourceBundle getCommonSQLResource() {
        return ResourcesManager.getResource(CommonConstants.COMMON_SQL_RESOURCE_FILE);
    }

    public static ResourceBundle getSQLResource() {
        return ResourcesManager.getResource(CommonConstants.SQL_RESOURCE_FILE);
    }

    public static ResourceBundle getLog4JResource() {
        ResourceBundle log4JResource = null;
        try {
            log4JResource = ResourcesManager.getResource(CommonConstants.CUSTOM_LOG4J_RESOURCE_FILE);
        } catch (MissingResourceException mre) {
            log4JResource = ResourcesManager.getResource(CommonConstants.LOG4J_RESOURCE_FILE);
        }
        return log4JResource;
    }

    public static ResourceBundle getStrutsErrorsResource() {
        return ResourcesManager.getResource(CommonConstants.STRUTS_ERRORS_RESOURCE_FILE);
    }

    public static ResourceBundle getStrutsApplicationErrorsResource() {
        return ResourcesManager.getResource(CommonConstants.STRUTS_APPLICATION_ERRORS_RESOURCE_FILE);
    }
}
