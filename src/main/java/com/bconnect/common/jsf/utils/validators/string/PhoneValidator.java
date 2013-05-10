/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bconnect.common.jsf.utils.validators.string;

import com.bconnect.common.util.CommonUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jorge Rodriguez
 */
public class PhoneValidator implements Validator {

    public PhoneValidator() {
    }

    public void validate(FacesContext facesContext,
            UIComponent uIComponent, Object object) throws ValidatorException {

        String enteredPhone = (String) object;

        if (CommonUtils.hasValue(enteredPhone)) {
            if (enteredPhone.length() < 10) {
                FacesMessage message = new FacesMessage();
                message.setDetail("The phone number lenght is incorrect.");
                message.setSummary("Invalid Phone Number");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }

    }
}
