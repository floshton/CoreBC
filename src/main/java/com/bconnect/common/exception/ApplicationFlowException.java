package com.bconnect.common.exception;

/**
 *
 * @author Jorge Rodriguez
 * Excepción a ser lanzada cuando se rompen las reglas lógicas de flujo
 * dentro de la aplicación
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
