package com.emin.digit.mobile.android.storage.database.v2.base;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public interface IDatabaseOperator {

    // ----------- 表级别操作 -----------
    public void createTable(JSONObject jsonObject);
    public void dropTable(JSONObject jsonObject);
    public void updateTable(JSONObject jsonObject);

    // ----------- 数据级别操作 -----------
    public void insert(JSONObject jsonObject);

    public void delete(JSONObject jsonObject);

    public void update(JSONObject jsonObject);

    public void query(JSONObject jsonObject);

    // 执行SQL
    public void execSQL(String sqlString);
}
