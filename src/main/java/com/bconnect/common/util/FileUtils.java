package com.bconnect.common.util;

import com.bconnect.common.logging.CommonLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jorge Rodriguez
 */
public class FileUtils {

    private static Logger logger;

    static {
        logger = CommonLogger.getLogger(FileUtils.class);
    }

    public static boolean fileExists(String filePath) {
        File theFile = new File(filePath);
        return theFile.exists();
    }

    public static boolean saveFile(byte[] fileData, String newFileName, String path)
            throws Exception {
        boolean success = false;
        File fileToCreate = null;
        try {
            if (CommonUtils.hasValue(newFileName)) {
                //Create file
                fileToCreate = new File(path, newFileName);
                //If file does not exists create file
                if (!fileToCreate.exists()) {
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(fileData);
                    fileOutStream.flush();
                    fileOutStream.close();
                    success = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Ocurrió un error al momento de guardar el archivo "
                    + fileToCreate, ex);
            throw new Exception(ex);
        }
        return success;
    }

    public static boolean saveFile(FormFile formFile, String newFileName, String path)
            throws Exception {
        boolean success = false;
        logger.debug("Archivo a ser almacenado: " + formFile.getFileName());
        success = FileUtils.saveFile(formFile.getFileData(), newFileName, path);
        return success;
    }

    public static boolean saveFile(FormFile formFile, String path)
            throws Exception {
        boolean success = false;
        success = FileUtils.saveFile(formFile, formFile.getFileName(), path);
        return success;
    }

    public static void saveFile(UploadedFile uploadedFile, String path, String newFileName) {
        // Just to demonstrate what information you can get from the uploaded file.
//        System.out.println("File type: " + uploadedFile.getContentType());
//        System.out.println("File name: " + uploadedFile.getFileName());
//        System.out.println("File size: " + uploadedFile.getSize() + " bytes");

        // Prepare filename prefix and suffix for an unique filename in upload folder.

        // Prepare file and outputstream.
        File file = null;
        OutputStream output = null;

        try {
            // Create file with unique name in upload folder and write to it.
//            file = File.createTempFile(fileNamePrefix + "_", "." + suffix, new File(PATH_ARCHIVOS));
            file = new File(path + newFileName);
            output = new FileOutputStream(file);
            IOUtils.copy(uploadedFile.getInputstream(), output);

        } catch (IOException e) {
            // Cleanup.
            if (file != null) {
                file.delete();
            }

            // Show error message.
            FacesContext.getCurrentInstance().addMessage("uploadForm", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error al almacenar el archivo", null));

            logger.error("Error al almacenar el archivo", e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }
}
