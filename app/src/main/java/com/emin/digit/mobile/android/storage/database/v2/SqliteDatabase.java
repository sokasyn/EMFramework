package com.emin.digit.mobile.android.storage.database.v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.base.Database;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Samson on 16/7/22.
 *
 * SqliteDatabase是对Android的SQLiteDatabase的封装
 * daoMap,以key-value的方式储存了数据库名称与SqliteDatabase的对应关系,
 * 即,SqliteDatabase可以管理多个SQLite数据库实例
 */
public class SqliteDatabase extends Database {

    private static final String TAG = SqliteDatabase.class.getSimpleName();

    private void debug(String info){
        System.out.println(info);
    }

    // SqliteDatabase Map,通过数据库的名称来获取数据库实例
    private static HashMap<String,SqliteDatabase> daoMap = new HashMap<String, SqliteDatabase>();

    // Android SQLiteDatabase
    private SQLiteDatabase sqLiteDb;

    // 数据库配置对象
    private DaoConfig daoConfig;

    // 私有构造方法
    private SqliteDatabase(DaoConfig config){
        if(config.getDbName() == null){
            debug("Db name is null");
        }else{
            sqLiteDb = new SqliteDbHelper(config.getContext().getApplicationContext(),
                    config.getDbName(), config.getDbVersion()).getWritableDatabase();
        }
        this.daoConfig = config;
    }

    /**
     * 通过context上下文创建数据库,因为数据库为设置,所以采用DaoConfig中默认的数据库名称
     *
     * @param context 上下文
     * @return SqliteDatabase单例(相应数据库名称单例,不同的数据库名称对应不同的数据库单例)
     */
    public static SqliteDatabase create(Context context){
        DaoConfig config = new DaoConfig();
        config.setContext(context);
        return create(config);
    }

    /**
     * 通过上下文和数据库名称创建数据库
     *
     * @param context 上下文
     * @param dbName 数据库名称
     * @return SqliteDatabase单例(相应数据库名称单例,不同的数据库名称对应不同的数据库单例)
     */
    public static SqliteDatabase create(Context context,String dbName){
        DaoConfig config = new DaoConfig();
        config.setContext(context);
        config.setDbName(dbName);
        return create(config);
    }

    /**
     * 通过数据库配置对象创建数据库
     *
     * @param config 数据库配置对象
     * @return SqliteDatabase单例(相应数据库名称单例,不同的数据库名称对应不同的数据库单例)
     */
    public static SqliteDatabase create(DaoConfig config){
        return getInstance(config);
    }

    // 避免create()重载的函数调用产生的线程安全问题
    private synchronized static SqliteDatabase getInstance(DaoConfig config){
        SqliteDatabase sqliteDb = daoMap.get(config.getDbName());
        if(sqliteDb == null){
            DebugLog.i(TAG,"sqliteDatabase object is null");
            sqliteDb = new SqliteDatabase(config);
            daoMap.put(config.getDbName(),sqliteDb);
        }
        return sqliteDb;
    }

    @Override
    public void createTable(JSONObject jsonObject) {
//        DebugLog.i(TAG,jsonObject.toString());
        super.createTable(jsonObject);

        if(jsonObject == null) return;
        Iterator<String> iterator =  jsonObject.keys();
        while (iterator.hasNext()){
            String tableName = iterator.next();
            String columnsDef = jsonObject.optString(tableName);
            SqlInfo sqlInfo = SqlBuilder.buildCreateTableSql(tableName,columnsDef);
            sqLiteDb.execSQL(sqlInfo.getSql());
        }
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
    public void execSQL(String sqlString) {
        this.sqLiteDb.execSQL(sqlString);
    }

    class SqliteDbHelper extends SQLiteOpenHelper {

//        private DbUpdateListener mDbUpdateListener;

        //三个不同参数的构造函数
        //带全部参数的构造函数，此构造函数必不可少
        public SqliteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //带三个参数的构造函数，调用的是带所有参数的构造函数
        public SqliteDbHelper(Context context,String name,int version){
            this(context, name, null, version);
        }

        /*
        public SqliteDbHelper(Context context, String name, int version, DbUpdateListener dbUpdateListener) {
            super(context, name, null, version);
            DebugLog.i(TAG,"EMDatabase SqliteDbHelper constructor");
            this.mDbUpdateListener = dbUpdateListener;
        }*/

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*
            if (mDbUpdateListener != null) {
                mDbUpdateListener.onUpgrade(db, oldVersion, newVersion);
            } else { // 清空所有的数据信息
                dropDb();
            }*/
        }
    }
}
