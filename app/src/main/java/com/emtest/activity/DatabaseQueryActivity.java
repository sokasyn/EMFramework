package com.emtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emin.digit.mobile.android.R;

/**
 * Created by Samson on 16/7/27.
 */
public class DatabaseQueryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_query);

        Button btnSingleQuery = (Button)findViewById(R.id.btnQuerySingle);
        btnSingleQuery.setOnClickListener(clickListener);

        Button btnUnionQuery = (Button)findViewById(R.id.btnQueryUnion);
        btnUnionQuery.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnQuerySingle:{
                    toActivity("com.emtest.activity.DatabaseQueryActivity");
                    break;
                }
                case R.id.btnQueryUnion:{
                    toActivity("com.emtest.activity.UnionQueryActivity");
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

    private void toActivity(String actionName){
        Intent intent = new Intent();
        intent.setAction(actionName);
        startActivity(intent);
    }
}
