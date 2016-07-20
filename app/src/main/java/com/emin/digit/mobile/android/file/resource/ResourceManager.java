package com.emin.digit.mobile.android.file.resource;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;

/**
 * Created by Samson on 16/7/20.
 */
public class ResourceManager {

    public void getFile(Context context,String filename){
        AssetManager assetManager = context.getAssets();
        try{
            assetManager.open(filename);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
