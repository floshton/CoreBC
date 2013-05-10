package com.bconnect.common.util.jsf;

/**
 *
 * @author Jorge Rodriguez
 */
public class ValidationBean {

    private Object object;
    private String validationMessage;

    public ValidationBean() {
    }

    public ValidationBean(Object object, String validationMessage) {
        this.object = object;
        this.validationMessage = validationMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
