package com.emin.digit.mobile.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emin.digit.mobile.android.storage.cache.FileCache;
import com.emin.digit.mobile.android.storage.database.v2.DatabaseAdaptor;
import com.emin.digit.mobile.android.storage.database.v2.DatabaseHybrid;
import com.emin.digit.mobile.android.util.StringUtil;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnShow;
    private EditText keyText;
    private EditText valueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
    }

    private void setupComponents(){
        btnStart = (Button)findViewById(R.id.btnGo);
        btnStart.setOnClickListener(clickListener);

        btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(clickListener);

        keyText = (EditText)findViewById(R.id.idKey);
        valueText = (EditText)findViewById(R.id.idValue);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGo:{
                    btnGoClicked();
                    break;
                }
                case R.id.btnShow:{
                    btnShowClicked();
//                    testExpired();
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

    private void btnGoClicked(){
        // 测试缓存
        // Key
        String inputKey = getInputKey();
        // value
        String inputValue = getInputValue();

        // 创建ACache实例
        FileCache cache = FileCache.get(this);

        // 1468894924463-60 Samson
        // 1468894924463-0 A
//        cache.put(inputKey, inputValue, 60 * 5);
//        testProperties();
        testDbAdaptor();

    }

    private void testDbAdaptor(){
        DatabaseAdaptor dbAdaptor = new DatabaseAdaptor(new DatabaseHybrid());
        dbAdaptor.insert(new JSONObject());


        DatabaseAdaptor db = DatabaseAdaptor.getIn
    }

    private void testProperties(){

        Properties prop = new Properties();
        try{
            InputStream is = this.getResources().openRawResource(R.raw.account);
            prop.load(is);
            prop.list(System.out);
        }catch (IOException e){
            e.printStackTrace();
        }

        String account =  prop.getProperty("account");
        debug("get account name from property:" + account);

        String password =  prop.getProperty("password");
        debug("get account password from property:" + password);

//        TimeUnit.SECONDS;
    }

    private void btnShowClicked(){
        String inputKey = getInputKey();
        FileCache cache = FileCache.get(this);
        String valueForKey = cache.getAsString(inputKey);
        valueText.setText(valueForKey);
    }

    private String getInputKey(){
        // key
        String strKey = keyText.getText().toString();
        debug("input key:" + strKey);
        if(StringUtil.isBlank(strKey) || StringUtil.isEmpty(strKey)){
            debug("input key is blank or empty!");
            return "";
        }
        return strKey;
    }

    private String getInputValue(){
        String strValue = valueText.getText().toString();
        debug("input value:" + strValue);
        if(StringUtil.isBlank(strValue) || StringUtil.isEmpty(strValue)){
            debug("input value is blank or empty!");
            return "";
        }
        return strValue;
    }

    // 1468894924463-60 Samson
    private byte[] copyRangeOf(byte[] data,int from ,int to){
        int newLength = to - from;
        byte[] newData = new byte[newLength];
        System.arraycopy(data,from,newData,0,newLength);
        return newData;
    }

    private void testExpired(){

        String testString = "1468894924463-1 Samson";
//        String testString = System.currentTimeMillis() + "-60 Samson";
        String value = "Sokasyn";
        int keepCachedTime = 50;
        put(value,keepCachedTime);

        if(isExpired(testString)){
            debug("isExpired");
        }else{
            debug("is not expired");
        }
    }


    // 将时间信息拼接上原始数据
    private void put(String data, int cachedSeconds){
        String dateInfo = createDateInfo(data,cachedSeconds);
        debug("create date info:" + dateInfo);
        String value = dateInfo + data;
        debug("build value:" + value);
    }

    // 中横线连接创建时间和保存时间
    private static final String SEPARATOR_HYPHEN = "-";

    // 空格连接时间信息和实际要保存的数据
    private static final String SEPARATOR_SPACE = " ";

    /*
     * 满足自定义的时间格式的数据
     */
    private boolean hashDateInfo(String data){
        if(data!= null && data.length() > 15 && data.indexOf(SEPARATOR_HYPHEN) == 13
                && data.indexOf(SEPARATOR_SPACE) > 14){
            return true;
        }
        return false;
    }

    // 判断缓存的数据是否过期
    private boolean isExpired(String data){
        debug("test data:" + data);

        // 不存在自定义的时间格式,说明是无限期的缓存
        if(!hashDateInfo(data)){
            debug("has no date info");
            return false;
        }

        // 取出数据的时间戳:创建的时间戳
        long createMillis = Long.parseLong(getCreateDate(data));

        // 取出数据缓存时间:单位为秒
        long cachedSeconds = Long.parseLong(getCachedSeconds(data));

        // 与当前时间比较,判断时候过期
        long currentMillis = System.currentTimeMillis();
        if(currentMillis > (createMillis + cachedSeconds)){
            return true;
        }
        return false;
    }

    // 1468894924463-60 Samson
    private String getCreateDate(String data){
        String cratedMillisString =  data.substring(0,13);
        debug("cratedMillisString :" + cratedMillisString);
        return cratedMillisString;
    }

    private String getCachedSeconds(String string){
        int index = string.indexOf(" ");
        String cachedSecondsString =  string.substring(14,index);
        debug("cachedSecondsString :" + cachedSecondsString);
        return cachedSecondsString;
    }

    private String createDateInfo(String data,int cahedSeconds){
        return getCurrentMillis() + SEPARATOR_HYPHEN + cahedSeconds + SEPARATOR_SPACE;
    }

    private long getCurrentMillis(){
        return System.currentTimeMillis();
    }

    private void debug(String stringInfo){
        System.out.println(stringInfo);
    }


    private void testPropertyFile(){
        Properties property = new Properties();
        String filePath = "";
        try{
            FileInputStream fis = new FileInputStream(filePath);
            property.load(fis);
        }catch (FileNotFoundException fileNotFoundE){
            fileNotFoundE.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        String name = property.getProperty("name","");
    }

}
