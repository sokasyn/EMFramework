package com.emin.digit.mobile.android.storage.database.v2.base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public interface IDatabaseOperator {

    // ----------- 表级别操作 -----------
    public void createTable(JSONObject jsonObject);
    public void dropTables(JSONArray jsonArray) throws JSONException;
    public void updateTable(JSONObject jsonObject) throws JSONException;

    // ----------- 数据级别操作 -----------
    public void insert(JSONObject jsonObject) throws JSONException;

    public void delete(JSONObject jsonObject) throws JSONException;

    public void update(JSONObject jsonObject) throws JSONException;

    public JSONArray query(JSONObject jsonObject) throws JSONException;

    // 执行SQL
    public void execSQL(String sqlString);
}
