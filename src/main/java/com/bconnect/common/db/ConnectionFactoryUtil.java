package com.bconnect.common.db;

import com.bconnect.common.db.CommonDataSource;
import com.bconnect.common.db.ConfigurationLoader;
import com.bconnect.common.db.ConnectionFactory;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jorge Rodriguez
 */
public class ConnectionFactoryUtil {

    /**
     *
     * @param includeCatalogo true si se desea incluir la conexión jdbc/catalogo
     * @return un set que contiene los nombres de las conexiones disponibles
     */
    public static Set getAppConnectionNames(boolean includeCatalogo) {
        Set appConnectionNames = null;
        Set connectionNames = ConnectionFactory.getConnectionNames();
        if (connectionNames != null) {
            appConnectionNames = new HashSet(connectionNames);
            if (!includeCatalogo) {
                appConnectionNames.remove(CommonConstants.DB_INFORMIX_CATALOGO);
            }
        }
        return appConnectionNames;
    }

    public static Set getProjectNames(boolean includeCatalogo) {
        Set projectNames = null;
        List<CommonDataSource> commonDataSources = ConfigurationLoader.getDataSources();
        if (commonDataSources.size() > 0) {
            projectNames = new HashSet();
        }

        for (CommonDataSource dataSource : commonDataSources) {
            String project = dataSource.getProject();
            if (CommonUtils.hasValue(project)) {
                projectNames.add(project);
            }
        }
        return projectNames;
    }

    public static String getProjectName(String dbInstance) {
        String projectName = null;
        List<CommonDataSource> commonDataSources = ConfigurationLoader.getDataSources();

        for (CommonDataSource dataSource : commonDataSources) {
            String instanceName = dataSource.getName();
            if (CommonUtils.hasValue(instanceName) && instanceName.equals(dbInstance)) {
                projectName = dataSource.getProject();
            }
        }
        return projectName;
    }

    public static String getDbInstance(String projectName) {
        String dbInstance = null;
        List<CommonDataSource> commonDataSources = ConfigurationLoader.getDataSources();

        for (CommonDataSource dataSource : commonDataSources) {
            String instanceName = dataSource.getProject();
            if (CommonUtils.hasValue(instanceName) && instanceName.equals(projectName)) {
                dbInstance = dataSource.getName();
                break;
            }
        }
        return dbInstance;
    }
}
