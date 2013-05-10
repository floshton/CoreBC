package com.bconnect.common.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.sql.Timestamp;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.DateUtil;

/**
 *
 * @author Jorge Rodriguez
 */
public class BConnectPdfDocumentPageEvent extends PdfDocumentBuilderUtil {

    public PdfPTable headTable;
    public PdfPTable bottomTable;
    /**
     * The Graphic state
     */
    public PdfGState gstate;
    /**
     * Se usará un template para guardar el número de hojas
     */
    public PdfTemplate tpl;

    public BConnectPdfDocumentPageEvent() {
        this.logger = CommonLogger.getLogger(this.getClass());
    }

    /**
     * Acciones a ejecutar al inicio del documento
     * @param writer the Writer instance
     * @param document the Document instance to be written
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            // initialization of the template
            tpl = writer.getDirectContent().createTemplate(100, 100);
        } catch (Exception e) {
            this.logger.error("Error al iniciar el Pdf", e);
            throw new ExceptionConverter(e);
        }
    }

    /**
     * Acciones a ejecutar cuando se termina cada página
     * @param writer instancia de Writer
     * @param document la instancia de Document que se imprimirá
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            float[] widths = new float[]{40f, 45f, 45f, 380f, 120f};
            bottomTable = makeTable(5, widths, 540f);
            float yPosition;
            Timestamp currentDate = new Timestamp(DateUtil.getSystemDate().getTime());

            // Check if it has image

            bottomTable.addCell(createEmptyCells(5));
            yPosition = document.bottom() - 40;

            bottomTable.addCell(makeCellGeneric("HOLA 3"));
            bottomTable.addCell(makeCellGeneric("HOLA 4"));
            bottomTable.addCell(createEmptyCells(1));

            bottomTable.writeSelectedRows(0, -1, 0, -1, 35f, yPosition, writer.getDirectContent());

            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();

            // compose the footer
            String text = "Página " + writer.getPageNumber() + " de ";
            float textSize = FONT7.getBaseFont().getWidthPoint(text, 8);
            float textBase = document.bottom() - 40;
            cb.beginText();
            cb.setFontAndSize(FONT7.getBaseFont(), 8);

            float adjust = FONT7.getBaseFont().getWidthPoint("0", 12);
            cb.setTextMatrix(document.right() - textSize - adjust, textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(tpl, document.right() - adjust, textBase);
            cb.saveState();
        } catch (Exception e) {
            logger.error("Ocurrio un error al terminar la pagina", e);
            e.printStackTrace();
        }

    }

    /**
     * Acciones a ejecutar cuando se inicia una nueva página
     * @param writer instancia de Writer
     * @param document la instancia de Document que se imprimirá
     */
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
    }

    /**
     * Acciones a ejecutarse cuando el documento está completo
     * @param writer instancia de Writer
     * @param document la instancia de Document que se imprimirá
     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        tpl.beginText();
        tpl.setFontAndSize(FONT7.getBaseFont(), 8);
        tpl.setTextMatrix(0, 0);
        tpl.showText("" + (writer.getPageNumber() - 1));
        tpl.endText();
    }
}
