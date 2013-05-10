package com.bconnect.common.bean;

import java.io.InputStream;

/**
 *
 * @author angel.ramirez
 */
public class AttachmentBean {

    private String nombre;
    private String nombreServidor;
    private String localPath;
    private String realPath;
    private InputStream inputStream;
    private String mimeType;

    public AttachmentBean() {
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreServidor() {
        return nombreServidor;
    }

    public void setNombreServidor(String nombreServidor) {
        this.nombreServidor = nombreServidor;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }
}
