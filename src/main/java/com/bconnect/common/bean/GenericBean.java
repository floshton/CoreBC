package com.bconnect.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class GenericBean implements Serializable {

    protected int id;
    protected int idFk;
    protected String nombre;
    protected String clave;
    protected Date fecha;
    protected String descripcion;
    protected String subNombre;
    protected String strnotes;
    protected boolean bit;
    protected String status;
    protected String creadoPor;
    protected Date fechaCreacion;
    protected boolean activo;
    protected List items;
    private float valor;
    protected long conteo;

    public GenericBean() {
        this.items = new ArrayList();
    }

    public GenericBean(String nombre) {
        this();
        this.nombre = nombre;
    }

    public GenericBean(String clave, String nombre) {
        this();
        this.clave = clave;
        this.nombre = nombre;
    }

    public GenericBean(int id) {
        this();
        this.id = id;
    }

    public GenericBean(int id, String nombre) {
        this();
        this.id = id;
        this.nombre = nombre;
    }
    
    public GenericBean(Long id, String nombre) {
        this();
        this.id = id.intValue();
        this.nombre = nombre;
    }

    public String getNombreDescripcion() {
        StringBuffer text = new StringBuffer();
        text.append(nombre);
        text.append(" - ");
        text.append(descripcion);

        return text.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Object id) {
        if (id == null) {
            if (id instanceof String) {
                try {
                    this.id = Integer.valueOf(id.toString());
                } catch (NumberFormatException nfe) {
                    this.id = 0;
                }
            } else {
                this.id = 0;
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBit() {
        return bit;
    }

    public void setBit(boolean bit) {
        this.bit = bit;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubNombre() {
        return subNombre;
    }

    public void setSubNombre(String subNombre) {
        this.subNombre = subNombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public int getIdFk() {
        return idFk;
    }

    public void setIdFk(int idFk) {
        this.idFk = idFk;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getConteo() {
        return conteo;
    }

    public void setConteo(long conteo) {
        this.conteo = conteo;
    }

    @Override
    public boolean equals(Object object) {
        boolean equal = false;
        if (object instanceof GenericBean) {
            GenericBean bean = (GenericBean) object;
            equal = bean.getId() == this.id;
        }
        return equal;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        return hash;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getStrnotes() {
        return strnotes;
    }

    public void setStrnotes(String strnotes) {
        this.strnotes = strnotes;
    }

   
    
}
