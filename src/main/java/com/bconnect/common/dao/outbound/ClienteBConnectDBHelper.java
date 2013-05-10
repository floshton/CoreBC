package com.bconnect.common.dao.outbound;

import com.bconnect.common.dao.outbound.*;
import com.bconnect.common.dao.*;
import com.bconnect.common.bean.domicilio.EstadoBean;
import com.bconnect.common.bean.personal.ClienteBConnectBean;
import com.bconnect.common.bean.personal.TelefonoBean;
import com.bconnect.common.db.DBConnection;
import com.bconnect.common.db.util.CommonPreparedStatement;
import com.bconnect.common.db.util.CommonResultSet;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Rodriguez
 */
public class ClienteBConnectDBHelper extends BaseDBHelper{
    
    public ClienteBConnectDBHelper(String dbInstance) {
        super(dbInstance);
    }

    public ClienteBConnectBean findInDespachoByNumCliente(String numeroCliente) throws DBException{
     
        ClienteBConnectBean cliente = null;
        CommonResultSet rs = null;
        CommonPreparedStatement stmt = null;

        DBConnection dbConnection = this.getConnection();
        try {

            stmt = dbConnection.getCommonPreparedStatement(
                    commonResource.getString(CommonConstants.QUERY_FIND_CLIENTE_IN_DESPACHO_BY_NUM_CLIENTE));
            stmt.setString(1, numeroCliente);

            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new ClienteBConnectBean();
                cliente.setNumeroCliente(rs.getString("num_cliente"));
                cliente.setProyecto(rs.getString("proy"));
                cliente.setTelefonoCasa(rs.getString("telefono"));
                cliente.setNombre1(rs.getString("nombre1"));
                cliente.setNombre2(rs.getString("nombre2"));
                cliente.setApellido1(rs.getString("paterno"));
                cliente.setApellido2(rs.getString("materno"));
                cliente.setFechaLlamada(rs.getDate("fecha_llamada"));
                cliente.setNumeroEmpleado(rs.getString("num_empleado"));
                cliente.setSupervisor(rs.getString("supervisor"));
                cliente.setIdCal(rs.getString("idcal"));
                cliente.setIdScal(rs.getString("idscal"));
            }
        } catch (SQLException sqle) {
            logger.error("Ocurrió un error al traer los datos del Cliente");
            sqle.printStackTrace();
            throw new DBException(sqle);
        } finally {
            try {
                dbConnection.close();
            } catch (DBException dbe) {
                logger.error("Ocurrió un error al momento de cerrar la conexion");
                dbe.printStackTrace();
                throw new DBException(dbe);
            }
        }
        return cliente;
    }
}
