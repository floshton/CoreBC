package com.bconnect.common.web.struts.action.outbound;

import com.bconnect.common.bean.personal.ClienteBConnectBean;
import com.bconnect.common.dao.outbound.ClienteBConnectDao;
import com.bconnect.common.util.CommonConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Jorge Rodriguez
 */
public class MuestraDatosClienteAction extends org.apache.struts.action.Action {

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

        String numeroCliente = request.getParameter(CommonConstants.PARAMETER_OUTBOUND_ID_CLIENTE);
        String usuario = request.getParameter(CommonConstants.PARAMETER_OUTBOUND_USUARIO);
        String tipoRegistro = request.getParameter(CommonConstants.PARAMETER_OUTBOUND_TIPO_REGISTRO);
        String claveProyecto = request.getParameter(CommonConstants.PARAMETER_OUTBOUND_CLAVE_PROYECTO);

        ClienteBConnectBean cliente = null;
        ClienteBConnectDao clienteDao = new ClienteBConnectDao();

        if (tipoRegistro.equals(CommonConstants.OUTBOUND_TIPO_REGISTRO_BASE)) {
            cliente = clienteDao.findInDespachoByNumCliente(numeroCliente);
        }

        session.setAttribute(CommonConstants.ATRIBUTO_USUARIO, usuario);
        if (cliente != null) {
            cliente.setProyecto(claveProyecto);
            cliente.setTipoRegistro(tipoRegistro);
            session.setAttribute(CommonConstants.ATRIBUTO_CLIENTE_B_CONNECT, cliente);
        }

        return mapping.findForward(CommonConstants.FORWARD_SUCCESS);

    }
}
