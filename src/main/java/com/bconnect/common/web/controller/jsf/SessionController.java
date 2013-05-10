package com.bconnect.common.web.controller.jsf;

import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.dao.UsuarioDao;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;
import com.bconnect.common.util.JsfUtils;
import com.bconnect.common.web.struts.action.LoginActionHelper;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Rodriguez
 */
public class SessionController implements Serializable {

    private String usuario;
    private String oldPassword;
    private String password;
    private String newPassword;
    private String extension;
    private String redirectPage;

    public SessionController() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }

    public String login() {
        String claveProyecto = JsfUtils.getRequestParameter("claveProyecto");
        int nivelMinimoAcceso = JsfUtils.getRequestParameterInt("nivelMinimoAcceso");

        LoginActionHelper loginHelper = new LoginActionHelper(usuario, password, claveProyecto, nivelMinimoAcceso);

        if (loginHelper.isValidLogin()) {
            if (CommonUtils.hasValue(extension)) {
                loginHelper.getUsuario().setExtension(extension);
            }
            JsfUtils.setSessionMapValue(CommonConstants.ATRIBUTO_USUARIO, loginHelper.getUsuario());
            JsfUtils.setSessionMapValue(CommonConstants.ATRIBUTO_PROYECTO, claveProyecto);
            if ("cambioPassword".equals(loginHelper.getForward())) {
                JsfUtils.addErrorMessage(loginHelper.getErrorMessage());
                oldPassword = new String(password);
                password = null;
            }
        }
        return loginHelper.getForward();

    }

    public String loginWithCustomPath() {
        String claveProyecto = JsfUtils.getRequestParameter("claveProyecto");
        int nivelMinimoAcceso = JsfUtils.getRequestParameterInt("nivelMinimoAcceso");
        String homePage = JsfUtils.getRequestParameter("homePage");

        LoginActionHelper loginHelper = new LoginActionHelper(usuario, password, claveProyecto, nivelMinimoAcceso);

        if (loginHelper.isValidLogin(homePage)) {
            if (CommonUtils.hasValue(extension)) {
                loginHelper.getUsuario().setExtension(extension);
            }
            JsfUtils.setSessionMapValue(CommonConstants.ATRIBUTO_USUARIO, loginHelper.getUsuario());
            JsfUtils.setSessionMapValue(CommonConstants.ATRIBUTO_PROYECTO, claveProyecto);
            if ("cambioPassword".equals(loginHelper.getForward())) {
                JsfUtils.addErrorMessage(loginHelper.getErrorMessage());
                oldPassword = new String(password);
                password = null;
            }
        } else {
            JsfUtils.addErrorMessage(loginHelper.getErrorMessage());
        }
        if (CommonUtils.hasValue(redirectPage)) {
            return JsfUtils.redirectResponse(redirectPage);
        } else {
            return loginHelper.getForward();
        }
    }

    public String cambiaPassword() {
        if (!isValid(password)) {
            JsfUtils.addErrorMessage("El password debe contener 2 letras y 2 numeros por lo menos");
            return null;
        } else if (!password.equals(newPassword)) {
            JsfUtils.addErrorMessage("Los passwords deben coincidir");
            return null;
        } else if (password.equals(oldPassword)) {
            JsfUtils.addErrorMessage("El password no puede ser igual al anterior");
            return null;
        } else {
            UsuarioBean usuarioBean =
                    (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);

            UsuarioDao usuarioDao = new UsuarioDao(CommonConstants.DB_INFORMIX_CATALOGO);

            boolean success = usuarioDao.changePassword(usuarioBean.getIdUsuario(), newPassword);
            if (success) {
                JsfUtils.addInfoMessage("El password se actualizó exitosamente");
                JsfUtils.removeSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                if (session != null) {
                    session.invalidate();
                    session = null;
                }
            }
            return "/login";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.removeAttribute(CommonConstants.ATRIBUTO_USUARIO);
            session.invalidate();
            session = null;
        }
        return "/login";
    }

    private boolean isValid(String password) {
        int numbersCounter = 0;
        int lettersCounter = 0;
        boolean validLetters = false;
        boolean validNumbers = false;
        String numberString = "012345679";
        String letterUpperString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letterLowerString = "abcdefghijklmnopqrstuvwxyz";

        char[] passwordArray = password.toCharArray();
        char[] numbers = numberString.toCharArray();
        char[] lettersUpper = letterUpperString.toCharArray();
        char[] lettersLower = letterLowerString.toCharArray();

        for (int i = 0; i < passwordArray.length; i++) {
            char c = passwordArray[i];
            for (int j = 0; j < lettersLower.length; j++) {
                if (c == lettersLower[j]) {
                    lettersCounter++;
                }
            }
            for (int k = 0; k < lettersUpper.length; k++) {
                if (c == lettersUpper[k]) {
                    lettersCounter++;
                }
            }
            for (int m = 0; m < numbers.length; m++) {
                if (c == numbers[m]) {
                    numbersCounter++;
                }
            }
        }
        if (lettersCounter >= 2) {
            validLetters = true;
        }
        if (numbersCounter >= 2) {
            validNumbers = true;
        }
        return (validLetters && validNumbers);
    }
}
