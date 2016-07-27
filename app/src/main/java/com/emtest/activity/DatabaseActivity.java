package com.emtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emin.digit.mobile.android.R;
import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.DatabaseManager;
import com.emtest.data.BuildInsertJSON;
import com.emtest.data.BuildQueryJSON;
import com.emtest.data.BuildTableJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Samson on 16/7/27.
 */
public class DatabaseActivity extends Activity{

    private static final String TAG = DatabaseActivity.class.getSimpleName();

    private Button btnTableCreate;
    private Button btnTableDrop;
    private Button btnTableAlter;

    private Button btnRecordInsert;
    private Button btnRecordDel;
    private Button btnRecordUpdate;
    private Button btnRecordQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        setupComponents();
    }

    // 界面控件的配置
    private void setupComponents(){

        btnTableCreate = (Button)findViewById(R.id.btnTblCreate);
        btnTableCreate.setOnClickListener(clickListener);

        btnTableDrop = (Button)findViewById(R.id.btnTblDrop);
        btnTableDrop.setOnClickListener(clickListener);

        btnTableAlter = (Button)findViewById(R.id.btnTblAlter);
        btnTableAlter.setOnClickListener(clickListener);

        btnRecordInsert = (Button)findViewById(R.id.btnRecordInsert);
        btnRecordInsert.setOnClickListener(clickListener);

        btnRecordDel = (Button)findViewById(R.id.btnRecordDel);
        btnRecordDel.setOnClickListener(clickListener);

        btnRecordUpdate = (Button)findViewById(R.id.btnRecordUpdate);
        btnRecordUpdate.setOnClickListener(clickListener);

        btnRecordQuery = (Button)findViewById(R.id.btnRecordQuery);
        btnRecordQuery.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnTblCreate:{
                    btnTblCreateClicked();
                    break;
                }
                case R.id.btnTblDrop:{
                    btnTblDropClicked();
                    break;
                }
                case R.id.btnTblAlter:{
                    btnTblAlterClicked();
                    break;
                }
                case R.id.btnRecordInsert:{
                    btnRecordInsertClicked();
                    break;
                }
                case R.id.btnRecordDel:{
                    btnRecordDelClicked();
                    break;
                }
                case R.id.btnRecordUpdate:{
                    btnRecordUpdateClicked();
                    break;
                }
                case R.id.btnRecordQuery:{
                    btnRecordQueryClicked();
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

    // - - - - - - - - - - - - - 按钮点击事件 - - - - - - - - - - - - -
    private void btnTblCreateClicked(){
        createTable();
    }

    private void btnTblDropClicked(){

    }

    private void btnTblAlterClicked(){

    }

    private void btnRecordInsertClicked(){
        insertRecords();
    }

    private void btnRecordDelClicked(){

    }

    private void btnRecordUpdateClicked(){

    }

    private void btnRecordQueryClicked(){
        queryRecords();
    }

    // - - - - - - - - - - - - - 数据库操作 - - - - - - - - - - - - -
    /*
    {"ACCOUNT":"id,name,password",
    "USER":"id int PRIMARY KEY NOT NULL,nick_name varchar(16) NOT NULL,age integer,address_id int",
    "ADDRESS":"id,pid,name"}
     */
    private void createTable(){
        try {
            JSONObject createObj = BuildTableJSON.buildCreateTable();
            DebugLog.d(TAG,"JSON of create table:" + createObj.toString());
            DatabaseManager.getInstance(this).createTable(createObj);
        }catch (JSONException e){
            Toast.makeText(this,"Error occurred..",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /*
     *{
     * "ACCOUNT":[{"id":"1","name":"sokasyn1","password":"1"},
     *            {"id":"2","name":"sokasyn2","password":"2"},
     *            {"id":"3","name":"sokasyn3","password":"3"},
     *            {"id":"4","name":"sokasyn4","password":"4"},
     *            {"id":"5","name":"sokasyn5","password":"5"},
     *            {"id":"6","name":"sokasyn6","password":"6"},
     *            {"id":"7","name":"sokasyn7","password":"7"},
     *            {"id":"8","name":"sokasyn8","password":"8"},
     *            {"id":"9","name":"sokasyn9","password":"9"},
     *            {"id":"10","name":"sokasyn10","password":"10"}],
     * "USER":[{"id":1,"nick_name":"Sam1","age":"1"},
     *         {"id":2,"nick_name":"Sam2","age":"2"},
     *         {"id":3,"nick_name":"Sam3","age":"3"},
     *         {"id":4,"nick_name":"Sam4","age":"4"},
     *         {"id":5,"nick_name":"Sam5","age":"5"},
     *         {"id":6,"nick_name":"Sam6","age":"6"},
     *         {"id":7,"nick_name":"Sam7","age":"7"},
     *         {"id":8,"nick_name":"Sam8","age":"8"},
     *         {"id":9,"nick_name":"Sam9","age":"9"},
     *         {"id":10,"nick_name":"Sam10","age":"10"}],
     * "ADDRESS":{"id":"1","pid":"","name":"Michael"}
     * }
     */

    private void insertRecords(){
        try {
            JSONObject insertObj = BuildInsertJSON.buildInsertRecords();
            DebugLog.d(TAG,"JSON of insert records:" + insertObj.toString());
            DatabaseManager.getInstance(this).insert(insertObj);
        } catch (JSONException e) {
            Toast.makeText(this,"JSONException occurred!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void queryRecords(){
        Intent intent = new Intent();
        intent.setAction("com.emtest.activity.SingleQueryActivity");
        startActivity(intent);
    }

}
