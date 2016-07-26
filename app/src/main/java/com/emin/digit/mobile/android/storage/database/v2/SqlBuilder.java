package com.emin.digit.mobile.android.storage.database.v2;

import android.database.SQLException;

import com.emin.digit.mobile.android.exception.DatabaseException;
import com.emin.digit.mobile.android.storage.database.util.DebugLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Samson on 16/7/25.
 *
 * 数据库可执行sql语句的构造器
 */
public class SqlBuilder {

    private static final String TAG = SqlBuilder.class.getSimpleName();

    // * * * * * * * * * * Table Level: Build Create SQL * * * * * * * * * * //
    public static SqlInfo buildCreateTableSql(String tableName , String columnsDef){

//        String className = Thread.currentThread().getStackTrace()[1].getClassName();
//        DebugLog.methodStart(className);

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("CREATE TABLE IF NOT EXISTS ");
        sqlBuffer.append(tableName);
        sqlBuffer.append("( ");
        sqlBuffer.append(columnsDef);
        sqlBuffer.append(" )");

        SqlInfo sqlInfo = new SqlInfo();
        sqlInfo.setSql(sqlBuffer.toString());
        return sqlInfo;
    }

    /**
     * 构建删除所有数据表的sql
     */
    public static SqlInfo buildDropAllTablesSql() {
        String sqlStr = "SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'";
        SqlInfo sqlInfo = new SqlInfo(sqlStr);
        return sqlInfo;
    }

    /**
     * 构建删除指定的一组数据表的sql
     *
     * @param tableArray 一组表的JSON数组
     * @return ArrayList<SqlInfo>
     * @throws JSONException
     */
    public static ArrayList<SqlInfo> buildDropTableSqlWithJsonArray(JSONArray tableArray) throws JSONException {
        ArrayList<SqlInfo> sqlInfoList = new ArrayList<SqlInfo>();
        for(int i = 0; i < tableArray.length(); i++){
            String tableName = tableArray.getString(i);
            SqlInfo sqlInfo = buildDropTableSqlWithTableName(tableName);
            sqlInfoList.add(sqlInfo);
        }
        return sqlInfoList;
    }

    /**
     * 构建删除指定的一个数据表的sql
     *
     * @param tableName 表名
     * @return
     */
    public static SqlInfo buildDropTableSqlWithTableName(String tableName){
        if(tableName.trim().isEmpty() || tableName == null) {
            throw new DatabaseException("Table name can not be null");
        }
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("DROP TABLE IF EXISTS ");
        sqlBuffer.append(tableName);
        SqlInfo sqlInfo = new SqlInfo(sqlBuffer.toString());
        return sqlInfo;
    }

    // 获取ALTER SQL中的共通部分 "ALTER TABLE "
    private static String getAlterTableSql(String tableName){
        return "ALTER TABLE " + tableName;
    }

    /**
     * 构建更新数据表的sql
     *
     * @param jsonObject
     * @return
     * @throws JSONException JSONException org.json.JSONException异常
     */
    public static SqlInfo buildAlterTableSql(JSONObject jsonObject) throws JSONException{
//        ALTER TABLE USER ADD COLUMN ADDRESS VARCHAR(20)
//        JSONObject alterObj = new JSONObject(jsonString);

        StringBuffer sqlBuffer = new StringBuffer();
        Iterator tableKeyIterator = jsonObject.keys();
        while (tableKeyIterator.hasNext()){
            String tableName = (String)tableKeyIterator.next();
            sqlBuffer.append(getAlterTableSql(tableName));
            Object columnObj = jsonObject.opt(tableName);
            if(columnObj instanceof JSONArray){


            }else{
                sqlBuffer.append(" ADD COLUMN ");
                sqlBuffer.append(columnObj.toString());
            }
        }
        return new SqlInfo(sqlBuffer.toString());
    }

    // - - - - - - - - 记录级别Sql的构建 - - - - - - - - - -

    /**
     * 构建新增表记录的sql
     *
     * @param tableName 数据表的名称字符串
     * @param recordObject 一行数据的JSON对象
     * @return
     */
    public static SqlInfo buildInsertSqlForTable(String tableName, JSONObject recordObject){
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("INSERT INTO ");
        sqlBuffer.append(tableName);
        sqlBuffer.append(" (");

        StringBuffer columnStr = new StringBuffer();
        StringBuffer valueStr = new StringBuffer();

//      JSONObject jsonObj = new JSONObject(jsonString);
        Iterator it = recordObject.keys();
        while (it.hasNext()){
            String key = (String)it.next();
            String value = recordObject.optString(key);
            columnStr.append(key + ",");
            valueStr.append("'" + value + "'" + ",");
        }
        columnStr.deleteCharAt(columnStr.length() - 1);
        valueStr.deleteCharAt(valueStr.length() - 1);

//        insert into staff (staff_id,first_name,last_name,address_id,store_id,username) values(2,'Sam','Hillyer',3,1,'Sam');
        sqlBuffer.append(columnStr);
        sqlBuffer.append(") VALUES (");
        sqlBuffer.append(valueStr);
        sqlBuffer.append(")");

        SqlInfo sqlInfo = new SqlInfo(sqlBuffer.toString());
        return sqlInfo;
    }


    /**
     * 构建删除数据表(一个或多个)的记录sql
     *
     * @param jsonObject
     * @return
     * @throws JSONException JSONException org.json.JSONException异常
     */
    public static ArrayList<SqlInfo> buildDeleteSql(JSONObject jsonObject) throws JSONException{
        ArrayList<SqlInfo> sqlInfoList = new ArrayList<SqlInfo>();
        Iterator tableKeyIterator = jsonObject.keys();
        while (tableKeyIterator.hasNext()){
            SqlInfo sqlInfo;
            String tableName = (String)tableKeyIterator.next();
            Object object = jsonObject.opt(tableName);
            if(object instanceof JSONObject){
                JSONObject whereObj = jsonObject.optJSONObject(tableName);
                sqlInfo = buildDeleteSqlForTable(tableName,whereObj);
            }else{
                sqlInfo = buildDeleteSqlForTable(tableName,null);
            }
            sqlInfoList.add(sqlInfo);
        }
        return sqlInfoList;
    }

    /**
     * 构建删除指定数据表的记录
     *
     * @param tableName 指定的数据表名字符串
     * @param whereJson 删除的条件JSON对象
     * @return
     */
    public static SqlInfo buildDeleteSqlForTable(String tableName, JSONObject whereJson){
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("DELETE FROM ");
        sqlBuffer.append(tableName);

//        DELETE from user where name='Kate' and age='1' and ;
        if(whereJson != null && whereJson.length() != 0){
            sqlBuffer.append(" WHERE ");
            Iterator whereKeyIterator = whereJson.keys();
            while (whereKeyIterator.hasNext()){
                String column = (String) whereKeyIterator.next();
                sqlBuffer.append(column);
                sqlBuffer.append("='");
                String value = whereJson.optString(column);
                sqlBuffer.append(value);
                sqlBuffer.append("'");

                sqlBuffer.append(" AND ");
            }
            sqlBuffer.delete(sqlBuffer.length() - 5, sqlBuffer.length()-1);
        }
        SqlInfo sqlInfo = new SqlInfo(sqlBuffer.toString());
        return  sqlInfo;
    }

    public static ArrayList<SqlInfo> buildUpdateSql(JSONObject jsonObject) throws JSONException{
        ArrayList<SqlInfo> sqlinfoList = new ArrayList<SqlInfo>();
        Iterator<String> tableKeyItr = jsonObject.keys();
        while (tableKeyItr.hasNext()){
            String tableName = tableKeyItr.next();
            JSONObject value = jsonObject.optJSONObject(tableName);
            if(value == null){
                break;
            }

            JSONObject setJson,whereJson;
            try{
                setJson = value.getJSONObject("SET");
            }catch (JSONException e){
                throw new JSONException("无法获取构建update sql中的set部分JSON");
            }

            // 采用optJSONObject(), 如果获取不到
            whereJson = value.optJSONObject("WHERE");

            SqlInfo sqlInfo = buildUpdateSqlForTable(tableName,setJson,whereJson);
            sqlinfoList.add(sqlInfo);
        }

        return sqlinfoList;
    }
    /*
    {"TBL_USER":{
            "SET":{"PASSWORD":"12345"},
            "WHERE":{"USER_NAME":"COCO","AGE":20 }
            }
     }

     var table = "TBL_USER";
     var obj = { tableName:table, }
     */

    /**
     * 更新数据表记录
     *
     * @param tableName 指定的数据表名称字符串
     * @param setJson   UPDATE SQL 中的 SET 部分JSON对象
     * @param whereJson UPDATE SQL 中的 WHERE  部分JSON对象
     * @return SqlInfo
     * @throws JSONException JSONException org.json.JSONException异常
     */
    public static SqlInfo buildUpdateSqlForTable(String tableName,
                                                 JSONObject setJson,
                                                 JSONObject whereJson) throws JSONException{

        // UPDATE TBL_USER SET PASSWORD = '12345' WHERE USER_NAME = 'COCO' AND AGE = 20;
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE ");
        sqlBuffer.append(tableName);
        sqlBuffer.append(" ");

        // 拼接 SET 部分
        sqlBuffer.append(" SET ");
        String setFragment = getColumnEqualValueString(setJson);
        sqlBuffer.append(" " + setFragment);

        // 拼接 WHERE 部分
        sqlBuffer.append(" WHERE ");
        String whereFragment = getColumnEqualValueString(whereJson);
        sqlBuffer.append(" " + whereFragment);

        SqlInfo sqlInfo = new SqlInfo(sqlBuffer.toString());
        return  sqlInfo;
    }

    // 构建 JSON对象中key = value 字符串
    private static String getColumnEqualValueString(JSONObject jsonObject) throws JSONException{
        StringBuffer strBuffer = new StringBuffer();
        Iterator<String> columnKeyItr = jsonObject.keys();
        while (columnKeyItr.hasNext()){
            String columnName = columnKeyItr.next();
            strBuffer.append(columnName);
            strBuffer.append(" = ");
            Object columnValue = jsonObject.get(columnName);
            strBuffer.append(columnValue.toString());
        }
        return strBuffer.toString();
    }

    /*
     SELECT USER_ID,USER_NAME FROM T_USER WHERE USER_DEP = 'QA' AND GENDER =  ORDER BY GROUP BY;

     var jsonObject =
     { "T_USER":{
            {"SELECT":["USER_ID","AGE"]},
            {"WHERE":{"USER_ID":2}}
        }
     }
     */

    public static SqlInfo buildQuerySql(JSONObject jsonObject){

        StringBuffer sqlBuffer = new StringBuffer();
        return null;
    }

    public static SqlInfo queryFromTable(JSONObject jsonObject) throws JSONException{
        DebugLog.i(TAG,jsonObject.toString());
        StringBuffer sqlBuffer = new StringBuffer();
        SqlInfo sqlInfo = null;
        Iterator<String> tableKeyItr = jsonObject.keys();
        if(tableKeyItr.hasNext()){
            String tableName = tableKeyItr.next();
            sqlBuffer.append("SELECT ");

            JSONObject valueObj = jsonObject.getJSONObject(tableName);
            // SELECT 部分
            JSONArray selectArray = valueObj.optJSONArray("SELECT");
            for(int i = 0; i < selectArray.length(); i++){
                String value = selectArray.getString(i);
                sqlBuffer.append(value);
                sqlBuffer.append(",");
            }
            // 删除最后一个逗号","
            sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
            DebugLog.i(TAG,"拼接SELECT 部分:" + sqlBuffer.toString());

            // FROM 部分
            sqlBuffer.append(" FROM ");
            sqlBuffer.append(tableName);
            DebugLog.i(TAG,"拼接FROM部分:" + sqlBuffer.toString());

            // 拼接WHERE 部分
            sqlBuffer.append(" WHERE ");
            JSONObject whereJson = valueObj.optJSONObject("WHERE");
            String whereString = getColumnEqualValueString(whereJson);
            sqlBuffer.append(whereString);
            DebugLog.i(TAG,"拼接WHERE部分:" + sqlBuffer.toString());

            sqlInfo = new SqlInfo(sqlBuffer.toString());
        }
        return sqlInfo;
    }

    public static SqlInfo buildQuerySqlForTable(String tableName){
        return null;
    }

}
