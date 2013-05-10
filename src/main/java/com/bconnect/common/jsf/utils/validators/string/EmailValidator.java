package com.bconnect.common.jsf.utils.validators.string;

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
public class EmailValidator implements Validator {

    public EmailValidator() {
    }

    public void validate(FacesContext facesContext,
            UIComponent uIComponent, Object object) throws ValidatorException {

        String enteredEmail = (String) object;
        
        if(!"".equals(enteredEmail)){
        
            //Patrón de email
            Pattern p = Pattern.compile(".+@.+\\.[A-Za-z]+");

            //hacer match de la cadena con el patrón
            Matcher m = p.matcher(enteredEmail);

            //Revisa si hay algún match
            boolean matchFound = m.matches();

            if (!matchFound) {
                FacesMessage message = new FacesMessage();
                message.setDetail("El formato de email no es válido.");
                message.setSummary("El email no es válido.");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
