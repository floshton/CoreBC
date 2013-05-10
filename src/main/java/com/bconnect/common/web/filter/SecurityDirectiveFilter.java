/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bconnect.common.web.filter;

import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author daniel.carbajal
 */
public class SecurityDirectiveFilter implements Filter {

    private Logger logger = null;
    private FilterConfig filterConfig = null;

    public SecurityDirectiveFilter() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) res;
            response.addHeader(CommonConstants.HEADER_DIRECTIVA_COMPACTA,
                    CommonConstants.HEADER_DIRECTIVA_COMPACTA_VALOR);

            chain.doFilter(req, res);
        } catch (Throwable t) {
            logger.error("Error al procesar el filtro de directivas", t);
        }
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
}
