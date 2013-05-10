package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonUtils;
import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class EstanciaExtranjeroBean {

    private String pais;
    private String motivo;
    private Date fechaInicio;
    private Date fechaFin;
    private String fechaInicioString;
    private String fechaFinString;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
        if (!CommonUtils.hasValue(pais) ||
                !CommonUtils.hasValue(motivo)) {
            isValid = false;
        }
        return isValid;
    }
}
