package com.example.chm.coolweather.util;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by chm on 18-9-6.
 */

public class Utility {
    public static boolean handleResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject=new JSONObject(response);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
