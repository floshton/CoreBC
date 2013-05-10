package com.bconnect.common.web.struts.action;

import com.bconnect.common.web.struts.form.ChangePasswordForm;
import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.dao.UsuarioDao;
import com.bconnect.common.util.CommonConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * Esta acción habilita la posibilidad para que el usuario cambie su contraseña,
 * procesando los parámetros recibidos de la forma y haciendo una llamada a un
 * stored procedure.
 * Una vez efectuada la actualización, invalida la sesión actual y redirige al
 * usuario a la pantalla de login para que inicie sesión con sus nuevos datos.
 * @author Jorge Rodriguez
 */
public class ChangePasswordAction extends org.apache.struts.action.Action {
    
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
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session = request.getSession();
        UsuarioBean usuario = 
                (UsuarioBean)session.getAttribute(CommonConstants.ATRIBUTO_USUARIO);
        
        ChangePasswordForm passwordForm = (ChangePasswordForm)form;
        
        UsuarioDao usuarioDao = new UsuarioDao(CommonConstants.DB_INFORMIX_CATALOGO);
        
        boolean success = usuarioDao.changePassword(usuario.getIdUsuario(), 
                passwordForm.getNewPassword());
        if(success){
            session.removeAttribute(CommonConstants.ATRIBUTO_USUARIO);
            session.invalidate();
        }
        
        return mapping.findForward(CommonConstants.FORWARD_SUCCESS);
     }
}