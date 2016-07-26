package com.emin.digit.mobile.android.storage.database.v2;

import com.emin.digit.mobile.android.storage.database.v2.base.Database;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/25.
 */
public class OtherDatabase extends Database {

    @Override
    public void createTable(JSONObject jsonObject) {
        super.createTable(jsonObject);
    }

    @Override
    public void dropTables(JSONArray jsonArray){
//        super.dropTables(jsonArray);
    }

    @Override
    public void updateTable(JSONObject jsonObject) {
//        super.updateTable(jsonObject);
    }

    @Override
    public void insert(JSONObject jsonObject) {

    }

    @Override
    public void delete(JSONObject jsonObject) {

    }

    @Override
    public void update(JSONObject jsonObject) {

    }

    @Override
    public JSONArray query(JSONObject jsonObject) {
        return null;
    }

    @Override
    public void execSQL(String sqlString) {

    }
}
