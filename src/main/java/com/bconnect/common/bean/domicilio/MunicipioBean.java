package com.bconnect.common.bean.domicilio;

/**
 *
 * @author Jorge Rodriguez
 */
public class MunicipioBean {
    
    protected String id;
    protected String nombre;
    protected String idEstado;
    
    public MunicipioBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }
}
