package com.bconnect.common.dao;

import com.bconnect.common.bean.domicilio.ColoniaBean;
import com.bconnect.common.bean.domicilio.EstadoBean;
import com.bconnect.common.bean.domicilio.MunicipioBean;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.db.util.CommonPreparedStatement;
import com.bconnect.common.db.util.CommonResultSet;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class DireccionesDBHelper extends BaseDBHelper {

    public DireccionesDBHelper(String dbInstance) {
        super(dbInstance);
    }

    public String findEstadoByCp(String cp) throws DBException {

        String estado = null;



        CommonResultSet rs = null;

        CommonPreparedStatement stmt = null;



        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_INFO_BY_CP));



            stmt.setString(1, cp);



            rs = stmt.executeQuery();



            if (rs.next()) {

                estado = rs.getString("estado");

            }

        } catch (SQLException sqle) {

            logger.error("Ocurrio un error al traer el estado por cp");

            sqle.printStackTrace();

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

        return estado;

    }

    public List findEstados() throws DBException {
        List estados = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_ESTADOS));

            resultSet = stmt.executeQuery();

            if (resultSet != null) {

                CommonResultSet rs = new CommonResultSet(resultSet);

                estados = new ArrayList();

                while (rs.next()) {
                    EstadoBean estado = new EstadoBean();

                    estado.setId(rs.getString("idedo"));
                    estado.setNombre(rs.getString("estado"));
                    estado.setSiglas(rs.getString("siglas"));

                    estados.add(estado);
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de estados");
            sqle.printStackTrace();
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
        return estados;
    }

    public List findMunicipiosByEstado(String idEstado) throws DBException {
        List municipios = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_MUNICIPIOS_BY_EDO));

            stmt.setString(1, idEstado);

            resultSet = stmt.executeQuery();

            if (resultSet != null) {

                CommonResultSet rs = new CommonResultSet(resultSet);

                municipios = new ArrayList();

                while (rs.next()) {
                    MunicipioBean municipio = new MunicipioBean();

                    municipio.setId(rs.getString("idmun"));
                    municipio.setNombre(rs.getString("municipio"));

                    municipios.add(municipio);
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de municipios");
            sqle.printStackTrace();
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
        return municipios;
    }

    public List findCiudades() throws DBException {
        List ciudades = new ArrayList();
        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;
        DBConnection dbConnection = this.getConnection();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_MUNICIPIOS));
            rs = stmt.executeQuery();
            if (rs != null) {

                while (rs.next()) {
                    ColoniaBean ciudad = new ColoniaBean();
                    ciudad.setMunicipio(rs.getString("municipio"));
                    ciudad.setEstado(rs.getString("estado"));

                    ciudades.add(ciudad);
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de ciudades");
            sqle.printStackTrace();
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
        return ciudades;
    }

    public List findColoniasByMunicipio(String idMunicipio) throws DBException {
        List colonias = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_COLONIAS_BY_MUNICIPIO));

            stmt.setString(1, idMunicipio);

            resultSet = stmt.executeQuery();

            if (resultSet != null) {

                CommonResultSet rs = new CommonResultSet(resultSet);

                colonias = new ArrayList();

                while (rs.next()) {
                    ColoniaBean colonia = new ColoniaBean();

                    colonia.setId(rs.getString("idcol"));
                    colonia.setNombre(rs.getString("colonia"));
                    colonia.setCp(rs.getString("cp"));

                    colonias.add(colonia);
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de colonias");
            sqle.printStackTrace();
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
        return colonias;
    }

    public String findCpByColonia(String idColonia) throws DBException {
        String cp = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_CP_BY_COLONIA));

            stmt.setString(1, idColonia);

            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                CommonResultSet rs = new CommonResultSet(resultSet);
                cp = rs.getString(1);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer el cp");
            sqle.printStackTrace();
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
        return cp;
    }

    public List findUbicacionesByCp(String cp) throws DBException {
        List ubicaciones = null;

        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();
        try {
            stmt = dbConnection.getCommonPreparedStatement(commonResource.getString(
                    CommonConstants.QUERY_FIND_INFO_BY_CP));

            stmt.setString(1, cp);

            rs = stmt.executeQuery();
            ubicaciones = new ArrayList(0);

            while (rs.next()) {
                ColoniaBean colonia = new ColoniaBean();

                colonia.setId(rs.getString("idColonia"));
                colonia.setNombre(rs.getString("colonia"));
                colonia.setIdMunicipio(rs.getString("idMunicipio"));
                colonia.setMunicipio(rs.getString("municipio"));
                colonia.setIdEstado(rs.getString("idEstado"));
                colonia.setEstado(rs.getString("estado"));

                ubicaciones.add(colonia);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer el cp");
            sqle.printStackTrace();
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
        return ubicaciones;
    }

    public List findMunicipiosByStrEstado(String strEstado) throws DBException {
        List municipios = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();

        try {

            stmt = dbConnection.getPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_MUNICIPIOS_BY_STREDO));

            stmt.setString(1, strEstado);

            resultSet = stmt.executeQuery();

            if (resultSet != null) {

                CommonResultSet rs = new CommonResultSet(resultSet);

                municipios = new ArrayList();

                while (rs.next()) {
                    MunicipioBean municipio = new MunicipioBean();

                    municipio.setId(rs.getString("idmun"));
                    municipio.setNombre(rs.getString("municipio"));

                    municipios.add(municipio);
                }
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de municipios");
            sqle.printStackTrace();
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
        return municipios;
    }
}
