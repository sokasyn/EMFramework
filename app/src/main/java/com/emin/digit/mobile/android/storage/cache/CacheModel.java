package com.emin.digit.mobile.android.storage.cache;

/**
 * 缓存数据模型
 *
 * Created by Samson on 16/7/18.
 */
public class CacheModel {

    private String cacheId;
    private String cacheName;

    public CacheModel(String name){
        this.cacheName = name;
        this.cacheId = "123456";
    }

    public String getCacheId() {
        return cacheId;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }
}
