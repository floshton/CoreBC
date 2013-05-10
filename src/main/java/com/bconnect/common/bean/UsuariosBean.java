/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bconnect.common.bean;

import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author isabel.ortiz
 */
public class UsuariosBean {

    private String login;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    public UsuariosBean(){

    }

    public UsuariosBean(String login, String nombre1, String nombre2, String apellido1, String apellido2){
        this.login = login;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getNombreCompleto(){
        String nombreCompleto = "";
        if(CommonUtils.hasValue(nombre1))
            nombreCompleto = nombreCompleto.concat(nombre1.trim()).concat(" ");
        if(CommonUtils.hasValue(nombre2))
            nombreCompleto = nombreCompleto.concat(nombre2.trim()).concat(" ");
        if(CommonUtils.hasValue(apellido1))
            nombreCompleto = nombreCompleto.concat(apellido1.trim()).concat(" ");
        if(CommonUtils.hasValue(apellido2))
            nombreCompleto = nombreCompleto.concat(apellido2.trim()).concat(" ");
        return nombreCompleto.trim();
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }



}
