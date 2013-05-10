package com.bconnect.common.web.struts.action;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;
import com.bconnect.common.util.ResourcesManager;
import com.bconnect.common.web.struts.form.LoginForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * Valida al usuario para su acceso a la aplicacion. De estar autorizado, 
 * coloca como atributo en sesion una instancia de Usuario y dirige el 
 * request a registroParticipante.jsp. De lo contrario, muestra nuevamente 
 * la pantalla de login.jsp
 * @author Jorge Rodriguez
 */
public class LoginAction extends org.apache.struts.action.Action {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        LoginForm loginForm = (LoginForm) form;

        String login = loginForm.getLogin();
        String password = loginForm.getPassword();
        String extension = loginForm.getExtension();

        String claveProyecto = ResourcesManager.getApplicationResource().getString(
                CommonConstants.PROJECT_CODE);

        LoginActionHelper loginHelper = new LoginActionHelper(login, password, claveProyecto);
        String forward = loginHelper.validateLogin();

        if (forward.equals(CommonConstants.FORWARD_SUCCESS)
                || forward.equals(CommonConstants.FORWARD_CHANGE_PASSWORD)) {
            if (CommonUtils.hasValue(extension)) {
                loginHelper.getUsuario().setExtension(extension);
            }
            session.setAttribute(CommonConstants.ATRIBUTO_USUARIO,
                    loginHelper.getUsuario());
            session.setAttribute(CommonConstants.ATRIBUTO_PROYECTO,
                    claveProyecto);
        } else {
            request.setAttribute(CommonConstants.ATRIBUTO_ERROR_LOGIN,
                    loginHelper.getErrorMessage());
            return mapping.getInputForward();
        }
        return mapping.findForward(forward);
    }
}
