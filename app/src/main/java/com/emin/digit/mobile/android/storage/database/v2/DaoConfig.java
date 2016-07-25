package com.emin.digit.mobile.android.storage.database.v2;

import android.content.Context;

/**
 * Created by Samson on 16/7/22.
 */
public class DaoConfig {

    private static final String DEFAULT_DB_NAME = "DefaultDb.db";
    private static final int DEFAULT_VERSION = 1;

    private Context context;
    private String dbType;
    private String dbName = DEFAULT_DB_NAME;
    private int    dbVersion = DEFAULT_VERSION;
    private String targetDirectory;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(int dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }
}
