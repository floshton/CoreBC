package com.bconnect.common.dao;

import com.bconnect.common.exception.DBException;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import com.bconnect.common.util.ResourcesManager;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class CommonQueriesDao extends BaseDao {

    private CommonQueriesDBHelper dbHelper;

    public CommonQueriesDao(String dbInstance) {
        super(dbInstance);
    }

    public CommonQueriesDao() {
        logger = CommonLogger.getLogger(this.getClass());
        String jndiConnectionName = null;
        try {
            jndiConnectionName = ResourcesManager.getApplicationResource().getString(CommonConstants.PROJECT_CATALOGO_DB_CONN_JNDI_NAME);
        } catch (Exception e) {
            jndiConnectionName = CommonConstants.DB_INFORMIX_CATALOGO;
        }
        this.dbInstance = jndiConnectionName;
        this.initializeDBHelper();
    }

    protected void initializeDBHelper() {
        dbHelper = new CommonQueriesDBHelper(dbInstance);
    }

    public boolean esDiaFeriado(java.util.Date date) {
        boolean esDiaFeriado = false;
        try {
            String dia = DateUtil.formatDate(date, "dd/MM/yyyy");
            esDiaFeriado = dbHelper.esDiaFeriado(dia);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de agencias");
            dbe.printStackTrace();
        }
        return esDiaFeriado;
    }

    public boolean esDiaFeriado(Calendar calendar) {
        java.util.Date date = calendar.getTime();
        return this.esDiaFeriado(date);
    }

    public Collection findCatalogoById(int idCatalogo) {
        List catalogo = null;
        try {
            catalogo = dbHelper.findCatalogoById(idCatalogo);
        } catch (DBException dbe) {
            logger.error("Ocurrió un error al traer el catálogo");
            dbe.printStackTrace();
        }
        return catalogo;
    }

    public Collection findCatalogoOcupaciones() {
        List catalogo = null;
        try {
            catalogo = dbHelper.findCatalogoById(CommonConstants.ID_CATALOGO_OCUPACIONES);
        } catch (DBException dbe) {
            logger.error("Ocurrió un error al traer el catálogo de ocupaciones");
            dbe.printStackTrace();
        }
        return catalogo;
    }

    public Collection findCatalogoPaises() {
        List catalogo = null;
        try {
            catalogo = dbHelper.findCatalogoById(CommonConstants.ID_CATALOGO_PAISES);
        } catch (DBException dbe) {
            logger.error("Ocurrió un error al traer el catálogo de paises");
            dbe.printStackTrace();
        }
        return catalogo;
    }
}
