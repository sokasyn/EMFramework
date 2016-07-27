package com.emtest.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.emin.digit.mobile.android.R;
import com.emin.digit.mobile.android.storage.database.util.DebugLog;
import com.emin.digit.mobile.android.storage.database.v2.DatabaseManager;
import com.emtest.data.BuildQueryJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Samson on 16/7/27.
 */
public class SingleQueryActivity extends Activity {

    private static final String TAG = SingleQueryActivity.class.getSimpleName();

    // Buttons
    private Button btnQueryStandard;
    private Button btnQueryNoSelect;
    private Button  btnQueryNoWhere;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_query_single);
        setupComponents();
        setupData();
    }

    private void setupComponents(){
        btnQueryStandard = (Button)findViewById(R.id.btnQueryStandard);
        btnQueryStandard.setOnClickListener(clickListener);

        btnQueryNoSelect = (Button)findViewById(R.id.btnQueryNoSelect);
        btnQueryNoSelect.setOnClickListener(clickListener);

        btnQueryNoWhere = (Button)findViewById(R.id.btnQueryNoWhere);
        btnQueryNoWhere.setOnClickListener(clickListener);

        listView = (ListView)findViewById(R.id.lvQuery);

    }

    private void setupData(){
        updateListView();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnQueryStandard:{
                    queryStandard();
                    break;
                }
                case R.id.btnQueryNoSelect:{
                    queryWithoutSelect();
                    break;
                }
                case R.id.btnQueryNoWhere:{
                    queryWithoutWhere();
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

    /*
     * {"USER":{"SELECT":["NICK_NAME","AGE"],"WHERE":{"ID":1}}}
     */

    private void queryStandard(){
        try{
            JSONObject queryObj = BuildQueryJSON.buildQueryForSingleTable();
            DebugLog.d(TAG,"JSON for query standard:" + queryObj.toString());
            JSONArray result = DatabaseManager.getInstance(this).query(queryObj);
            DebugLog.d(TAG,"Query result:" + result.toString());
        }catch (JSONException e){
            Toast.makeText(this,"JSONException occurred!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void queryWithoutSelect(){

        try{
            JSONObject queryObj = BuildQueryJSON.buildQueryForSingleTableWithoutSelectColumns();
            DebugLog.d(TAG,"JSON for query no select:" + queryObj.toString());
            JSONArray result = DatabaseManager.getInstance(this).query(queryObj);
            DebugLog.d(TAG,"Query result:" + result.toString());
        }catch (JSONException e){
            Toast.makeText(this,"JSONException occurred!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void queryWithoutWhere(){
        try{
            JSONObject queryObj = BuildQueryJSON.buildQueryForSingleTableWithoutWhere();
            DebugLog.d(TAG,"JSON for query no where:" + queryObj.toString());
            JSONArray result = DatabaseManager.getInstance(this).query(queryObj);
            DebugLog.d(TAG,"Query result:" + result.toString());
        }catch (JSONException e){
            Toast.makeText(this,"JSONException occurred!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // - - - - - - - - - - - - - - - Test ListView - - - - - - - - - - - - - - -
    private void updateListView(){
//        updateListViewWithArrayAdaper();
        updateListViewWithSimpleAdapter();
    }



    private void updateListViewWithArrayAdaper(){
//        String[] arrayData = getArray();
//        ListAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayData);

        List<String> listData = getArrayListData();
        ListAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,listData);
        listView.setAdapter(arrayAdapter);
    }

    private String[] getArray(){
        String[] array = new String[]{
                "test1","test2","test3",
                "test4","test5","test6",
                "test7","test8","test9",
                "test10","test11","test12"};
        return array;
    }

    private List<String> getArrayListData(){
        List<String> list = new ArrayList<String>();
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        list.add("Test4");
        list.add("Test5");
        return list;
    }

    private void updateListViewWithSimpleAdapter(){
        SimpleAdapter adapter = new SimpleAdapter(this, getMapData(),
                R.layout.list_test, new String[] { "ID", "NickName", "Age" },
                new int[] { R.id.listNum, R.id.nickName, R.id.age });
        listView.setAdapter(adapter);
    }


//    public SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
    private List<Map<String,Object>> getMapData(){

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ID", "1");
        map.put("NickName", "1ABCDEFGFEFEFEF");
        map.put("Age", "1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ID", "2");
        map.put("NickName", "2ABCDEFGFEFEFEF");
        map.put("Age", "2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("ID", "3");
        map.put("NickName", "3ABCDEFGFEFEFEF");
        map.put("Age", "3");
        list.add(map);

        return list;
    }

    private void queryForException(){

    }
}
