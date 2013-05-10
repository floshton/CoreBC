package com.bconnect.common.chart;

import com.bconnect.common.util.CommonConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Jorge Rodriguez
 */
public class DefaultChartBuilderServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = this.getServletContext();
        OutputStream out = response.getOutputStream();

        try {
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("MEG 248", 40);//(conteoMEG + conteoTradicional) / (conteoMEG * 100));
            pieDataset.setValue("Tradicional", 60);//(conteoMEG + conteoTradicional) / (conteoTradicional * 100));

            JFreeChart pieChart = ChartFactory.createPieChart3D("Ventas MEG248 vs Tradicional", pieDataset, false, false, false);
            pieChart.setBackgroundImageAlpha(1);
            PiePlot3D piePlot = (PiePlot3D) pieChart.getPlot();
            piePlot.setStartAngle(38d);
            piePlot.setDepthFactor(0.20d);

            piePlot.setForegroundAlpha(0.8f);
            piePlot.setBackgroundImageAlpha(0.1f);
            piePlot.setDarkerSides(true);

            ChartProperties chartProps1 = new ChartProperties(pieChart, 80, 35);


            ChartProperties chartProperties = null;
            String indexChartString = request.getParameter(CommonConstants.PARAMETER_INDEX_CHART_PROPERTIES);
            String imageWidthString = request.getParameter(CommonConstants.PARAMETER_CHART_IMAGE_WIDTH);
            String imageHeightString = request.getParameter(CommonConstants.PARAMETER_CHART_IMAGE_HEIGHT);

            int imageWidth = 0;
            int imageHeight = 0;

            if (indexChartString == null) {
                indexChartString = "0";
            }

            int indexChart = Integer.parseInt(indexChartString);
            List listChartProperties = null;

            if (request.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES) != null) {
                chartProperties = (ChartProperties) request.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES);
            } else if (session.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES) != null) {
                chartProperties = (ChartProperties) session.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES);
            } else if (context.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES) != null) {
                chartProperties = (ChartProperties) context.getAttribute(CommonConstants.ATRIBUTO_CHART_PROPERTIES);
            }

            if (chartProperties == null) {
                if (request.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES) != null) {
                    listChartProperties = (List) request.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES);
                } else if (session.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES) != null) {
                    listChartProperties = (List) session.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES);
                } else if (context.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES) != null) {
                    listChartProperties = (List) context.getAttribute(CommonConstants.ATRIBUTO_LIST_CHART_PROPERTIES);
                }
                chartProperties = (ChartProperties) listChartProperties.get(indexChart);
            }

            imageWidth = imageWidthString != null ? Integer.parseInt(imageWidthString) : chartProperties.getWidth();
            imageHeight = imageHeightString != null ? Integer.parseInt(imageHeightString) : chartProperties.getHeight();

            String contentType = CommonConstants.CONTENT_TYPE_IMAGE_JPEG;
            String imageExtension = request.getParameter(CommonConstants.PARAMETER_CHART_IMAGE_EXTENSION) != null
                    ? request.getParameter(CommonConstants.PARAMETER_CHART_IMAGE_EXTENSION) : CommonConstants.FILE_EXTENSION_JPG;

            if (imageExtension.equals(CommonConstants.FILE_EXTENSION_PNG)) {
                contentType = CommonConstants.CONTENT_TYPE_IMAGE_PNG;
                response.setContentType(contentType);
                ChartUtilities.writeChartAsPNG(out, chartProperties.getChart(),
                        imageWidth, imageHeight);
            } else if (imageExtension.equals(CommonConstants.FILE_EXTENSION_JPG)) {
                contentType = CommonConstants.CONTENT_TYPE_IMAGE_JPEG;
                response.setContentType(contentType);
                ChartUtilities.writeChartAsJPEG(out, chartProperties.getChart(),
                        imageWidth, imageHeight);
            }
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
