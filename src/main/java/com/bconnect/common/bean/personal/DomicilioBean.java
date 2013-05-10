package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author Jorge Rodriguez
 */
public class DomicilioBean {

    public DomicilioBean() {
    }
    protected String tipoCalle;
    protected String calle;
    protected String tipoExterior;
    protected String numExterior;
    protected String tipoInterior;
    protected String numInterior;
    protected String cp;
    protected String apartadoPostal;
    protected String idColonia;
    protected String colonia;
    protected String idMunicipio;
    protected String municipio;
    protected String idEstado;
    protected String estado;
    protected String idPais;
    protected String pais;
    protected String entreCalle1;
    protected String entreCalle2;
    protected String refGeografica;
    protected String ciudad;
    protected String direccionCompleta;

    public boolean validateInput() {
        return (CommonUtils.hasValue(calle) &&
                CommonUtils.hasValue(numExterior) &&
                CommonUtils.hasValue(cp) &&
                CommonUtils.hasValue(colonia) &&
                CommonUtils.hasValue(municipio) &&
                CommonUtils.hasValue(estado) &&
                CommonUtils.hasValue(entreCalle1) &&
                CommonUtils.hasValue(entreCalle2) &&
                CommonUtils.hasValue(refGeografica));
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumExterior() {
        return numExterior;
    }

    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    public String getNumInterior() {
        return numInterior;
    }

    public void setNumInterior(String numInterior) {
        this.numInterior = numInterior;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getApartadoPostal() {
        return apartadoPostal;
    }

    public void setApartadoPostal(String apartadoPostal) {
        this.apartadoPostal = apartadoPostal;
    }

    public String getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(String idColonia) {
        this.idColonia = idColonia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    public String getEntreCalle1() {
        return entreCalle1;
    }

    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    public String getEntreCalle2() {
        return entreCalle2;
    }

    public String getEntreCalles() {
        StringBuffer entreCalles = new StringBuffer();
        entreCalles.append(entreCalle1);
        if (CommonUtils.hasValue(entreCalle2)) {
            entreCalles.append(" Y ");
            entreCalles.append(entreCalle2);
        }
        return entreCalles.toString();
    }

    public String getRefGeografica() {
        return refGeografica;
    }

    public void setRefGeografica(String refGeografica) {
        this.refGeografica = refGeografica;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getDireccionCompleta() {
        if (CommonUtils.hasValue(this.direccionCompleta)) {
            return this.direccionCompleta;
        } else {
            StringBuffer direccion = new StringBuffer();
            if (CommonUtils.hasValue(tipoCalle)) {
                direccion.append(tipoCalle);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(calle)) {
                direccion.append(calle);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(tipoExterior)) {
                direccion.append(tipoExterior);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(numExterior)) {
                direccion.append(numExterior);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(tipoInterior)) {
                direccion.append(tipoInterior);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(numInterior)) {
                direccion.append(numInterior);
                direccion.append(" ");
            }
            if (CommonUtils.hasValue(colonia)) {
                direccion.append("COL. ");
                direccion.append(colonia);
            }
            if (CommonUtils.hasValue(estado)) {
                direccion.append(" ");
                direccion.append(estado);
            }
            if (CommonUtils.hasValue(cp)) {
                direccion.append(" ");
                direccion.append("C.P. ");
                direccion.append(cp);
                direccion.append(". ");
            }
            if (CommonUtils.hasValue(entreCalle1)) {
                direccion.append("ENTRE CALLES ");
                direccion.append(this.getEntreCalles());
                direccion.append(". ");
            }
            if (CommonUtils.hasValue(refGeografica)) {
                direccion.append("REFERENCIA GEOGRÁFICA: ");
                direccion.append(refGeografica);
                direccion.append(". ");
            }
            return direccion.toString();
        }
    }

    public String getTipoCalle() {
        return tipoCalle;
    }

    public void setTipoCalle(String tipoCalle) {
        this.tipoCalle = tipoCalle;
    }

    public String getTipoExterior() {
        return tipoExterior;
    }

    public void setTipoExterior(String tipoExterior) {
        this.tipoExterior = tipoExterior;
    }

    public String getTipoInterior() {
        return tipoInterior;
    }

    public void setTipoInterior(String tipoInterior) {
        this.tipoInterior = tipoInterior;
    }
}
