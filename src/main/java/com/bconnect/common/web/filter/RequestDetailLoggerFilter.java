package com.bconnect.common.web.filter;

import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author jorge.rodriguez
 */
public class RequestDetailLoggerFilter implements Filter {

    private FilterConfig filterConfig = null;
    private final String defaultFormat = "%20s\t %-20s\n ";
    private Logger logger = null;
    public static boolean detailedLog = false;

    public RequestDetailLoggerFilter() {
    }

    public void doFilter(ServletRequest req, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Throwable problem = null;
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            if (!request.getRequestURL().toString().contains("javax.faces.resource")) {
                if (detailedLog) {
                    printDetailedMessage(request);
                } else {
                    printSimpleMessage(request);
                }
            }
            chain.doFilter(req, response);
        } catch (Throwable t) {
            problem = t;
        }
    }

    private void printDetailedMessage(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        StringBuffer message = new StringBuffer("\n");
        message.append(getInfoMessage("Page", request.getRequestURL() + "?" + request.getQueryString()));
        message.append(getInfoMessage("Method", request.getMethod()));
        message.append(getInfoMessage("Query String", request.getQueryString()));
        message.append(getInfoMessage("Remote Address", request.getRemoteAddr()));
        message.append(getInfoMessage("Remote Host", request.getRemoteHost()));
        message.append(getInfoMessage("Remote Port", request.getRemotePort()));
        message.append(getInfoMessage("Remote URI", request.getRequestURI()));
        message.append(getInfoMessage("Remote URL", request.getRequestURL()));
        message.append(getInfoMessage("IP Address", ipAddress));

        message.append("\n");
        message.append(getInfoMessage("REQUEST HEADERS", "---------------"));
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = (String) headers.nextElement();
            message.append(getInfoMessage("- " + headerName, request.getHeader(headerName)));
        }

        message.append("\n");
        message.append(getInfoMessage("REQUEST PARAMETERS", "---------------"));
        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            message.append(getInfoMessage("- " + paramName, request.getParameter(paramName)));
        }

        logger.debug(message.toString());
    }

    private void printSimpleMessage(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        StringBuffer message = new StringBuffer();
        message.append(request.getRequestURL());
        message.append(" -- IP --> ");
        message.append(ipAddress);

        HttpSession session = request.getSession();
        UsuarioBean usuario = null;
        if ((usuario = (UsuarioBean) session.getAttribute(CommonConstants.ATRIBUTO_USUARIO)) != null) {
            message.append(" -- USER --> ");
            message.append(usuario.getLogin());
        }

        Enumeration params = request.getParameterNames();
        if (params.hasMoreElements()) {
            message.append("\n\tPARAMS --> ");
            while (params.hasMoreElements()) {
                String paramName = (String) params.nextElement();
                message.append(paramName);
                message.append("=");
                message.append(request.getParameter(paramName));
                message.append(", ");
            }
        }

        logger.debug(message.toString());
    }

    private String getInfoMessage(String name, Object value) {
        return String.format(defaultFormat, name, value, true);
    }

    private String getInfoMessage(String name, Object value, boolean addNewLine) {
        String end = addNewLine ? "\n" : "";
        return String.format(defaultFormat, name, value, end);
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        this.logger = CommonLogger.getLogger(this.getClass());
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RequestDetailLoggerFilter()");
        }
        StringBuffer sb = new StringBuffer("RequestDetailLoggerFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
