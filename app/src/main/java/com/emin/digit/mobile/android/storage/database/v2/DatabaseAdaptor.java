package com.emin.digit.mobile.android.storage.database.v2;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 */
public class DatabaseAdaptor {

    private DatabaseAdaptee dbAdaptee;

    public DatabaseAdaptor(DatabaseAdaptee adaptee){
        this.dbAdaptee = adaptee;
    }

    public void insert(JSONObject jsonObj){
        this.dbAdaptee.insert(jsonObj);
    }

    public void delete(JSONObject jsonObj){
        this.dbAdaptee.delete(jsonObj);
    }

    public void update(JSONObject jsonObj){
        this.dbAdaptee.update(jsonObj);
    }

    public void query(JSONObject jsonObj){
        this.dbAdaptee.query(jsonObj);
    }
}
