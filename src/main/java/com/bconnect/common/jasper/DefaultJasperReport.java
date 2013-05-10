package com.bconnect.common.jasper;

import com.bconnect.common.db.ConnectionFactory;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author jorge.rodriguez
 */
public class DefaultJasperReport implements JasperReportInterface {

    private Map parameters;
    private Connection connection;
    private boolean isAbsoluteFilePath = false;
    private String filePath;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private JasperReport jasperReport = null;
    private JasperDesign jasperDesign = null;
    private boolean initialized = false;

    public DefaultJasperReport(Map parameters, Connection connection,
            String relativePath) {
        this.parameters = parameters;
        this.connection = connection;
        this.filePath = relativePath;
    }

    public DefaultJasperReport(Connection connection, String fileRelativePath) {
        this.connection = connection;
        this.filePath = fileRelativePath;
    }

    public DefaultJasperReport(String dbInstance, String fileRelativePath) {
        this.filePath = fileRelativePath;
        setConnection(dbInstance);
    }

    public void initialize(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.initialized = true;
    }

    public void generateReport() throws ReportException {
        if (!initialized) {
            throw new ReportException("No se puede generar el reporte porque no "
                    + "se ha inicializado el objeto");
        }
        try {
            jasperDesign = JRXmlLoader.load(getSourceFileName());
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport,
                    getParameters(), getConnection());
            OutputStream outStream = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength(byteStream.length);
            outStream.write(byteStream, 0, byteStream.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map getParameters() {
        if (parameters == null) {
            parameters = new HashMap();
        }
        return parameters;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSourceFileName() {
        if (!isAbsoluteFilePath) {
            filePath = request.getSession().getServletContext().getRealPath(filePath);
        }
        return filePath;
    }

    public void setConnection(String dbInstance) {
        try {
            this.connection = ConnectionFactory.newConnection(dbInstance);
        } catch (Exception e) {
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addParameter(Object key, Object value) {
        getParameters().put(key, value);
    }

    public void setRelativeJrxmlFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setAbsoluteJrxmlFilePath(String filePath) {
        this.filePath = filePath;
        isAbsoluteFilePath = true;
    }
}
