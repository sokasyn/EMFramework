package com.emin.digit.mobile.android.storage.database.v2;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public abstract class DatabaseAdaptee {

    // ----------- 表级别操作 -----------
    public abstract void createTable(JSONObject jsonObject);
    public abstract void dropTable(JSONObject jsonObject);
    public abstract void updateTable(JSONObject jsonObject);

    // ----------- 数据级别操作 -----------
    public abstract void insert(JSONObject jsonObject);

    public abstract void delete(JSONObject jsonObject);

    public abstract void update(JSONObject jsonObject);

    public abstract void query(JSONObject jsonObject);


}
