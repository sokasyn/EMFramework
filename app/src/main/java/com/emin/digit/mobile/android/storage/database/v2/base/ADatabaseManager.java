package com.emin.digit.mobile.android.storage.database.v2.base;

import com.emin.digit.mobile.android.storage.database.v2.DaoConfig;
import com.emin.digit.mobile.android.storage.database.v2.SqliteDatabase;
import com.emin.digit.mobile.android.storage.database.v2.base.Database;

import org.json.JSONObject;

/**
 * Created by Samson on 16/7/22.
 *
 * 数据库操作需要创建或者打开数据库,将该流程封装起来
 *
 */
public abstract class ADatabaseManager {

    public void getInstance(){
        createOrOpenDatabase();
    }

    public abstract void createOrOpenDatabase();
}
