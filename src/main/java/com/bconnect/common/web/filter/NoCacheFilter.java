package com.bconnect.common.web.filter;

import com.bconnect.common.logging.CommonLogger;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author jorge.rodriguez
 */
public class NoCacheFilter implements Filter {

    private FilterConfig filterConfig = null;
    private Logger logger = null;

    public NoCacheFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest commonRequest, ServletResponse commonResponse,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) commonResponse;
        response.setHeader("CACHE-CONTROL", "NO-CACHE");
        response.setHeader("EXPIRES", "NOW");
        response.setHeader("PRAGMA", "NO-CACHE");
        try {
            chain.doFilter(commonRequest, commonResponse);
        } catch (Throwable t) {
            t.printStackTrace();
            logger.error("Ocurrió un error al procesar el filtro NO CACHE", t);
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
        this.logger = CommonLogger.getLogger(NoCacheFilter.class);
    }
}
