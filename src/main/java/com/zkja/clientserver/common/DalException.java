package com.zkja.clientserver.common;

/**
 * @authon zzr
 */
public class DalException extends Exception{
    private static final long serialVersionUID = 7832799332298727249L;

    public DalException() {
        super();
    }

    public DalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DalException(String message) {
        super(message);
    }

    public DalException(Throwable cause) {
        super(cause);
    }
}
