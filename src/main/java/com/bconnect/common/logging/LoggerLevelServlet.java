package com.bconnect.common.logging;

import com.bconnect.common.util.CommonConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author jorge.rodriguez
 */
public class LoggerLevelServlet extends HttpServlet {

    private Logger logger;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean success = false;
        String loggerLevel = null;
        Logger rootLogger = null;
        try {
            rootLogger = Logger.getRootLogger();
            
            if (request.getParameter(CommonConstants.PARAMETER_LOGGER_LEVEL) != null &&
                    !CommonConstants.EMPTY_STRING.equals(request.getParameter(CommonConstants.PARAMETER_LOGGER_LEVEL))) {
                loggerLevel = request.getParameter(CommonConstants.PARAMETER_LOGGER_LEVEL);
                LoggerUtil.logEveryLevel(logger, "---- Se intenta actualizar el nivel de Log4j a " + loggerLevel);
                rootLogger.setLevel(LoggerUtil.getLoggerLevel(loggerLevel));
                success = true;
                LoggerUtil.logEveryLevel(logger, "---- Se actualiza el nivel de Log4j a " + loggerLevel + " exitosamente");
            }

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cambio de Nivel de Logger</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cambio de Nivel de Logger</h1>");
            if (success) {
                out.println("<h3 style='color: RED;'>El nivel se cambió exitosamente a " + loggerLevel + "</h3>");
            } else {
                out.println("<h3 style='color: GREEN;'>El nivel actual del log es " + rootLogger.getLevel() + "</h3>");
            }
            out.println("<form action='LoggerConsole.logger' method='post'>");
            out.println("Nuevo Nivel: ");
            out.println("<select name='" + CommonConstants.PARAMETER_LOGGER_LEVEL + "'>");
            out.println("<option value=''>SELECCIONE UN NIVEL</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_FATAL + "'>" + CommonConstants.LOGGER_LEVEL_FATAL + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_ERROR + "'>" + CommonConstants.LOGGER_LEVEL_ERROR + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_WARN + "'>" + CommonConstants.LOGGER_LEVEL_WARN + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_INFO + "'>" + CommonConstants.LOGGER_LEVEL_INFO + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_DEBUG + "'>" + CommonConstants.LOGGER_LEVEL_DEBUG + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_TRACE + "'>" + CommonConstants.LOGGER_LEVEL_TRACE + "</option>");
            out.println("<option value='" + CommonConstants.LOGGER_LEVEL_OFF + "'>" + CommonConstants.LOGGER_LEVEL_OFF + "</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Modificar'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            logger.error("Error al procesar el Servlet de cambio de nivel de Lo4j", e);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para controlar el nivel de logueo en aplicaciones";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        super.init();
        logger = CommonLogger.getLogger(this.getClass());
    }
}
