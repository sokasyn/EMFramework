package com.emin.digit.mobile.android.storage.database.v2.base;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public abstract class AbstractDatabase implements DatabaseOperator {

    @Override
    public void createTable(JSONObject jsonObject) {

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

    public abstract void executeSql(String sqlString);
}
