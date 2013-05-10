package com.bconnect.common.interfaces;

import com.bconnect.common.bean.personal.ClienteBean;

/**
 *
 * @author jorge.rodriguez
 */
public abstract class AbstractClienteDao {

    public abstract ClienteBean insert(ClienteBean cliente);
    
    public abstract ClienteBean update(ClienteBean cliente);
}
