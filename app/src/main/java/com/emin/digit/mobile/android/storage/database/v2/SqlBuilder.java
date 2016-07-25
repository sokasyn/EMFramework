package com.emin.digit.mobile.android.storage.database.v2;

/**
 * Created by Samson on 16/7/25.
 *
 * 数据库可执行sql语句的构造器
 */
public class SqlBuilder {


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
}
