package com.emtest.data;

import com.emin.digit.mobile.android.commom.ConstantTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samson on 16/7/1.
 */
public class BuildQueryJSON {

    /*
     * 构建后的JSON
     *    { "T_USER":{
     *         "SELECT":["USER_ID","AGE"],
     *         "WHERE":{"USER_ID":2}
     *       }
     *    }
     */

    public static JSONObject buildQueryForSingleTable() throws JSONException{

        JSONObject queryObj= new JSONObject();
        // 查询的字段
        JSONArray selectObj = getSelectJSON(new String[]{"NICK_NAME","AGE"});
        queryObj.put("SELECT",selectObj);

        // 条件
        JSONObject whereObj = new JSONObject();
        whereObj.put("ID",1);
        queryObj.put("WHERE",whereObj);

        // 目标表
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ConstantTable.TBL_USER,queryObj);
        return jsonObject;
    }

    /**
     * 无SELECT列查询,相当于全表查询
     * SELECT * FROM T_USER WHERE ID = 1;
     *
     * 构建后的JSON
     *    { "T_USER":{
     *         "WHERE":{"USER_ID":2}
     *       }
     *    }
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject buildQueryForSingleTableWithoutSelectColumns() throws JSONException{
        JSONObject queryObj= new JSONObject();

        // 条件
        JSONObject whereObj = new JSONObject();
        whereObj.put("ID",1);
        queryObj.put("WHERE",whereObj);

        // 目标表
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ConstantTable.TBL_USER,queryObj);
        return jsonObject;
    }

    /**
     * 无WHERE条件查询
     * SELECT COLUMN_1,COLUMN_2 FROM T_USER;
     *
     * 构建后的JSON
     *    { "T_USER":{
     *         "SELECT":["USER_ID","AGE"]
     *       }
     *    }
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject buildQueryForSingleTableWithoutWhere() throws JSONException{
        JSONObject queryObj= new JSONObject();
        // 查询的字段
        JSONArray selectObj = getSelectJSON(new String[]{"NICK_NAME","AGE"});
        queryObj.put("SELECT",selectObj);

        // 目标表
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ConstantTable.TBL_USER,queryObj);
        return jsonObject;
    }

    // 通过可变的String数组,构建SELECT部分JSON
    private static JSONArray getSelectJSON(String ... columnsArray) throws JSONException{
        JSONArray ColumnsJson = new JSONArray();
        for(int i = 0 ; i < columnsArray.length; i++){
            ColumnsJson.put(i,columnsArray[i]);
        }
        return ColumnsJson;
    }
}
