package com.bconnect.common.pdf;

import com.bconnect.common.exception.PDFException;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.logging.CommonLogger;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfException;
import com.lowagie.text.pdf.PdfWriter;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class PdfServlet extends HttpServlet {
    
    private Logger logger;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Document document = new Document();
        PdfWriter writer = null;
        OutputStream out = null;
        ServletContext context =  null;

        try {
            context = this.getServletContext();
            response.setContentType("application/pdf");
            out = response.getOutputStream();
            writer = PdfWriter.getInstance(document, out);

            PdfDocumentBuilder pdfBuilder = (PdfDocumentBuilder)request.getAttribute(
                    CommonConstants.ATRIBUTO_PDF_BUILDER);
            
            pdfBuilder.initialize(document, writer, context);
            pdfBuilder.buildDocument();
            
        } catch (PdfException pe) {
            pe.printStackTrace();
            this.logger.error("Hubo un problema para procesar el documento", pe);
        } catch (DocumentException de) {
            de.printStackTrace();
            this.logger.error("Hubo un problema para procesar el documento", de);
        } catch (PDFException pdfe) {
            pdfe.printStackTrace();
            this.logger.error("Hubo un problema para procesar el documento", pdfe);
        }

        document.close();
        writer.close();
        out.flush();
        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    @Override
    public void init(){
        logger = CommonLogger.getLogger(this.getClass());
    }
    // </editor-fold>
}
