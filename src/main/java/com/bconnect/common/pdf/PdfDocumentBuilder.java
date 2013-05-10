package com.bconnect.common.pdf;

import com.bconnect.common.exception.PDFException;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.ServletContext;

/**
 *
 * @author Jorge Rodriguez
 */
public interface PdfDocumentBuilder {

    /**
     * La implementación para construir el PDF
     */
    public void buildDocument() throws PDFException;

    public void initialize(Document document, PdfWriter writer, ServletContext context);
}
