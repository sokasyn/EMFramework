package com.emin.digit.mobile.android.exception;

/**
 * Created by Samson on 16/7/26.
 */
public class DatabaseException extends EmException {
    private static final long serialVersionUID = 1L;

    public DatabaseException() {}


    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(Throwable ex) {
        super(ex);
    }

    public DatabaseException(String msg,Throwable ex) {
        super(msg,ex);
    }
}
