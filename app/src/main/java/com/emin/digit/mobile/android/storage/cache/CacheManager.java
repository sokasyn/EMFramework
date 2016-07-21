package com.emin.digit.mobile.android.storage.cache;

import java.util.HashMap;

/**
 * Created by Samson on 16/7/18.
 */
public class CacheManager {

    private static HashMap<String,CacheManager> map = new HashMap<String,CacheManager>();

    private CacheModel cacheModel;
    private CacheConfig config;

    private CacheManager(CacheConfig config){
        cacheModel = new CacheModel(config.getKey());
    }

    public synchronized static CacheManager getInstance(CacheConfig config){
        CacheManager cacheManager = map.get(config.getKey());
        if( cacheManager == null) {
            cacheManager = new CacheManager(config);
            map.put(config.getKey(),cacheManager);
        }
        return cacheManager;
    }

    public static CacheManager create(String key){
        CacheConfig config = new CacheConfig();
        config.setKey(key);
        return getInstance(config);


    }

}
