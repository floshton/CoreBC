package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author Jorge Rodriguez
 */
public class IdiomaBean {

    public IdiomaBean() {
    }
    protected int id;
    protected String nombre;
    protected String dominioEscritura;
    protected String dominioOral;
    protected String dominioLectura;

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

    public String getDominioEscritura() {
        return dominioEscritura;
    }

    public void setDominioEscritura(String dominioEscritura) {
        this.dominioEscritura = dominioEscritura;
    }

    public String getDominioOral() {
        return dominioOral;
    }

    public void setDominioOral(String dominioOral) {
        this.dominioOral = dominioOral;
    }

    public String getDominioLectura() {
        return dominioLectura;
    }

    public void setDominioLectura(String dominioLectura) {
        this.dominioLectura = dominioLectura;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (!CommonUtils.hasValue(nombre) ||
                !CommonUtils.hasValue(dominioEscritura) ||
                !CommonUtils.hasValue(dominioOral)) {
            isValid = false;
        }
        return isValid;
    }
}
