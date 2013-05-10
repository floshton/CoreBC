/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bconnect.common.bean.personal;

/**
 *
 * @author isabel.ortiz
 */
public class UsuarioEsp1Bean {

    private String login;
    private String nomina;
    private String nombre;
    private int idHorario;
    private String horario;
    private int idSupervisor;
    private String supervisor;
    private String validador;

    public UsuarioEsp1Bean(){

    }

    public UsuarioEsp1Bean(String login){
        this.login = login;
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
     * @return the nomina
     */
    public String getNomina() {
        return nomina;
    }

    /**
     * @param nomina the nomina to set
     */
    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the idSupervisor
     */
    public int getIdSupervisor() {
        return idSupervisor;
    }

    /**
     * @param idSupervisor the idSupervisor to set
     */
    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the validador
     */
    public String getValidador() {
        return validador;
    }

    /**
     * @param validador the validador to set
     */
    public void setValidador(String validador) {
        this.validador = validador;
    }

}
