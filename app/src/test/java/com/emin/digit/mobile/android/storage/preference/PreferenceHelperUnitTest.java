package com.emin.digit.mobile.android.storage.preference;

import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Test;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockApplication;
import android.test.mock.MockContext;

import com.emin.digit.mobile.android.SharedPreferencesMockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by Samson on 16/7/15.
 */

public class PreferenceHelperUnitTest extends AndroidTestCase{

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testWriteSharedPreferences(){
//        Context context  = getContext();
//        System.out.println("conext:" + context);
        long ct = System.currentTimeMillis();
        debug("currentTimeMillis :" + ct);


        long milliSecsPerYear = 365 * 24 * 60 *60 *1000;
        debug("milliSecsPerYear:" + milliSecsPerYear + "");

        //     1 471 228 928
        //   147 122 892 800
        // 1 468 834 597 873
        // 9223372036854775807

//        Date nowDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");
//        String dateString = dateFormat.format(nowDate);
//        debug("Template Date String:" + dateString);
//
//        debug("Default date string:" + nowDate.toString());
    }

    @Test
    public void testHas(){
        testPid();
        String key = "name";
        debug("key:" + key.hashCode());

        String key2 = "first name";
        debug("key2:" + key2.hashCode());
    }

    @Test
    public void testPid(){
        String pid1  = "_" + android.os.Process.myPid();
        String pid2  = "_" + android.os.Process.myPid();
        String pid3  = "_" + android.os.Process.myPid();
        debug("pid1 :" + pid1);
        debug("pid2 :" + pid2);
        debug("pid3 :" + pid1);

    }


    private void debug(String debugInfo){
        System.out.println(debugInfo);
    }

    /*
    @Mock
    private Context mMockContext;

    private Context mContext;


    @Mock
    private SharedPreferences mMockSharedPreferences;

//    private RenamingDelegatingContext mContext;

    @Before
    public void setUp() throws Exception {
//        mContext = new RenamingDelegatingContext(getContext(),"test_Prefs_");
//        initMockContextWithFakeCurrentTime();
        initMockContext();
    }

    private void initMockContext(){
        final SharedPreferencesMockContext mockContext = new SharedPreferencesMockContext(new MockContext());
        MockApplication mockApplication = new MockApplication(){
            @Override
            public Context getApplicationContext(){
                return  mockContext;
            }
        };

        mContext = mockContext;
    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void writeSharedPreferences() throws Exception {
        System.out.println("testWriteSharedPreferences");
        System.out.println("mMockContext:" + mMockContext);
        System.out.println("mContext:" + mContext);
        PreferenceHelper.writeSharedPreferences(mContext,"ABC","name","Samson");
    }

    private void initMockContextWithFakeCurrentTime() {
//        when(mMockContext.getSharedPreferences("mock_data", Context.MODE_PRIVATE)).thenReturn(mMockSharedPreferences);
//        when(mMockContext).thenReturn(mMockContext);
//        when(mMockContext.getApplicationContext()).thenReturn(mMockContext);
    }*/
}