package com.bconnect.common.jasper;

import java.sql.Connection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.rodriguez
 */
public interface JasperReportInterface {

    public void initialize(HttpServletRequest request, HttpServletResponse response);

    public void generateReport() throws ReportException;

    public Map getParameters();

    public Connection getConnection();

    public String getSourceFileName();
}
