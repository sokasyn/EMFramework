package com.emin.digit.mobile.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by Samson on 16/7/15.
 */
public class AppApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }


}
