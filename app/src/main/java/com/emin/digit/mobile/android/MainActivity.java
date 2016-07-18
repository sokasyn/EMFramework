package com.emin.digit.mobile.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emin.digit.mobile.android.storage.cache.ACache;
import com.emin.digit.mobile.android.util.StringUtil;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
    }

    private void setupComponents(){
        btnStart = (Button)findViewById(R.id.btnGo);
        btnStart.setOnClickListener(clickListener);

        EditText inputText = (EditText)findViewById(R.id.idInputText);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGo:{
                    btnGoClicked();
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

    private void btnGoClicked(){
        testCache(); // 测试缓存
    }

    private void testCache(){
        String strInput = inputText.getText().toString();
        debug(strInput);
        if(StringUtil.isBlank(strInput) || StringUtil.isEmpty(strInput)){
            return;
        }
        ACache cache = ACache.get(this);
        cache.put("key_name", "Samson", 10); // 保存10秒，测试10秒之后会不会自动删除
    }

    private void debug(String stringInfo){
        System.out.println(stringInfo);
    }
}
