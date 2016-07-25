package com.emin.digit.mobile.android.storage.database.v2.base;

import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.SqlBuilder;
import com.emin.digit.mobile.android.storage.database.v2.SqlInfo;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Samson on 16/7/22.
 */
public abstract class Database implements IDatabaseOperator {

    private static final String TAG = Database.class.getSimpleName();

    private Database database;

    @Override
    public void createTable(JSONObject jsonObject) {
//        DebugLog.i(TAG,jsonObject.toString());
    }

    @Override
    public void dropTable(JSONObject jsonObject) {

    }

    @Override
    public void updateTable(JSONObject jsonObject) {

    }

    public abstract void insert(JSONObject jsonObject);

    public abstract void delete(JSONObject jsonObject);

    public abstract void update(JSONObject jsonObject);

    public abstract void query(JSONObject jsonObject);

    public abstract void execSQL(String sqlString);
}
