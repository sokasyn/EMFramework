package com.emin.digit.mobile.android.storage.database.v2;

import android.database.sqlite.SQLiteDatabase;

import com.emin.digit.mobile.android.storage.database.v2.base.AbstractDatabase;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public class SqliteDatabase extends AbstractDatabase{

    private SQLiteDatabase database;


    public SqliteDatabase(){

    }

    @Override
    public void createTable(JSONObject jsonObject) {
        super.createTable(jsonObject);
    }

    @Override
    public void dropTable(JSONObject jsonObject) {
        super.dropTable(jsonObject);
    }

    @Override
    public void updateTable(JSONObject jsonObject) {
        super.updateTable(jsonObject);
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
    public void query(JSONObject jsonObject) {

    }

    @Override
    public void executeSql(String sqlString) {

    }
}
