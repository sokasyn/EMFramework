package com.emin.digit.mobile.android.storage.database.v2;

import android.content.Context;

import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.base.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/25.
 *
 * 数据库管理器,在获取数据库管理实例(单例)的同时,就根据相关的配置创建/打开数据库
 * 调用者在做数据库操作的时候,只需要调用相应的增删改查的接口,而不用手动去管理数据库本身
 */
public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getSimpleName();

    private static DatabaseManager instance = new DatabaseManager();

    private static Database sqliteDatabase;

    // TODO: 除了SQLite,将来还有其它的数据库类型?或者将来扩展成ORM形式？
    private static Database ohterDatabase;

    /**
     *
     * 获取数据库管理器的实例(单例）
     * 并通过数据库配置对象初始化数据库,创建或打开,以便调用者专注与数据库中数据的操作。
     * DaoConfig对象不会对DatabaseManager(单例)产生影响,而只影响配置所对应的数据库实例.
     * 因为Database的具体实现,如SqliteDatabas,(通过daoMap)具有管理多个物理数据库的能力
     *
     * @param config 数据库配置对象
     * @return 数据库管理器的单例
     */
    public static DatabaseManager getInstance(DaoConfig config){
        sqliteDatabase = createOrOpenDatabase(config);
        return instance;
    }

    /**
     * 获取数据库管理器的实例(单例）
     * 并通过Android 上下文context获取默认的数据库实例
     *
     * @param context
     * @return
     */
    public static DatabaseManager getInstance(Context context){
        DaoConfig config = new DaoConfig();
        config.setContext(context);
        sqliteDatabase = createOrOpenDatabase(config);
        return instance;
    }

    // 私有构造方法,避免外部自行创建管理器实例
    private DatabaseManager(){

    }

    // - - - - - - - - - - - - - 数据库的创建/打开 - - - - - - - - - - - - -
    /**
     * 通过数据库配置对象创建或打开数据库
     * 目前以SqliteDatabase作为具体的实现
     *
     * @param config 数据库配置对象
     * @return
     */
    private static Database createOrOpenDatabase(DaoConfig config){
        SqliteDatabase sqliteDb = SqliteDatabase.create(config);
        DebugLog.i(TAG,"------ createOrOpenDatabase SqliteDatabase instance:" + sqliteDb);
        return sqliteDb;
    }

    // - - - - - - - - - - - - - 数据库表级别操作 - - - - - - - - - - - - -

    /**
     * 新建数据表
     * JSON对象中的每一个Key-value键值对都应当是一个以key为表名,value为字段列表定义的json字符串
     * 每一个key-value对,对应一张表
     *  格式如:{"user":"name,age",
     *         "account":"name,password",
     *         "address":"id,pid,name"}
     *         表示创建user,account,address 三表
     *
     * @param jsonObject
     */
    public void createTable(JSONObject jsonObject) throws JSONException{
        sqliteDatabase.createTable(jsonObject);
    }

    public void dropTable(JSONArray jsonArray) throws JSONException{
        sqliteDatabase.dropTables(jsonArray);
    }


    public void updateTable(JSONObject jsonObject) throws JSONException{
        sqliteDatabase.updateTable(jsonObject);
    }

    // - - - - - - - - - - - - - 数据库记录级别操作 - - - - - - - - - - - - -

    public void insert(JSONObject jsonObject) throws JSONException{
        sqliteDatabase.insert(jsonObject);
    }

    public void delete(JSONObject jsonObject) throws JSONException{
        sqliteDatabase.delete(jsonObject);
    }

    public void update(JSONObject jsonObject) throws JSONException {
        sqliteDatabase.update(jsonObject);
    }

    public JSONArray query(JSONObject jsonObject) throws JSONException{
        JSONArray result = sqliteDatabase.query(jsonObject);
        return result;
    }

}
