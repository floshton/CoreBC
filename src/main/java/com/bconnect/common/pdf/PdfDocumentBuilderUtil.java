package com.bconnect.common.pdf;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.logging.CommonLogger;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public abstract class PdfDocumentBuilderUtil extends PdfPageEventHelper {
// Deviation Request Form

    protected static final Font FONT1 = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, java.awt.Color.BLACK);
    protected static final Font FONT2 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, java.awt.Color.BLACK);
    protected static final Font FONT3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, java.awt.Color.BLACK);
    // Same as H3, not BOLD. 
    protected static final Font FONT4 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, java.awt.Color.BLACK);
    // A bit smaller than H4, used in Stop Info table cells and Underlined cells
    protected static final Font FONT5 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL, java.awt.Color.BLACK);
    // Smaller bold, used in Stop Info table headings and in second page
    protected static final Font FONT6 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, java.awt.Color.BLACK);
    // Smallest, used in field names, in GM form
    protected static final Font FONT7 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, java.awt.Color.BLACK);
    protected static final Font FONT8 = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, java.awt.Color.BLACK);
    protected static final Font FONT9 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, java.awt.Color.BLACK);
    protected static Font FONT_BAR_CODE;
    protected static Font FONT_BAR_CODE_SMALL;
    protected Document document;
    protected PdfWriter writer;
    protected ServletContext context;
    protected Logger logger = CommonLogger.getLogger(PdfDocumentBuilderUtil.class);
    protected boolean initialized;

    protected BaseFont getCustomFont(String relativePath, boolean isEmbedded)
            throws DocumentException, IOException {
        String fontPath = context.getRealPath(relativePath);
        boolean useType = false;
        if (isEmbedded) {
            useType = BaseFont.EMBEDDED;
        }
        BaseFont baseFont = BaseFont.createFont(fontPath, "ISO-8859-1", useType);

        return baseFont;
    }

    protected BaseFont getCustomFont(String relativePath)
            throws DocumentException, IOException {
        String fontPath = context.getRealPath(relativePath);
        BaseFont baseFont = BaseFont.createFont(fontPath, "ISO-8859-1", BaseFont.EMBEDDED);

        return baseFont;
    }

    private void initializeSpecialFonts() throws DocumentException, IOException {
        String fontsFolder = "WEB-INF/fonts/";
        StringBuffer fontPath = new StringBuffer(fontsFolder);
        fontPath.append("3OF9.TTF");
        BaseFont baseFontBarCode = this.getCustomFont(fontPath.toString());
        FONT_BAR_CODE = new Font(baseFontBarCode, 15, Font.NORMAL, java.awt.Color.BLACK);
    }

    /**
     * Constructor de la clase
     */
    public PdfDocumentBuilderUtil(Document pdfDocument, PdfWriter writer, PdfPageEvent event) {
        this.document = pdfDocument;
        this.writer = writer;

        this.setPageEvent(event);
    }

    public PdfDocumentBuilderUtil(Document pdfDocument, PdfWriter writer) {
        this.document = pdfDocument;
        this.writer = writer;
    }

    /**
     * An empty class constuctor
     */
    public PdfDocumentBuilderUtil() {
    }

    public void initialize(Document document, PdfWriter writer, ServletContext context) {
        this.context = context;
        this.document = document;
        this.writer = writer;
        try {
            this.initializeSpecialFonts();
        } catch (DocumentException de) {
            logger.error("Error al inicializar las fuentas especiales", de);
        } catch (IOException ioe) {
            logger.error("Error al inicializar las fuentas especiales", ioe);
        }
    }

    /**
     * Hace el seteo inicial de todas las propiedades del documento
     */
    protected void initializeDocument() {
        this.initializeDocument("HI");
    }

    protected void initializeDocument(String title) {
        this.initializeDocument(title, 60, 90);
    }

    protected void initializeDocument(String title, int verticalMargin, int horizontalMargin) {
        this.initializeDocument(title, PageSize.LETTER, verticalMargin, horizontalMargin,
                verticalMargin, horizontalMargin);
    }

    protected void initializeDocument(String title, Rectangle pageSize, int topMargin,
            int rightMargin, int bottomMargin, int leftMargin) {
        document.addAuthor(this.getClass().getName());
        document.addCreator(this.getClass().getName());
        document.addCreationDate();
        document.addTitle(title);
        document.setPageSize(pageSize);
        document.setMargins(leftMargin, rightMargin, topMargin, bottomMargin);

        document.open();
        initialized = true;
    }

    /**
     * This method defines the PdfPageEvent class that will handle the document
     * creation. These events include onEndPage and onCloseDocument, among others
     */
    protected void setPageEvent(PdfPageEvent event) {
        this.writer.setPageEvent(event);
    }

    protected Paragraph makeParagraph(String text, int alignment) throws DocumentException {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    protected Paragraph makeParagraph(String text, int alignment, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    protected Font getFont(String fontFamily, int fontSize, int fontWeight) {
        Font theFont = FontFactory.getFont(fontFamily, fontSize,
                fontWeight, java.awt.Color.BLACK);
        return theFont;
    }

    /**
     * This method returns a Rectangle instance, which is used to determine a
     * cell's borders
     * @param topBorder the visibility of the Top Border
     * @param rightBorder the visibility of the Right Border
     * @param bottomBorder the visibility of the Bottom Border
     * @param leftBorder the visibility of the Left Border
     * @param width the tickness of the border
     * @return a Rectangle instance that contains the borders definition
     */
    protected Rectangle setBorders(boolean topBorder, boolean rightBorder,
            boolean bottomBorder, boolean leftBorder, float width) {
        Rectangle border = new Rectangle(0f, 0f);
        border.setBorderWidthLeft(0f);
        border.setBorderWidthBottom(0f);
        border.setBorderWidthRight(0f);
        border.setBorderWidthTop(0f);
        if (topBorder) {
            border.setBorderWidthTop(width);
        }
        if (rightBorder) {
            border.setBorderWidthRight(width);
        }
        if (bottomBorder) {
            border.setBorderWidthBottom(width);
        }
        if (leftBorder) {
            border.setBorderWidthLeft(width);
        }
        return border;
    }

    protected Rectangle setBorders(boolean allBorders, float width) {
        return this.setBorders(allBorders, allBorders, allBorders, allBorders, width);
    }

    /**
     * This method is used to print a table
     * @param rows the number of rows
     * @param colWidths an array that specifies the width of every column
     * @param totalWidth the table width
     * @return a PdfPTable instance
     */
    protected PdfPTable makeTable(int rows, float[] colWidths, float totalWidth) {
        PdfPTable table = new PdfPTable(rows);
        try {
            table.setWidths(colWidths);
            table.setTotalWidth(totalWidth);
        } catch (DocumentException ex) {
            logger.error("Could not create a PDFPTable", ex);
            ex.printStackTrace();
        }
        return table;
    }

    /**
     * This method is used to print a table
     * @param colWidths an array that specifies the width of every column
     * @param totalWidth the table width
     * @return a PdfPTable instance
     */
    protected PdfPTable makeTable(float[] colWidths, float totalWidth) {
        PdfPTable table = new PdfPTable(colWidths.length);
        try {
            table.setWidths(colWidths);
            table.setTotalWidth(totalWidth);
        } catch (DocumentException ex) {
            logger.error("Could not create a PDFPTable", ex);
            ex.printStackTrace();
        }
        return table;
    }

    /**
     * This method is used to print a table
     * @param colWidths an array that specifies the width of every column
     * @return a PdfPTable instance
     */
    protected PdfPTable makeTable(float... colWidths) {
        PdfPTable table = new PdfPTable(colWidths.length);
        try {
            float totalWidth = 0f;
            for (int i = 0; i < colWidths.length; i++) {
                totalWidth += colWidths[i];
            }
            table.setWidths(colWidths);
            table.setTotalWidth(totalWidth);
            table.setWidthPercentage(100);
        } catch (DocumentException ex) {
            logger.error("Could not create a PDFPTable", ex);
            ex.printStackTrace();
        }
        return table;
    }

    protected PdfPTable makeTable(int numberOfCols) {
        PdfPTable table = new PdfPTable(numberOfCols);
        table.setWidthPercentage(100);
        return table;
    }

    /**
     * This method is used to print a table cell
     * @param text the text that will appear in the cell
     * @param font the Font to be used
     * @param vAlignment a number that specifies the vertical alignment
     * @param hAlignment a number that specifies the horizontal alignment
     * @param padding the cell padding
     * @param borders a Rectangle instance that contains borders information
     * @param colSpan the cell colspan
     * @return a PdfPCell instance 
     */
    protected PdfPCell makeCell(String text, Font font, int vAlignment,
            int hAlignment, float padding, Rectangle borders, int colSpan) {
        Paragraph p = new Paragraph(text, font);
        PdfPCell cell = new PdfPCell(p);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        return cell;
    }
    
    protected PdfPCell makeCell(PdfPTable table, int vAlignment,
            int hAlignment, float padding, Rectangle borders, int colSpan) {
        PdfPCell cell = new PdfPCell(table);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        return cell;
    }

    protected PdfPCell makeCell(String text, Font font, int vAlignment,
            int hAlignment, float padding, Rectangle borders, int colSpan,
            Color color) {
        Paragraph p = new Paragraph(text, font);
        PdfPCell cell = new PdfPCell(p);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        cell.setBackgroundColor(color);
        return cell;
    }

    protected PdfPCell makeCellTable(PdfPTable table, int vAlignment,
            int hAlignment, float padding, Rectangle borders, int colSpan) {

        PdfPCell cell = new PdfPCell(table);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        return cell;
    }

    protected PdfPCell makeCellTable(PdfPTable table) {

        PdfPCell cell = new PdfPCell(table);
        Rectangle borders = setBorders(false, 1f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(1f);
        cell.setColspan(1);
        return cell;
    }

    /**
     * This method is used to print a table cell
     * @param image the image that will appear in the cell
     * @param font the Font to be used
     * @param vAlignment a number that specifies the vertical alignment
     * @param hAlignment a number that specifies the horizontal alignment
     * @param padding the cell padding
     * @param borders a Rectangle instance that contains borders information
     * @param colSpan the cell colspan
     * @return a PdfPCell instance 
     */
    protected PdfPCell makeCell(Image image, Font font, int vAlignment,
            int hAlignment, float padding, Rectangle borders, int colSpan) {
        PdfPCell cell = new PdfPCell(image);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.cloneNonPositionParameters(borders);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        return cell;
    }

    /**
     * This method is used to print a table cell
     * @param text the text that will appear in the cell
     * @param font the Font to be used
     * @param vAlignment a number that specifies the vertical alignment
     * @param hAlignment a number that specifies the horizontal alignment
     * @param padding the cell padding
     * @param colSpan the cell colspan
     * @return a PdfPCell instance 
     */
    protected PdfPCell makeCell(String text, Font font, int vAlignment,
            int hAlignment, float padding, int colSpan) {
        Paragraph p = new Paragraph(text, font);
        PdfPCell cell = new PdfPCell(p);
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setUseBorderPadding(true);
        cell.setPadding(padding);
        cell.setColspan(colSpan);
        return cell;
    }

    protected PdfPCell makeCellUnderlinded(String text) {
        Rectangle borders = setBorders(false, false, true, false, 1f);
        PdfPCell cell = makeCell(text, FONT6, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellLabel(String text) {
        Rectangle borders = setBorders(false, false, false, false, 0f);
        PdfPCell cell = makeCell(text, FONT3, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, 1f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellTableHeader(String text) {
        Rectangle borders = setBorders(false, false, false, false, 0f);
        PdfPCell cell = makeCell(text, FONT4, Element.ALIGN_BOTTOM, Element.ALIGN_CENTER, 0f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGeneric(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericBordersAlignLeft(String text) {
        Rectangle borders = setBorders(true, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericAlignLeft(String text) {
        Rectangle borders = setBorders(false, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericBorders(String text) {
        Rectangle borders = setBorders(true, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericBorders(String text, Integer colSpan) {
        Rectangle borders = setBorders(true, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2f, borders, colSpan);
        return cell;
    }

    protected PdfPCell makeCellGenericBold(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT8, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericBoldAlignRight(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT8, Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericCenter(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT5, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellGenericBoldCenter(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT8, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellUnderlindedData(String text) {
        Rectangle borders = setBorders(false, false, true, false, 1f);
        PdfPCell cell = makeCell(text, FONT9, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, 2f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellBigLabel(String text) {
        Rectangle borders = setBorders(false, false, false, false, 0f);
        PdfPCell cell = makeCell(text, FONT2, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, 1f, borders, 1);
        return cell;
    }

    public PdfPCell createEmptyCells(Integer colspan) {
        PdfPCell cell = new PdfPCell(this.makeCellGenericCenter(CommonConstants.SPACE_STRING));
        cell.setColspan(colspan);
        return cell;
    }

    public PdfPCell createEmptyUnderlineCells(Integer colspan) {
        PdfPCell cell = new PdfPCell(this.makeCellUnderlinded(CommonConstants.SPACE_STRING));
        cell.setColspan(colspan);
        return cell;
    }

    // A text with small text. No Borders. Centered
    protected PdfPCell makeCellSmallTextCenteredNB(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_CENTER, 1.6f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellSmallTextRight(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_RIGHT, 1.6f, borders, 1);
        return cell;
    }

    protected PdfPCell makeCellSmallText(String text) {
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_CENTER, 1.6f, 1);
        return cell;
    }

    // Overloaded method. Allows COLSPAN
    protected PdfPCell makeCellSmallText(String text, int colSpan) {
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_CENTER, 1.6f, colSpan);
        return cell;
    }

    protected PdfPCell makeCellSmallTextLeft(String text, int colSpan) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, 1.6f, borders, colSpan);
        return cell;
    }

    protected PdfPCell makeCellSmallTextLeft(Image image, int colSpan) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(image, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, 1.6f, borders, colSpan);
        return cell;
    }

    // A text with small text. No Borders. Justified
    protected PdfPCell makeCellSmallTextNB(String text) {
        Rectangle borders = setBorders(false, false, false, false, 1f);
        PdfPCell cell = makeCell(text, FONT7, Element.ALIGN_BOTTOM, Element.ALIGN_JUSTIFIED, 1.6f, borders, 1);
        return cell;
    }

    /**
     * Este método define una imagen para ser pintada en el PDF
     * @param filename el URL relativo de la imagen
     * @return una instancia de Image
     */
    protected Image getImage(String filename) {
        Image image = null;
        try {
            image = Image.getInstance(filename);
        } catch (MalformedURLException ex) {
            logger.error("URL erroneo de la imagen", ex);
            ex.printStackTrace();
        } catch (BadElementException ex) {
            logger.error("Elemento incorrecto", ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.error("No se pudo crear una instancia de la imagen", ex);
            ex.printStackTrace();
        }
        return image;
    }

    protected String getFileRealpath(String file) {
        String realPath = this.context.getRealPath(file);
        return realPath;
    }

    protected Image getImageFromAppRelativePath(String relativePath) {
        String imagePath = this.getFileRealpath(relativePath);
        Image image = null;
        try {
            image = Image.getInstance(imagePath);
        } catch (Exception e) {
            logger.error("Error cuando se iniciaba el encabezado del PDF", e);
        }
        return image;
    }
}
