package com.bconnect.common.bean.personal;

import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class ClienteBConnectBean extends PersonaBean {

    protected String numeroCliente;
    protected String proyecto;
    protected Date fechaLlamada;
    protected String numeroEmpleado;
    protected String supervisor;
    protected String idCal;
    protected String idScal;
    protected String tipoRegistro;

    public ClienteBConnectBean() {
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(Date fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getIdCal() {
        return idCal;
    }

    public void setIdCal(String idCal) {
        this.idCal = idCal;
    }

    public String getIdScal() {
        return idScal;
    }

    public void setIdScal(String idScal) {
        this.idScal = idScal;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
