package com.emtest.data;

import com.emin.digit.mobile.android.commom.ConstantTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/1.
 */
public class BuildInsertJSON {

    // *********** 新增表数据的测试 ***********
    // 针对单个表
    public String buildInsertWithTable(String tableName,String jsonString){
        return null;
    }

    // 针对多个表
    public String buildInsert(String jsonString){
        return null;
    }


    public static JSONObject buildInsertRecords() throws JSONException{
        int recordCount = 10;
        JSONObject insertObj = new JSONObject();
        // ----- TABLE ACCOUNT
        JSONArray accountDataArray = new JSONArray();
        for(int i = 0 ; i < recordCount ; i++){
            int index = i + 1;
            JSONObject obj = new JSONObject();
            // TODO: 16/7/27 不同的类型测试
            obj.put("id","" + index);
            obj.put("name","sokasyn" + index);
            obj.put("password","" + index);
            accountDataArray.put(i,obj);
        }
        insertObj.put(ConstantTable.TBL_ACCOUNT,accountDataArray);

        // ----- TABLE USER
        // 多条数据存放与array中
        JSONArray userDataArray = new JSONArray();
        for(int i = 0 ; i < recordCount ; i++){
            int index = i + 1;
            JSONObject userObj = new JSONObject();
            userObj.put("id",index);
            userObj.put("nick_name","Sam" + index);
            userObj.put("age","" + index);
            userDataArray.put(i,userObj);
        }
        insertObj.put(ConstantTable.TBL_USER,userDataArray);

        // ----- TABLE ADDRESS
        // 单条数据直接一个JSONObject
        JSONObject addrObj = new JSONObject();
        addrObj.put("id","1");
        addrObj.put("pid","");
        addrObj.put("name","Michael");
        insertObj.put(ConstantTable.TBL_ADDRESS,addrObj);

        return insertObj;
    }

}
