package com.bconnect.common.bean;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class StatusBean {
    
    protected int idHistorial;
    protected int idCandidato;
    protected int idStatus;
    protected String claveStatus;
    protected String nombre;
    protected String notas;
    protected String creadoPor;
    protected Date fechaCreacion;

    public StatusBean() {
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }    
    public String getFechaCreacionFormat(){
        return DateUtil.formatDate(fechaCreacion, CommonConstants.FECHA_FORMATO_WEB_DDMMYYYY_HHMM);
    }

    public String getClaveStatus() {
        return claveStatus;
    }

    public void setClaveStatus(String claveStatus) {
        this.claveStatus = claveStatus;
    }
}
