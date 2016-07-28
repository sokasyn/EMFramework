package com.emin.digit.mobile.android.storage.database.v2.exception;

import com.emin.digit.mobile.android.exception.EmException;

/**
 * 数据库操作相关异常
 *
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
