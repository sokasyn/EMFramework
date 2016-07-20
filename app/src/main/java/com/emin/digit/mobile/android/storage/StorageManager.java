package com.emin.digit.mobile.android.storage;

import android.content.Context;
import android.nfc.tech.NfcA;

import java.util.HashMap;

/**
 * Created by Samson on 16/7/20.
 */
public class StorageManager {

    private static HashMap<String,StorageManager> storageMap = new HashMap<String,StorageManager>();

    public static StorageManager getInstance(StorageConfig config) {
        StorageManager instance = storageMap.get(config.name);
        if(instance == null){
            instance = new StorageManager(config);
            storageMap.put(config.name,instance);
        }
        return instance;
    }

    private StorageManager(StorageConfig config) {
    }


    public static StorageManager create(Context context){
        StorageConfig config = new StorageConfig();
        config.setContext(context);
        return create(config);
    }

    public static StorageManager create(Context context, StorageType type){
        StorageConfig config = new StorageConfig();
        config.setContext(context);
        config.setType(type);
        return create(config);
    }

    public static StorageManager create(Context context, StorageType type ,String name){
        StorageConfig config = new StorageConfig();
        config.setContext(context);
        config.setType(type);
        config.setName(name);
        return create(config);
    }

    public static StorageManager create(StorageConfig config){
        return getInstance(config);
    }

    public static class StorageConfig{
        private Context context;
        private StorageType type;
        private String name;


        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public StorageType getType() {
            return type;
        }

        public void setType(StorageType type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private enum StorageType{
        Cache,
        Database,
        Preference,
        File
    }

}
