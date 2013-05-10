package com.bconnect.common.dao;

import com.bconnect.common.bean.EmpresaBean;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.db.util.CommonPreparedStatement;
import com.bconnect.common.db.util.CommonResultSet;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class EmpresaDBHelper extends BaseDBHelper {

    public EmpresaDBHelper(String dbInstance) {
        super(dbInstance);
    }

    public List findEmpresas() throws DBException {
        List empresas = null;

        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_EMPRESAS));

            rs = stmt.executeQuery();

            empresas = new ArrayList();
            while (rs.next()) {
                empresas.add(this.parseEmpresaBean(rs));
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de empresas", sqle);
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion", dbe);
                throw new DBException(dbe);
            }
        }
        return empresas;
    }

    public List findEmpresasByGiro(String giro) throws DBException {
        List empresas = null;

        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_EMPRESAS_BY_GIRO));

            stmt.setString(1, giro);
            rs = stmt.executeQuery();

            empresas = new ArrayList();
            while (rs.next()) {
                empresas.add(this.parseEmpresaBean(rs));
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la lista de empresas", sqle);
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion", dbe);
                throw new DBException(dbe);
            }
        }
        return empresas;
    }

    public EmpresaBean findEmpresaById(int id) throws DBException {
        EmpresaBean empresa = null;

        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();
        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_EMPRESA_BY_ID));
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                empresa = this.parseEmpresaBean(rs);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer la empresa con id " + id, sqle);
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrio un error al momento de cerrar la conexion", dbe);
                throw new DBException(dbe);
            }
        }
        return empresa;
    }

    private EmpresaBean parseEmpresaBean(CommonResultSet rs) throws SQLException {
        EmpresaBean empresa = new EmpresaBean();

        empresa.setId(rs.getInt("idEmpresa"));
        empresa.setNombre(rs.getString("strEmpresa"));
        empresa.setRazonSocial(rs.getString("strRazonSocial"));
        empresa.setGiro(rs.getString("strGiro"));
        empresa.setRfc(rs.getString("strRfc"));
        empresa.setActivo(rs.getBoolean("bitActivo"));
        empresa.setEmail(rs.getString("stremail"));

        return empresa;
    }
}
