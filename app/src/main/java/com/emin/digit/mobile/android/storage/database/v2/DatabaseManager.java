package com.emin.digit.mobile.android.storage.database.v2;

import com.emin.digit.mobile.android.storage.database.v2.base.AbstractDatabase;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public class DatabaseManager {

    AbstractDatabase database;

    public void getDbInstance(DaoConfig config){
        this.database = new SqliteDatabase();
//        this.database = new IndexDatabase();
    }


    private void insert(JSONObject jsonObject){
        this.database.insert(jsonObject);
    }


}
