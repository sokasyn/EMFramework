package com.emin.digit.mobile.android.storage.database.v2.base;

import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.SqlBuilder;
import com.emin.digit.mobile.android.storage.database.v2.SqlInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Samson on 16/7/22.
 */
public abstract class Database implements IDatabaseOperator {

    private static final String TAG = Database.class.getSimpleName();

//    private Database database;

    @Override
    public void createTable(JSONObject jsonObject) {
//        DebugLog.i(TAG,jsonObject.toString());
    }

    @Override
    public void dropTables(JSONArray jsonArray) throws JSONException {

    }

    @Override
    public void updateTable(JSONObject jsonObject) throws JSONException{

    }

    public abstract void insert(JSONObject jsonObject) throws JSONException;

    public abstract void delete(JSONObject jsonObject) throws JSONException;

    public abstract void update(JSONObject jsonObject) throws JSONException;

    public abstract JSONArray query(JSONObject jsonObject) throws JSONException;

    public abstract void execSQL(String sqlString);
}
