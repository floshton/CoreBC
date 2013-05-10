package com.bconnect.common.dao.outbound;

import com.bconnect.common.dao.outbound.*;
import com.bconnect.common.dao.*;
import com.bconnect.common.bean.personal.ClienteBConnectBean;
import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;

/**
 *
 * @author Jorge Rodriguez
 */
public class ClienteBConnectDao extends BaseDao {

    ClienteBConnectDBHelper dbHelper;

    public ClienteBConnectDao(String dbInstance) {
        super(dbInstance);
    }

    public ClienteBConnectDao() {
        super(CommonConstants.DB_INFORMIX_APPLICATION);
    }

    @Override
    protected void initializeDBHelper() {
        dbHelper = new ClienteBConnectDBHelper(dbInstance);
    }
    
    public ClienteBConnectBean findInDespachoByNumCliente(String numeroCliente){
        ClienteBConnectBean cliente = null;
        try {
            cliente = dbHelper.findInDespachoByNumCliente(numeroCliente);
        } catch (DBException dbe) {
            logger.error("Ocurrio un error al traer la lista de estados");
            dbe.printStackTrace();
        }
        return cliente;
    }
}
