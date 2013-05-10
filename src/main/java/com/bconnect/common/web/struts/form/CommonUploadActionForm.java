package com.bconnect.common.web.struts.form;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * Esta clase implementa los métodos comúnes para realizar el guardado de
 * archivos desde JSP's
 * @author Jorge Rodriguez
 */
public class CommonUploadActionForm extends org.apache.struts.action.ActionForm {

    protected List<FormFile> files;

    /**
     *
     */
    public CommonUploadActionForm() {
        super();
        files = new ArrayList<FormFile>();
    }

    public FormFile getFile(int i) {
        return (files.size() > i) ? (FormFile) files.get(i) : null;
    }

    public void setFile(int i, FormFile f) {
        if (f.getFileSize() <= 0) {
            f.destroy();
        } else {
            files.add(f);
        }
    }

    public List getFiles() {
        return files;
    }

    public void setFiles(List<FormFile> files) {
        this.files = files;
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }
}
