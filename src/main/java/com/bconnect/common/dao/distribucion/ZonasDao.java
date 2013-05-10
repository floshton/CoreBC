package com.bconnect.common.dao.distribucion;

import com.bconnect.common.bean.distribucion.ZonaDistribucionBean;
import com.bconnect.common.dao.BaseDao;
import com.bconnect.common.exception.DBException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge Rodriguez
 */
public class ZonasDao extends BaseDao {

    ZonasDBHelper dbHelper;

    public ZonasDao(String dbInstance) {
        super(dbInstance);
    }

    public ZonasDao() {
        super("jdbc/nextel_meg");
    }

    @Override
    protected void initializeDBHelper() {
        dbHelper = new ZonasDBHelper(dbInstance);
    }

    public int insertZona(ZonaDistribucionBean zona, String creadoPor) {
        int idZona = 0;

        try {
            idZona = dbHelper.insertZona(zona.getIdEstado(), zona.getNombreEstado(),
                    zona.getIdMunicipio(), zona.getNombreMunicipio(),
                    zona.getIdMensajeria(),
                    zona.getNombreMensajeria(), zona.isLunes(), zona.isMartes(),
                    zona.isMiercoles(), zona.isJueves(), zona.isViernes(),
                    zona.isSabado(), zona.isDomingo(), Boolean.TRUE, creadoPor);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al insertar la zona de distribucion");
            dbe.printStackTrace();
        }
        return idZona;
    }

    public ZonaDistribucionBean findZonaById(long idZona) {
        ZonaDistribucionBean zona = null;

        try {
            zona = dbHelper.findZonaById(idZona);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la zona de distribución");
            dbe.printStackTrace();
        }
        return zona;
    }

    public List findZonasByIdEstado(String idEstado) {
        List zonas = null;

        try {
            zonas = dbHelper.findZonasByIdEstado(idEstado);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de zonas de distribución");
            dbe.printStackTrace();
        }
        return zonas;
    }

    public List findZonasByIdEstadoMunicipio(String idEstado, String idMunicipio) {
        List zonas = null;

        try {
            zonas = dbHelper.findZonasByIdEstadoMunicipio(idEstado, idMunicipio);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de zonas de distribución");
            dbe.printStackTrace();
        }
        return zonas;
    }

    public boolean deleteZona(long idZona) {
        boolean success = false;

        try {
            success = dbHelper.deleteZona(idZona);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de zonas de distribución");
            dbe.printStackTrace();
        }
        return success;
    }

    public int updateZona(ZonaDistribucionBean zona) {
        int idZona = 0;

        try {
            idZona = dbHelper.updateZona(zona.getIdZona(), zona.getIdEstado(),
                    zona.getNombreEstado(), zona.getIdMunicipio(), zona.getNombreMunicipio(),
                    zona.getIdMensajeria(), zona.getNombreMensajeria(),
                    zona.isLunes(), zona.isMartes(), zona.isMiercoles(),
                    zona.isJueves(), zona.isViernes(), zona.isSabado(),
                    zona.isDomingo(), zona.getCosto(), zona.getTiempoEntrega());
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al actualizar la zona de distribución");
            dbe.printStackTrace();
        }


        return idZona;
    }

    public Map<String, String> validateZonaDias(ZonaDistribucionBean zona) {
        Map output = null;

        try {
            output = dbHelper.validateZonaDias(zona.getIdEstado(), zona.getIdMunicipio(),
                    zona.getIdMensajeria(), zona.isLunes(), zona.isMartes(), zona.isMiercoles(),
                    zona.isJueves(), zona.isViernes(), zona.isSabado(),
                    zona.isDomingo());
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al momento de validar la zona de distribución");
            dbe.printStackTrace();
        }
        return output;
    }

    public boolean validateExisteMunicipio(ZonaDistribucionBean zona) {
        boolean existe = false;

        try {
            existe = dbHelper.validateExisteMunicipio(zona.getIdEstado(), zona.getIdMunicipio());
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al momento de validar la zona de distribución");
            dbe.printStackTrace();
        }
        return existe;
    }
}
