package com.bconnect.common.servlet;

import com.bconnect.common.web.filter.RequestDetailLoggerFilter;
import com.bconnect.common.util.CommonConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorge.rodriguez
 */
public class RequestDetailLoggerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String requestLogDetailParam = null;
        boolean newDetailLevel = false;
        try {

            if (request.getParameter(CommonConstants.PARAMETER_REQUEST_LOG_DETAIL_LEVEL) != null
                    && !CommonConstants.EMPTY_STRING.equals(request.getParameter(CommonConstants.PARAMETER_REQUEST_LOG_DETAIL_LEVEL))) {
                requestLogDetailParam = request.getParameter(CommonConstants.PARAMETER_REQUEST_LOG_DETAIL_LEVEL);
                newDetailLevel = "1".equals(requestLogDetailParam);
                RequestDetailLoggerFilter.detailedLog = newDetailLevel;
            }


            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cambio de Nivel de Log en Peticiones de usuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cambio de Nivel de Log en Peticiones de usuarios</h1>");
            String nivelDetalle = RequestDetailLoggerFilter.detailedLog ? "DETALLADO" : "SIMPLE";
            out.println("<h3 style='color: GREEN;'>El nivel de detalle actual del log es "
                    + nivelDetalle + "</h3>");
            out.println("<form action='requestLog' method='post'>");
            out.println("Nuevo Nivel: ");
            out.println("<select name='" + CommonConstants.PARAMETER_REQUEST_LOG_DETAIL_LEVEL + "'>");
            out.println("<option value='1'>DETALLADO</option>");
            out.println("<option value='0'>SIMPLE</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Modificar'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
