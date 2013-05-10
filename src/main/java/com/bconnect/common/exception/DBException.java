package com.bconnect.common.exception;

/**
 * @author Jorge Rodriguez
 * Esta clase extiende Exception y es lanzada cada vez que una transaccion
 * a la BD no se completa exitosamente
 */
public class DBException extends Exception
{
    
    public DBException(String message)
    {
        super(message);
    }
    
    public DBException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public DBException(Throwable cause)
    {
        super(cause);
    }
}
