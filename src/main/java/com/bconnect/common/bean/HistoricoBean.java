package com.bconnect.common.bean;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import com.bconnect.common.util.DateUtil;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class HistoricoBean {

    protected int idHistorico;
    protected int idRegistro;
    protected int idEvento;
    protected String status;
    protected String nombre;
    protected String notas;
    protected String creadoPor;
    protected Date fechaCreacion;
    protected Calendar fechaInicioEvento;
    protected Calendar fechaFinEvento;
    protected long duracionEvento;
    protected String idCalificacion;
    protected String idCalificacionNombre;
    protected String idSubcalificacion;
    protected String idSubcalificacionNombre;

    public HistoricoBean() {
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
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

    public String getFechaCreacionFormat() {
        return DateUtil.formatDate(fechaCreacion,
                CommonConstants.FECHA_FORMATO_WEB_DDMMYYYY_HHMM);
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Calendar getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    public void setFechaInicioEvento(Calendar fechaInicioEvento) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    public Calendar getFechaFinEvento() {
        return fechaFinEvento;
    }

    public void setFechaFinEvento(Calendar fechaFinEvento) {
        this.fechaFinEvento = fechaFinEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDuracionEvento() {
        return duracionEvento;
    }

    public void setDuracionEvento(long duracionEvento) {
        this.duracionEvento = duracionEvento;
    }

    public void calculaDuracionEvento() {
        if (fechaInicioEvento != null && fechaFinEvento != null) {
            long duracion = DateUtil.findDiffereceSeconds(fechaInicioEvento, fechaFinEvento);
            this.setDuracionEvento(duracion);
        }
    }

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getIdSubcalificacion() {
        return idSubcalificacion;
    }

    public void setIdSubcalificacion(String idSubcalificacion) {
        this.idSubcalificacion = idSubcalificacion;
    }

    public String getIdCalificacionNombre() {
        return idCalificacionNombre;
    }

    public void setIdCalificacionNombre(String idCalificacionNombre) {
        this.idCalificacionNombre = idCalificacionNombre;
    }

    public String getIdSubcalificacionNombre() {
        return idSubcalificacionNombre;
    }

    public void setIdSubcalificacionNombre(String idSubcalificacionNombre) {
        this.idSubcalificacionNombre = idSubcalificacionNombre;
    }
}
