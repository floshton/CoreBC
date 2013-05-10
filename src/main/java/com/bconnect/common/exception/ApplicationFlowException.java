package com.bconnect.common.exception;

/**
 *
 * @author Jorge Rodriguez
 * Excepci�n a ser lanzada cuando se rompen las reglas l�gicas de flujo
 * dentro de la aplicaci�n
 */
public class ApplicationFlowException extends BConnectException{

    public ApplicationFlowException(String message)
    {
        super(message);
    }
    
    public ApplicationFlowException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public ApplicationFlowException(Throwable cause)
    {
        super(cause);
    }
}
