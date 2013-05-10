package com.bconnect.common.dao;

import com.bconnect.common.bean.GenericBean;
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
public class CommonQueriesDBHelper extends BaseDBHelper {

    public CommonQueriesDBHelper(String dbInstance) {
        super(dbInstance);
    }

    public boolean esDiaFeriado(String fecha) throws DBException {
        boolean esFeriado = false;

        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_DIA_FERIADO_FIND));

            stmt.setString(1, fecha);

            rs = stmt.executeQuery();
            if (rs.next()) {
                esFeriado = true;
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al validar si es día feriado", sqle);
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
        return esFeriado;
    }

    public List findCatalogoById(int idCatalogo) throws DBException {
        List catalogo = new ArrayList();
        CommonPreparedStatement stmt = null;
        CommonResultSet rs = null;
        DBConnection dbConnection = this.getConnection();

        try {
            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_CATALOGO_FIND_BY_ID));

            stmt.setInt(1, idCatalogo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                GenericBean elemento = new GenericBean();
                elemento.setId(rs.getInt("idElementoCat"));
                elemento.setIdFk(rs.getInt("idCatalogo"));
                elemento.setNombre(rs.getString("strNombreElem"));

                catalogo.add(elemento);
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrio un error al traer los elementos del catálogo " +
                    idCatalogo, sqle);
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
        return catalogo;
    }
}
