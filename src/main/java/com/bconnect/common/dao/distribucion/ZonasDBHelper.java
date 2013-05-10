package com.bconnect.common.dao.distribucion;

import com.bconnect.common.bean.distribucion.ZonaDistribucionBean;
import com.bconnect.common.dao.BaseDBHelper;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.db.util.CommonCallableStatement;
import com.bconnect.common.db.util.CommonPreparedStatement;
import com.bconnect.common.db.util.CommonResultSet;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jorge Rodriguez
 */
public class ZonasDBHelper extends BaseDBHelper {

    public ZonasDBHelper(String dbInstance) {
        super(dbInstance);
    }

    public int insertZona(String idEstado, String nombreEstado,
            String idMunicipio, String nombreMunicipio, String idMensajeria,
            String nombreMensajeria, boolean lunes, boolean martes, boolean miercoles,
            boolean jueves, boolean viernes, boolean sabado, boolean domingo,
            boolean activo, String creadoPor) throws DBException {

        int idZona = 0;

        ResultSet resultSet = null;
        CommonResultSet rs = null;
        CommonCallableStatement cs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            cs = dbConnection.getCommonCallableStatement(
                    commonResource.getString(CommonConstants.SP_ZONA_DISTRIBUCION_INSERT));

            int i = 1;
            cs.setString(i++, idEstado);
            cs.setString(i++, nombreEstado);
            cs.setString(i++, idMunicipio);
            cs.setString(i++, nombreMunicipio);
            cs.setString(i++, idMensajeria);
            cs.setString(i++, nombreMensajeria);
            cs.setBit(i++, lunes);
            cs.setBit(i++, martes);
            cs.setBit(i++, miercoles);
            cs.setBit(i++, jueves);
            cs.setBit(i++, viernes);
            cs.setBit(i++, sabado);
            cs.setBit(i++, domingo);
            cs.setBit(i++, activo);
            cs.setString(i++, creadoPor);

            resultSet = cs.executeQuery();
            rs = new CommonResultSet(resultSet);

            if (rs.next()) {
                idZona = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            logger.error("Se produjo un error al momento de insertar la zona de distribución", sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return idZona;
    }

    public int updateZona(long idZona, String idEstado, String nombreEstado,
            String idMunicipio, String nombreMunicipio, String idMensajeria,
            String nombreMensajeria, boolean lunes, boolean martes, boolean miercoles,
            boolean jueves, boolean viernes, boolean sabado, boolean domingo,
            String costo, String tiempoEntrega) throws DBException {

        int idZonaUpdate = 0;

        CommonCallableStatement cs = null;
        CommonResultSet rs = null;
        ResultSet resultSet = null;

        DBConnection dbConnection = this.getConnection();

        try {
            cs = dbConnection.getCommonCallableStatement(
                    commonResource.getString(CommonConstants.SP_ZONA_DISTRIBUCION_UPDATE));

            int i = 1;
            cs.setLong(i++, idZona);
            cs.setString(i++, idEstado);
            cs.setString(i++, nombreEstado);
            cs.setString(i++, idMunicipio);
            cs.setString(i++, nombreMunicipio);
            cs.setString(i++, idMensajeria);
            cs.setString(i++, nombreMensajeria);
            cs.setBit(i++, lunes);
            cs.setBit(i++, martes);
            cs.setBit(i++, miercoles);
            cs.setBit(i++, jueves);
            cs.setBit(i++, viernes);
            cs.setBit(i++, sabado);
            cs.setBit(i++, domingo);
            cs.setString(i++, costo);
            cs.setString(i++, tiempoEntrega);

            resultSet = cs.executeQuery();
            rs = new CommonResultSet(resultSet);

            if (rs.next()) {
                idZonaUpdate = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            logger.error("Se produjo un error al momento de insertar la zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return idZonaUpdate;
    }

    public ZonaDistribucionBean findZonaById(long idZona) throws DBException {
        ZonaDistribucionBean zona = null;

        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_ZONA_DIST_BY_ID));

            stmt.setLong(1, idZona);
            rs = stmt.executeQuery();

            if (rs.next()) {
                zona = this.parseZonaDistribucion(rs);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de traer el bean de zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return zona;
    }

    public List findZonasByIdEstado(String idEstado) throws DBException {
        List zonas = new ArrayList(0);

        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_ZONA_DIST_BY_ESTADO));

            stmt.setString(1, idEstado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                zonas.add(this.parseZonaDistribucion(rs));
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de traer el bean de zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return zonas;
    }

    public ZonaDistribucionBean findZonaByCp(String cp) throws DBException {
        ZonaDistribucionBean zona = null;

        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_ZONA_DIST_BY_CP));

            stmt.setString(1, cp);
            rs = stmt.executeQuery();

            if (rs.next()) {
                zona = this.parseZonaDistribucion(rs);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de traer el bean de zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return zona;
    }

    public List findZonasByIdEstadoMunicipio(String idEstado, String idMunicipio)
            throws DBException {
        List zonas = null;

        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_ZONA_DIST_BY_ESTADO_MUNICIPIO));

            stmt.setString(1, idEstado);
            stmt.setString(2, idMunicipio);
            rs = stmt.executeQuery();

            if (rs != null) {
                zonas = new ArrayList(0);

                while (rs.next()) {
                    zonas.add(this.parseZonaDistribucion(rs));
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de traer el bean de zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return zonas;
    }

    public boolean deleteZona(long idZona) throws DBException {
        boolean success = false;

        DBConnection dbConnection = this.getConnection();
        CommonCallableStatement cs = null;

        try {

            cs = dbConnection.getCommonCallableStatement(commonResource.getString(
                    CommonConstants.SP_ZONA_DISTRIBUCION_DELETE));

            cs.setLong(1, idZona);
            cs.execute();

            success = true;

        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de eliminar la zona de distribución");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return success;
    }

    public Map<String, String> validateZonaDias(String idEstado, String idMunicipio,
            String idMensajeria, boolean lunes, boolean martes, boolean miercoles,
            boolean jueves, boolean viernes, boolean sabado, boolean domingo) throws DBException {
        Map output = null;

        CommonResultSet rs = null;
        CommonCallableStatement cs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            cs = dbConnection.getCommonCallableStatement(commonResource.getString(
                    CommonConstants.SP_ZONA_DISTRIBUCION_VALIDATE));

            int i = 1;
            cs.setString(i++, idEstado);
            cs.setString(i++, idMunicipio);
            cs.setString(i++, idMensajeria);
            cs.setBit(i++, lunes);
            cs.setBit(i++, martes);
            cs.setBit(i++, miercoles);
            cs.setBit(i++, jueves);
            cs.setBit(i++, viernes);
            cs.setBit(i++, sabado);
            cs.setBit(i++, domingo);

            rs = cs.executeCommonQuery();

            if (rs.next()) {
                output = new HashMap<String, String>();

                output.put(CommonConstants.LLAVE_VALIDA_ZONA_CONTADOR, rs.getString(1));
                output.put(CommonConstants.LLAVE_VALIDA_ZONA_DIA, rs.getString(2));
                output.put(CommonConstants.LLAVE_VALIDA_ZONA_MENSAJERIA, rs.getString(3));
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de validar la zona");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return output;
    }

    public boolean validateExisteMunicipio(String idEstado, String idMunicipio) throws DBException {
        boolean existe = false;

        CommonResultSet rs = null;
        CommonCallableStatement cs = null;

        DBConnection dbConnection = this.getConnection();

        try {
            cs = dbConnection.getCommonCallableStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_ZONA_DIST_BY_ESTADO_MUNICIPIO));

            int i = 1;
            cs.setString(i++, idEstado);
            cs.setString(i++, idMunicipio);

            rs = cs.executeCommonQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al momento de validar si existe el municipio");
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return existe;
    }

    private ZonaDistribucionBean parseZonaDistribucion(CommonResultSet rs)
            throws SQLException {
        ZonaDistribucionBean zona = new ZonaDistribucionBean();

        zona.setIdZona(rs.getInt("idZonaDist"));
        zona.setIdEstado(rs.getString("idestado"));
        zona.setNombreEstado(rs.getString("strestado"));
        zona.setIdMunicipio(rs.getString("idmunicipio"));
        zona.setNombreMunicipio(rs.getString("strmunicipio"));
        zona.setIdMensajeria(rs.getString("idmensajeria"));
        zona.setNombreMensajeria(rs.getString("strmensajeria"));

        zona.setLunes(rs.getBoolean("bitdia1"));
        zona.setMartes(rs.getBoolean("bitdia2"));
        zona.setMiercoles(rs.getBoolean("bitdia3"));
        zona.setJueves(rs.getBoolean("bitdia4"));
        zona.setViernes(rs.getBoolean("bitdia5"));
        zona.setSabado(rs.getBoolean("bitdia6"));
        zona.setDomingo(rs.getBoolean("bitdia7"));

        return zona;
    }
}
