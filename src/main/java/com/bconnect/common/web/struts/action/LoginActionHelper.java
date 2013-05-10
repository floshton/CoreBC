package com.bconnect.common.web.struts.action;

import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.dao.UsuarioDao;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import com.bconnect.common.util.ResourcesManager;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Esta clase sirve como soporte para la acción de inicio de sesión en la aplicación
 * @author Jorge Rodriguez
 */
public class LoginActionHelper {

    private String login;
    private String password;
    private String claveProyecto;
    private UsuarioDao usuarioDao;
    private long idUsuario;
    /**
     * Mensaje de error regresado, en caso de haber alguna excepción
     */
    private String errorMessage;
    private UsuarioBean usuario;
    private String forward;
    private int nivelMinimoAcceso;
    private String jndiConnectionName = null;

    private LoginActionHelper() {
        try {
            jndiConnectionName = ResourcesManager.getApplicationResource().getString(CommonConstants.PROJECT_CATALOGO_DB_CONN_JNDI_NAME);
        } catch (Exception e) {
            jndiConnectionName = CommonConstants.DB_INFORMIX_CATALOGO;
        }
    }

    public LoginActionHelper(String login, String password, String claveProyecto) {
        this();
        this.login = login;
        this.password = password;
        this.claveProyecto = claveProyecto;
    }

    public LoginActionHelper(String login, String password, String claveProyecto, int nivelMinimoAcceso) {
        this();
        this.login = login;
        this.password = password;
        this.claveProyecto = claveProyecto;
        this.nivelMinimoAcceso = nivelMinimoAcceso;
    }

    /**
     * Efectúa una llamada un stored procedure que valida que el usuario
     * tenga permisos de acceso a la aplicación. Pasada esta validación, también
     * se valida que el password no haya expirado ya. De haberlo hecho, se
     * redirige al usuario a la pantalla destinada a procesar una actualización 
     * del mismo
     * @return una cadena que consituye el Forward al que debe de ser dirigido
     * @throws java.text.ParseException
     */
    public String validateLogin() throws ParseException {
        ResourceBundle strutsErrorsResource = ResourcesManager.getStrutsErrorsResource();
        usuarioDao = new UsuarioDao(jndiConnectionName);
        idUsuario = usuarioDao.validateAccess(login, password, claveProyecto);
        int nivelMínimo = 0;

        if (ResourcesManager.getApplicationResource().
                getString(CommonConstants.PROJECT_ACCESS_LEVEL) != null) {
            nivelMínimo = Integer.parseInt(ResourcesManager.getApplicationResource().
                    getString(CommonConstants.PROJECT_ACCESS_LEVEL));
        }

        if (idUsuario != 0) {
            usuario = usuarioDao.findById(idUsuario);
        }
        if (usuario != null) {

            if (usuario.hasAccess(nivelMínimo)) {

                Date fechaSistema = DateUtil.getSystemDate();
                Date fechaVence = DateUtil.parseDate(usuario.getFechaExpiraPass(),
                        CommonConstants.FECHA_FORMATO_YYYYMMDD);

                if (fechaVence.compareTo(fechaSistema) > 0) {
                    return CommonConstants.FORWARD_SUCCESS;
                } else {
                    errorMessage = strutsErrorsResource.getString("form.login.error.expired");
                    return CommonConstants.FORWARD_CHANGE_PASSWORD;
                }
            } else {
                errorMessage = ResourcesManager.getStrutsErrorsResource().getString(
                        "form.login.error.noAccess");
                return CommonConstants.FORWARD_ERROR;
            }
        } else {
            errorMessage = strutsErrorsResource.getString("form.login.error.invalid");
            return CommonConstants.FORWARD_ERROR;
        }
    }

    /**
     * Efectúa una llamada un stored procedure que valida que el usuario
     * tenga permisos de acceso a la aplicación. Pasada esta validación, también
     * se valida que el password no haya expirado ya. De haberlo hecho, se
     * redirige al usuario a la pantalla destinada a procesar una actualización 
     * del mismo
     * @return una cadena que consituye el Forward al que debe de ser dirigido
     * @throws java.text.ParseException
     */
    public boolean isValidLogin() {
        boolean isValid = false;
        usuarioDao = new UsuarioDao(jndiConnectionName);
        idUsuario = usuarioDao.validateAccess(login, password, claveProyecto);

        if (idUsuario != 0) {
            usuario = usuarioDao.findById(idUsuario);
            if (usuario != null) {
                if (usuario.hasAccess(nivelMinimoAcceso)) {
                    isValid = true;
                    Date fechaSistema = DateUtil.getSystemDate();
                    Date fechaVence = null;
                    try {
                        fechaVence = DateUtil.parseDate(usuario.getFechaExpiraPass(),
                                CommonConstants.FECHA_FORMATO_YYYYMMDD);
                    } catch (ParseException e) {
                    }

                    if (fechaVence.compareTo(fechaSistema) > 0) {
                        // login exitoso
                        forward = "/content/home";
                    } else {
                        errorMessage = "La fecha de vigencia de su password ha expirado";
                        forward = "cambioPassword";
                    }
                } else {
                    errorMessage = "No cuenta con permisos para accesar a la aplicación";
                    forward = null;
                }
            }
        } else {
            errorMessage = "Su usuario o contraseña son incorrectos";
            forward = null;
        }
        return isValid;
    }

    public boolean isValidLogin(String successPath) {
        boolean isValid = false;
        usuarioDao = new UsuarioDao(jndiConnectionName);
        idUsuario = usuarioDao.validateAccess(login, password, claveProyecto);

        if (idUsuario != 0) {
            usuario = usuarioDao.findById(idUsuario);
            if (usuario != null) {
                if (usuario.hasAccess(nivelMinimoAcceso)) {
                    isValid = true;
                    Date fechaSistema = DateUtil.getSystemDate();
                    Date fechaVence = null;
                    try {
                        fechaVence = DateUtil.parseDate(usuario.getFechaExpiraPass(),
                                CommonConstants.FECHA_FORMATO_YYYYMMDD);
                    } catch (ParseException e) {
                    }

                    if (fechaVence.compareTo(fechaSistema) > 0) {
                        // login exitoso
                        forward = successPath;
                    } else {
                        errorMessage = "La fecha de vigencia de su password ha expirado";
                        forward = "cambioPassword";
                    }
                } else {
                    errorMessage = "No cuenta con permisos para accesar a la aplicación";
                    forward = null;
                }
            }
        } else {
            errorMessage = "Su usuario o contraseña son incorrectos";
            forward = null;
        }
        return isValid;
    }

    public UsuarioBean getUsuario() {
        return this.usuario;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getForward() {
        return forward;
    }
}
