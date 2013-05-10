/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bconnect.common.bean.animal;

import java.util.Date;

/**
 *
 * @author Jorge Rodriguez
 */
public class AnimalBean {

    protected String nombre;
    protected String sexo;
    protected String edad;
    protected Date fechaNacimiento;
    protected String peso;
    protected String raza;

    public AnimalBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
