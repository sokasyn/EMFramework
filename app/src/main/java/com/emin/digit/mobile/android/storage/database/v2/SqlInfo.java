package com.emin.digit.mobile.android.storage.database.v2;

/**
 * Created by Samson on 16/7/25.
 */
public class SqlInfo {

    private String sqlStr;

    public SqlInfo(){

    }

    /**
     * 构造方法
     *
     * @param sql sql语句
     */
    public SqlInfo(String sql){
        this.sqlStr = sql;
    }

    /**
     * 获取sql语句
     *
     * @return
     */
    public String getSql() {
        return sqlStr;
    }

    /**
     * 设置sql语句
     *
     * @param sqlStr sql语句
     */
    public void setSql(String sqlStr) {
        this.sqlStr = sqlStr;
    }
}
