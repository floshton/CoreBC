package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonConstants;
import java.util.List;

/**
 * Este bean encapsula todas aquellas propiedades asociadas con un Usuario,
 * ya sea de tipo administrativo, operacional, etc.
 * @author Jorge Rodriguez
 */
public class UsuarioBean extends PersonaBean {

    protected String numeroEmpleado;
    protected String login;
    protected String password;
    protected String esActivo;
    protected String fechaExpiraPass;
    protected long idUsuario;
    protected String idEmpleado;
    protected int idEmpresa;
    protected int idDepto;
    protected int idArea;
    protected int idPerfil;
    protected int nivel;
    protected String cvePerfil;
    protected long idProyecto;
    protected String cveProyecto;
    protected String extension;
    protected int idDepartamento;
    protected int idJefe;
    protected String loginTel;
    //Se agrego para examenes
    private int idCuestionario;
    private float calificacion;
    private int idCalendarizacion;
    //Se agrego para MEG248
    private List menuPermisos;
    private String puesto;

    /**
     * Valida si el usuario tiene el nivel minimo requerido para accesar a 
     * alguna aplicacion (Especialmente usado para validacion en aplicaciones
     * administrativas)
     * @param requiredNivel el nivel minimo requerido por la aplicacion
     * @return true si el usuario tiene acceso permitido
     */
    public boolean hasAccess(int nivelMinimo) {
        return nivel >= nivelMinimo;
    }

    /**
     * Valida si el perfil del usuario es adecuado para accesar a una aplicacion
     * (especialmente aquellas de tipo administrativo). En caso de que la
     * lista de perfiles requeridos este vacia, el metodo valida como true.
     * @param requiredPerfiles una lista de perfiles adecuados para accesar a la
     * aplicacion
     * @return true si el usuario tiene acceso permitido
     */
    public boolean hasAccess(List<String> requiredPerfiles) {
        boolean hasAccess = false;

        if (requiredPerfiles.isEmpty()) {
            hasAccess = true;
        } else if (this.cvePerfil != null && !this.cvePerfil.equals("")) {
            for (int i = 0; i < requiredPerfiles.size(); i++) {
                if (requiredPerfiles.get(i).equals(this.cvePerfil)) {
                    hasAccess = true;
                    break;
                }
            }
        }
        return hasAccess;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEsActivo(String esActivo) {
        this.esActivo = esActivo;
    }

    public String getEsActivo() {
        return esActivo;
    }

    public void setFechaExpiraPass(String fechaExpiraPass) {
        this.fechaExpiraPass = fechaExpiraPass;
    }

    public String getFechaExpiraPass() {
        return fechaExpiraPass;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setCvePerfil(String cvePerfil) {
        this.cvePerfil = cvePerfil;
    }

    public String getCvePerfil() {
        return cvePerfil;
    }

    public boolean getIsPerfilIT() {
        return cvePerfil.equals(CommonConstants.PERFIL_IT);
    }

    public boolean getIsPerfilSA() {
        return cvePerfil.equals(CommonConstants.PERFIL_SA);
    }

    public boolean getIsPerfilDIR() {
        return cvePerfil.equals(CommonConstants.PERFIL_DIR);
    }

    public void setIdProyecto(long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public long getIdProyecto() {
        return idProyecto;
    }

    public void setCveProyecto(String cveProyecto) {
        this.cveProyecto = cveProyecto;
    }

    public String getCveProyecto() {
        return cveProyecto;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

    public String getLoginTel() {
        return loginTel;
    }

    public void setLoginTel(String loginTel) {
        this.loginTel = loginTel;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    /**
     * @return the idCuestionario
     */
    public int getIdCuestionario() {
        return idCuestionario;
    }

    /**
     * @param idCuestionario the idCuestionario to set
     */
    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    /**
     * @return the calificacion
     */
    public float getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the idCalendarizacion
     */
    public int getIdCalendarizacion() {
        return idCalendarizacion;
    }

    /**
     * @param idCalendarizacion the idCalendarizacion to set
     */
    public void setIdCalendarizacion(int idCalendarizacion) {
        this.idCalendarizacion = idCalendarizacion;
    }

    // Se agrego para MEG248

    /**
     * @return the menuPermisos
     */
    public List getMenuPermisos() {
        return menuPermisos;
    }

    /**
     * @param menuPermisos the menuPermisos to set
     */
    public void setMenuPermisos(List menuPermisos) {
        this.menuPermisos = menuPermisos;
    }

    public boolean getValidaPermisoMenu(String cveMenu){
        boolean permiso = false;
        if(this.menuPermisos != null && this.menuPermisos.size() > 0)
            permiso = this.menuPermisos.contains(cveMenu.trim());
        return permiso;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
