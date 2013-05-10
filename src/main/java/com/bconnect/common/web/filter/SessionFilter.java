package com.bconnect.common.web.filter;

import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.dao.UsuarioDao;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Esta clase que implementa la interfaz Filter sirve como verificador
 * de que cualquier pagina protegida, esto es, dentro de la carpeta JSP solo 
 * sea accesible para usuarios que cuenten con una sesion activa, una vez que
 * han pasado por la pantalla de login. De no estar en sesion, lo redirije a la
 * pagina en la que lo pueda hacer
 * @author Jorge Rodriguez
 */
public class SessionFilter implements Filter {

    private Logger logger;
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        logger = CommonLogger.getLogger(this.getClass());
        this.config = config;
    }

    /**
     * Este método es invocado cada vez que se hace una petición al path 
     * asociado con este filtro. Valida si ya hay una sesión activa. En caso de 
     * sí haberla, dirige al usuario al recurso. En caso contrario, lo redirige
     * a la pantalla de login para iniciar sesión
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            String login = request.getParameter("strCreadoPor");
            if (login == null || "".equals(login)) {
                login = request.getParameter("STRCREADOPOR");
            }

            if (!validateUserExists(session) && !validateUserExists(session, login)) {
                ServletContext context = config.getServletContext();
                context.getRequestDispatcher(getFullUri(httpRequest)).forward(request, response);
                logger.debug("El usuario no esta logueado");
                return;
            } else {
                UsuarioBean usuario = (UsuarioBean) session.getAttribute(
                        CommonConstants.ATRIBUTO_USUARIO);
                logger.trace("USUARIO - REQUEST - " + usuario.getLogin());
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFullUri(HttpServletRequest request) {
        StringBuilder uri = new StringBuilder("/");
        uri.append(getHomePage());
        if (getRedirectPage(request) != null && getRedirectPage(request).contains("content")) {
            uri.append("?redirectPage=");
            uri.append(getRedirectPage(request));
        }
        return uri.toString();
    }

    private String getHomePage() {
        String homePage = "index.jsp";
        if (config.getInitParameter("homePage") != null) {
            homePage = config.getInitParameter("homePage");
        }
        return homePage;
    }

    private String getRedirectPage(HttpServletRequest request) {
        StringBuilder page = new StringBuilder(request.getRequestURI());
        if (CommonUtils.hasValue(request.getQueryString())) {
            page.append("?");
            page.append(request.getQueryString());
        }
        return page.toString();
    }

    /**
     * Evalua si existe una instancia de Usuario en sesion, como atributo. Este
     * atributo se crea al momento de login. Sino existe, significa que aun no 
     * se ha logueado
     * @param session
     * @return
     */
    private boolean validateUserExists(HttpSession session) {
        boolean userExists = false;

        if (session != null) {
            UsuarioBean usuario = (UsuarioBean) session.getAttribute(
                    CommonConstants.ATRIBUTO_USUARIO);

            if (usuario != null) {
                userExists = true;
            }
        }
        return userExists;
    }

    protected boolean validateUserExists(HttpSession session, String login) {
        boolean userExists = false;

        if (login != null) {
            UsuarioDao usuarioDao = new UsuarioDao();
            UsuarioBean usuario = usuarioDao.findByLogin(login);
            if (usuario != null) {
                session.setAttribute(CommonConstants.ATRIBUTO_USUARIO, usuario);
                userExists = true;
            }
        }
        return userExists;
    }

    public void destroy() {
    }
}
