package com.bconnect.common.bean.distribucion;

/**
 *
 * @author Jorge Rodriguez
 */
public class ZonaDistribucionBean {

    private long idZona;
    private String idEstado;
    private String idMunicipio;
    private String idColonia;
    private String idMensajeria;
    private String nombreEstado;
    private String nombreMunicipio;
    private String nombreColonia;
    private String nombreMensajeria;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private String tiempoEntrega;
    private String costo;

    public ZonaDistribucionBean() {
    }

    public long getIdZona() {
        return idZona;
    }

    public void setIdZona(long idZona) {
        this.idZona = idZona;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getIdMensajeria() {
        return idMensajeria;
    }

    public void setIdMensajeria(String idMensajeria) {
        this.idMensajeria = idMensajeria;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreMensajeria() {
        return nombreMensajeria;
    }

    public void setNombreMensajeria(String nombreMensajeria) {
        this.nombreMensajeria = nombreMensajeria;
    }

    public boolean isLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public boolean tieneDiasAssignados() {
        boolean tieneAsignados = false;

        tieneAsignados = (lunes ||
                martes ||
                miercoles ||
                jueves ||
                viernes ||
                sabado ||
                domingo);

        return tieneAsignados;
    }

    public String getDias() {
        StringBuffer dias = new StringBuffer();

        if (lunes) {
            dias.append("Lu");
            dias.append(" ");
        }
        if (martes) {
            dias.append("Ma");
            dias.append(" ");
        }
        if (miercoles) {
            dias.append("Mi");
            dias.append(" ");
        }
        if (jueves) {
            dias.append("Ju");
            dias.append(" ");
        }
        if (viernes) {
            dias.append("Vi");
            dias.append(" ");
        }
        if (sabado) {
            dias.append("Sa");
            dias.append(" ");
        }
        if (domingo) {
            dias.append("Do");
            dias.append(" ");
        }

        return dias.toString();
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public String getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(String idColonia) {
        this.idColonia = idColonia;
    }
}
