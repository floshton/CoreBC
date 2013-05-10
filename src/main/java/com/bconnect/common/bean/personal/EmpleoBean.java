package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonUtils;
import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class EmpleoBean {

    protected int id;
    protected String nombre;
    protected boolean personaFisica;
    protected String puesto;
    protected String ramo;
    protected int idRamo;
    protected String empresa;
    private String mesInicio;
    private String mesFin;
    private String yearInicio;
    private String yearFin;
    protected Date fechaInicio;
    protected Date fechaFin;
    private String fechaInicioString;
    private String fechaFinString;
    protected String tipoContrato;
    protected String actividad;
    protected String ocupacion;
    protected DomicilioBean domicilio;
    protected TelefonoBean telefono1;
    protected TelefonoBean telefono2;
    protected String ingresoMensual;
    protected String ingresoTotal;
    protected String ingresoOtros;
    protected String creadoPor;

    public EmpleoBean() {
        domicilio = new DomicilioBean();
        telefono1 = new TelefonoBean();
        telefono2 = new TelefonoBean();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPersonaFisica(boolean personaFisica) {
        this.personaFisica = personaFisica;
    }

    public boolean isPersonaFisica() {
        return personaFisica;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public int getIdRamo() {
        return idRamo;
    }

    public void setIdRamo(int idRamo) {
        this.idRamo = idRamo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getFechaInicioString() {
        return fechaInicioString;
    }

    public void setFechaInicioString(String fechaInicioString) {
        this.fechaInicioString = fechaInicioString;
    }

    public String getFechaFinString() {
        return fechaFinString;
    }

    public void setFechaFinString(String fechaFinString) {
        this.fechaFinString = fechaFinString;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (!CommonUtils.hasValue(ramo) ||
                !CommonUtils.hasValue(yearInicio) ||
                !CommonUtils.hasValue(yearFin) ||
                !CommonUtils.hasValue(mesInicio) ||
                !CommonUtils.hasValue(mesFin)) {
            isValid = false;
        }
        return isValid;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    public String getMesFin() {
        return mesFin;
    }

    public void setMesFin(String mesFin) {
        this.mesFin = mesFin;
    }

    public String getYearInicio() {
        return yearInicio;
    }

    public void setYearInicio(String yearInicio) {
        this.yearInicio = yearInicio;
    }

    public String getYearFin() {
        return yearFin;
    }

    public void setYearFin(String yearFin) {
        this.yearFin = yearFin;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public DomicilioBean getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioBean domicilio) {
        this.domicilio = domicilio;
    }

    public TelefonoBean getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(TelefonoBean telefono1) {
        this.telefono1 = telefono1;
    }

    public TelefonoBean getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(TelefonoBean telefono2) {
        this.telefono2 = telefono2;
    }

    public String getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(String ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(String ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public String getIngresoOtros() {
        return ingresoOtros;
    }

    public void setIngresoOtros(String ingresoOtros) {
        this.ingresoOtros = ingresoOtros;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
