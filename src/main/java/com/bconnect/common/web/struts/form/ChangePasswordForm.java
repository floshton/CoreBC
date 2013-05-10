package com.bconnect.common.web.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Esta clase del tipo ActionForm procesa la validación de datos al momento
 * de intentar cambiar actualizar el password de un usuario
 * @author Jorge Rodriguez
 */
public class ChangePasswordForm extends org.apache.struts.action.ActionForm {

    public String userPassword;
    public String oldPassword;
    public String newPassword;
    public String confirmPassword;

    /**
     *
     */
    public ChangePasswordForm() {
        super();
    // TODO Auto-generated constructor stub
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Método que efectúa la validación de los datos, previo a la invocación
     * del stored procedure que efectúa la tarea de actualización.
     * Se efectúan las siguientes validaciones:
     * <ul>
     * <li>Ningun valor puede quedar vacío</li>
     * <li>El password y su confirmación deben coincidir</li>
     * <li>El password anterior debe ser correcto, de acuerdo al actualmente 
     * asociado con el usuario que tiene una sesión activa</li>
     * <li>El password debe estar en el rango de 8 a 15 caracteres</li>
     * <li>El password debe contener mínimamente 2 caracteres numéricos
     * y dos alfanuméricos</li>
     * </ul>
     * @param mapping
     * @param request
     * @return una instancia de ActionErrors, producto de la validación de los datos.
     * Esta instancia es nula de no existir errores.
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        // validar llenado
        if (oldPassword == null || oldPassword.equals("")) {
            errors.add("oldPassword", new ActionMessage("form.error.missing"));
        }
        if (newPassword == null || newPassword.equals("")) {
            errors.add("newPassword", new ActionMessage("form.error.missing"));
        }
        if (confirmPassword == null || confirmPassword.equals("")) {
            errors.add("confirmPassword", new ActionMessage("form.error.missing"));
        }
        if(!errors.isEmpty()){
            return errors;
        }
        
        // validar oldPassword
        if(!oldPassword.equals(userPassword)){
            errors.add("confirmPassword", new ActionMessage("form.changePassword.notMatch"));
        }

        // validar construccion
        if (!isValid(newPassword)) {
            errors.add("newPassword", new ActionMessage("form.changePassword.invalid"));
        }
        if(!errors.isEmpty()){
            return errors;
        }
        
        // validar longitud
        if (newPassword.length() < 8 || newPassword.length() > 15) {
            errors.add("confirmPassword", new ActionMessage("form.changePassword.length"));
        }
        if(!errors.isEmpty()){
            return errors;
        }

        // validar confirmacion
        if (!newPassword.equals(confirmPassword)) {
            errors.add("confirmPassword", new ActionMessage("form.changePassword.notMatch"));
        }
        return errors;
    }

    private boolean isValid(String password) {

        int numbersCounter = 0;
        int lettersCounter = 0;

        boolean validLetters = false;
        boolean validNumbers = false;

        String numberString = "012345679";
        String letterUpperString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letterLowerString = "abcdefghijklmnopqrstuvwxyz";

        char[] passwordArray = password.toCharArray();

        char[] numbers = numberString.toCharArray();
        char[] lettersUpper = letterUpperString.toCharArray();
        char[] lettersLower = letterLowerString.toCharArray();

        for (int i = 0; i < passwordArray.length; i++) {
            char c = passwordArray[i];

            for (int j = 0; j < lettersLower.length; j++) {
                if (c == lettersLower[j]) {
                    lettersCounter++;
                }
            }

            for (int k = 0; k < lettersUpper.length; k++) {
                if (c == lettersUpper[k]) {
                    lettersCounter++;
                }
            }

            for (int m = 0; m < numbers.length; m++) {
                if (c == numbers[m]) {
                    numbersCounter++;
                }
            }
        }

        if (lettersCounter >= 2) {
            validLetters = true;
        }

        if (numbersCounter >= 2) {
            validNumbers = true;
        }

        return (validLetters && validNumbers);
    }
}
