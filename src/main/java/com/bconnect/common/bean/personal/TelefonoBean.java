package com.bconnect.common.bean.personal;

import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author Jorge Rodriguez
 */
public class TelefonoBean {

    public TelefonoBean() {
    }
    protected String lada;
    protected String numTelefono;
    protected String extension;
    protected String descripcion;
    protected boolean isActive;
    protected String tipo;

    public String getLada() {
        return lada;
    }

    public void setLada(String lada) {
        if (CommonUtils.hasValue(lada)) {
            this.isActive = true;
        } else {
            this.isActive = false;
        }
        this.lada = lada;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        if (CommonUtils.hasValue(numTelefono)) {
            isActive = true;
        } else {
            this.isActive = false;
        }
        this.numTelefono = numTelefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefonoCompleto() {
        StringBuffer num = new StringBuffer();
        if (lada != null && !lada.equals("")) {
            num.append(lada);
            num.append("-");
        }
        num.append(numTelefono);
        if (extension != null && !extension.equals("")) {
            num.append(" ext. ");
            num.append(extension);
        }
        return num.toString();
    }

    public String getTelefonoMarcacion() {
        StringBuffer num = new StringBuffer();
        if (tipo.equals("ALTERNO")) {
            // modificacion de inconcert 
            //marcar celular con un 1 antes
//            num.append("1");
//            num.append(lada);
            
            
             if (!"55".equals(lada)) {
                num.append("045");
                num.append(lada);
            } else {
                num.append("044");
                num.append(lada);
            }
            
        } else {
            if (lada != null && !lada.equals("")) {
                if (!"55".equals(lada)) {
                    num.append("01");
                    num.append(lada);
                }
            }
        }


        num.append(numTelefono);
        return num.toString();
    }

//    public String getTelefonoMarcacionMovil() {
//        StringBuffer num = new StringBuffer();
//        if (lada != null && !lada.equals("")) {
//            if (!"55".equals(lada)) {
//                num.append("045");
//                num.append(lada);
//            } else {
//                num.append("044");
//                num.append(lada);
//            }
//        }
//        num.append(numTelefono);
//        return num.toString();
//    }

    @Override
    public String toString() {
        StringBuffer num = new StringBuffer();
        num.append(lada);
        num.append("-");
        num.append(numTelefono);
        num.append("-");
        num.append(extension);

        return num.toString();
    }

    public boolean validateInput() {
        boolean isOk = true;

        if (isActive) {
            isOk = (CommonUtils.hasValue(lada)
                    && CommonUtils.hasValue(numTelefono));
        }
        return isOk;
    }

    public boolean hasValidLengths() {
        boolean validLengths = false;
        if (!isActive) {
            validLengths = true;
        }
        if (CommonUtils.hasValue(lada)
                && CommonUtils.hasValue(numTelefono)) {
            validLengths = (this.lada.length() == 2
                    && this.numTelefono.length() == 8);
        }
        return validLengths;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
