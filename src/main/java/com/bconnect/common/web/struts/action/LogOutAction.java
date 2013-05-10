package com.bconnect.common.web.struts.action;

import com.bconnect.common.util.CommonConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * Lleva a cabo la tarea de finalizaci�n de sesi�n en la aplicaci�n de un usuario.
 * Remueve el atributo Usuario de la sesi�n y finalmente invalida la sesi�n
 * actual.
 * @author Jorge Rodriguez
 */
public class LogOutAction extends org.apache.struts.action.Action {

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

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(CommonConstants.ATRIBUTO_USUARIO);
            session.invalidate();
            session = null;
        }

        return mapping.findForward(CommonConstants.FORWARD_SUCCESS);
    }
}