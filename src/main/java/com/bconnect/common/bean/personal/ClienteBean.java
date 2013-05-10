package com.bconnect.common.bean.personal;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jorge Rodriguez
 */
public class ClienteBean extends PersonaBean {

    Integer idCliente;
    List<RefBancariaBean> refBancarias;
    List<PersonaBean> refPersonales;

    public ClienteBean() {
        super();

        refBancarias = new ArrayList<RefBancariaBean>();
        refPersonales = new ArrayList<PersonaBean>();
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public List<RefBancariaBean> getRefBancarias() {
        return refBancarias;
    }

    public void setRefBancarias(List<RefBancariaBean> refBanciarias) {
        this.refBancarias = refBanciarias;
    }

    public List<PersonaBean> getRefPersonales() {
        return refPersonales;
    }

    public void setRefPersonales(List<PersonaBean> refPersonales) {
        this.refPersonales = refPersonales;
    }

    @Override
    public String toString() {
        return super.getNombreCompleto();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClienteBean)) {
            return false;
        }
        ClienteBean other = (ClienteBean) obj;
        if ((this.getIdCliente() == null && other.getIdCliente() != null)
                || (this.getIdCliente() != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }
}
