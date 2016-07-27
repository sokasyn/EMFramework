package com.emtest.data;


import com.emin.digit.mobile.android.commom.ConstantTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/1.
 */
public class BuildTableJSON {

    public static JSONObject buildCreateTable() throws JSONException{
        JSONObject createObj = new JSONObject();

        // table:account
        createObj.put(ConstantTable.TBL_ACCOUNT,"id,name,password");

        // table:user
        createObj.put(ConstantTable.TBL_USER,"id int PRIMARY KEY NOT NULL,nick_name varchar(16) NOT NULL,age integer,address_id int");

        // table:address
        createObj.put(ConstantTable.TBL_ADDRESS,"id,pid,name");

        return createObj;
    }

    public static JSONObject buildAlterTable(int caseNum) throws JSONException{
        JSONObject alterObj = new JSONObject();
        switch (caseNum){
            case 0:{
                alterObj.put(ConstantTable.TBL_USER,"telNum");
                break;
            }
            case 1:{
                alterObj.put(ConstantTable.TBL_ACCOUNT,"memo varchar(20)");
                break;
            }
        }
        return alterObj;
    }


    public static JSONArray buildDropJson(int caseNum) throws JSONException {
        JSONArray dropObj =  new JSONArray();
        switch (caseNum) {
            // 单个表
            case 0: {
                dropObj.put(ConstantTable.TBL_USER);
                break;
            }
            // 两个表
            case 1: {
                dropObj.put(ConstantTable.TBL_ACCOUNT);
                dropObj.put(ConstantTable.TBL_ADDRESS);
                break;
            }
            // 三个表
            // ["USER","ACCOUNT","ADDRESS"]
            case 2:{
                dropObj.put(ConstantTable.TBL_USER);
                dropObj.put(ConstantTable.TBL_ACCOUNT);
                dropObj.put(ConstantTable.TBL_ADDRESS);
                break;
            }
            default:{
                break;
            }
        }
        String jsonString = dropObj.toString();
        System.out.println("drop table json string:" + jsonString);
        return dropObj;
    }
}
