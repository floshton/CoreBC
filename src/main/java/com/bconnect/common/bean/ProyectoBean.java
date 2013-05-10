package com.bconnect.common.bean;

import java.util.Date;

/**
 *
 * @author floshton
 */
public class ProyectoBean {

    protected int id;
    protected int idProyecto;
    protected String nombre;
    protected String clave;
    protected String status;
    protected String creadoPor;
    protected boolean activo;
    protected String descripcion;
    protected int idCliente;
    protected String nombreSite;
    protected String prefijoClaveRed;
    private Date fechaInicio;
    private Date fechaFin;
    protected Date fechaCreacion;

    public ProyectoBean() {
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreSite() {
        return nombreSite;
    }

    public void setNombreSite(String nombreSite) {
        this.nombreSite = nombreSite;
    }

    public String getPrefijoClaveRed() {
        return prefijoClaveRed;
    }

    public void setPrefijoClaveRed(String prefijoClaveRed) {
        this.prefijoClaveRed = prefijoClaveRed;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
