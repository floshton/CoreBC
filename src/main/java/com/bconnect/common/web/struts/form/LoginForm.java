package com.bconnect.common.web.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Esta clase recibe y valida los datos de la forma de inicio de sesión
 * @author Jorge Rodriguez
 */
public class LoginForm extends org.apache.struts.action.ActionForm {

    protected String login;
    protected String password;
    protected String campaign;
    protected String extension;

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

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    /**
     *
     */
    public LoginForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Lleva a cabo la validación de login y password, los cuales no pueden ser
     * nulos ni cadenas vacías
     * @param mapping
     * @param request
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (login == null || login.equals("")) {
            errors.add("login", new ActionMessage("form.error.missing"));
        }
        if (password == null || password.equals("")) {
            errors.add("password", new ActionMessage("form.error.missing"));
        }
        
        //NOrmalmente comentar este if, solo cuanod se pide extension en el login
//        if (extension == null || extension.equals("")) {
//            errors.add("extension", new ActionMessage("form.error.missing"));
//        }
        /*if(campaign == null || campaign.equals("")){
        errors.add("campaign", new ActionMessage("form.error.missing"));
        }*/
        return errors;
    }
}
