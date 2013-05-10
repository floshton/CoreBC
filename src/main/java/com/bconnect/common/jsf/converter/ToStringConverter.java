package com.bconnect.common.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Jorge Rodriguez
 */
public class ToStringConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        return object.toString();
    }
}