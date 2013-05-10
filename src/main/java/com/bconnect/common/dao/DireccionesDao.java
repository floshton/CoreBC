package com.bconnect.common.dao;

import com.bconnect.common.exception.DBException;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class DireccionesDao extends BaseDao {

    DireccionesDBHelper dbHelper;

    public DireccionesDao(String dbInstance) {
        super(dbInstance);
    }

    public DireccionesDao() {
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
        dbHelper = new DireccionesDBHelper(dbInstance);
    }

    public String findEstadoByCp(String cp) {

        String estado = null;

        try {

            estado = dbHelper.findEstadoByCp(cp);

        } catch (DBException dbe) {

            logger.error("Ocurrio un error al traer el estado por cp");

            dbe.printStackTrace();

        }

        return estado;

    }

    public List findEstados() {
        List estados = null;

        try {
            estados = dbHelper.findEstados();
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de estados");
            dbe.printStackTrace();
        }
        return estados;
    }

    public List findMunicipiosByEstado(String idEstado) {
        List municipios = null;

        try {
            municipios = dbHelper.findMunicipiosByEstado(idEstado);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traes la lista de municipios");
            dbe.printStackTrace();
        }
        return municipios;
    }

    public List findCiudades() {
        List municipios = null;
        try {
            municipios = dbHelper.findCiudades();
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traes la lista de ciudades");
            dbe.printStackTrace();
        }
        return municipios;
    }

    public List findColoniasByMunicipio(String idMunicipio) {
        List colonias = null;

        try {
            colonias = dbHelper.findColoniasByMunicipio(idMunicipio);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traes la lista de colonias");
            dbe.printStackTrace();
        }
        return colonias;
    }

    public String findCpByColonia(String idColonia) {
        String cp = null;

        try {
            cp = dbHelper.findCpByColonia(idColonia);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traes la lista de colonias");
            dbe.printStackTrace();
        }
        return cp;
    }

    public List findUbicacionesByCp(String cp) {
        List ubicaciones = null;
        try {
            ubicaciones = dbHelper.findUbicacionesByCp(cp);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer las ubicaciones por cp");
            dbe.printStackTrace();
        }
        return ubicaciones;
    }

    public List findMunicipiosByStrEstado(String strEstado) {
        List municipios = null;

        try {
            municipios = dbHelper.findMunicipiosByStrEstado(strEstado);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traes la lista de municipios");
            dbe.printStackTrace();
        }
        return municipios;
    }
}
