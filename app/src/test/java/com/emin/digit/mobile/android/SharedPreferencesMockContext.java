package com.emin.digit.mobile.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.mock.MockContext;

/**
 * Created by Samson on 16/7/15.
 */
public class SharedPreferencesMockContext extends MockContext {

    private static final String PREFIX = "test.";

    private Context mBaseContext;

    public SharedPreferencesMockContext(Context context) {
        mBaseContext = context;
    }

    @Override
    public String getPackageName() {
        return mBaseContext.getPackageName();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return mBaseContext.getSharedPreferences(PREFIX + name, mode);
    }
}
