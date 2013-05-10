package com.bconnect.common.dao;

import com.bconnect.common.bean.EmpresaBean;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class EmpresaDao extends BaseDao {

    EmpresaDBHelper dbHelper;

    public EmpresaDao(String dbInstance) {
        super(dbInstance);
    }

    public EmpresaDao() {
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

    @Override
    protected void initializeDBHelper() {
        dbHelper = new EmpresaDBHelper(dbInstance);
    }

    public List findEmpresas() {
        List mensajerias = null;

        try {
            mensajerias = dbHelper.findEmpresas();
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de mensajerias", dbe);
        }
        return mensajerias;
    }

    public List findEmpresasByGiro(String giro) {
        List mensajerias = null;

        try {
            mensajerias = dbHelper.findEmpresasByGiro(giro);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de mensajerias", dbe);
        }
        return mensajerias;
    }

    public EmpresaBean findEmpresaById(int id) {
        EmpresaBean empresa = null;
        try {
            empresa = dbHelper.findEmpresaById(id);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la empresa con id " + id, dbe);
        }
        return empresa;
    }
}
