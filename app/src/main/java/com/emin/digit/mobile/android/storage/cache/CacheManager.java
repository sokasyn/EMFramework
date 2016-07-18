package com.emin.digit.mobile.android.storage.cache;

import java.util.HashMap;

/**
 * Created by Samson on 16/7/18.
 */
public class CacheManager {

    private static HashMap dataMap = new HashMap();

    // 单例
    private static CacheManager ourInstance = new CacheManager();

    // 获取单例
    public static CacheManager getInstance() {
        return ourInstance;
    }

    // 私有构造方法
    private CacheManager() {

    }

    private static String myPid() {
        return "_" + android.os.Process.myPid();
    }




}
