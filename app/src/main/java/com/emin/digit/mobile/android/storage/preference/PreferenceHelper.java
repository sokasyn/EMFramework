package com.emin.digit.mobile.android.storage.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Samson on 16/7/14.
 */
public class PreferenceHelper {

    private static final String DEFAULT_PREF_NAME = "default_prefs";

    // 优化:Context
    public static void writeSharedPreferences(Context context, String prefsName, String name, Object value) {
        if (name == null || value == null) {
            return;
        }
        SharedPreferences user = context.getSharedPreferences(prefsName, 0);
        Editor editor = user.edit();
        if (value instanceof Integer) {
            editor.putInt(name, Integer.parseInt(value.toString()));
        } else if (value instanceof Long) {
            editor.putLong(name, Long.parseLong(value.toString()));
        } else if (value instanceof Boolean) {
            editor.putBoolean(name, Boolean.parseBoolean(value.toString()));
        } else if (value instanceof String) {
            editor.putString(name, value.toString());
        } else if (value instanceof Float) {
            editor.putFloat(name, Float.parseFloat(value.toString()));
        }
        editor.commit();
    }
}
