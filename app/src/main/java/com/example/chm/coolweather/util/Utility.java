package com.example.chm.coolweather.util;

import android.text.TextUtils;

import com.example.chm.coolweather.db.County;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chm on 18-9-6.
 */

public class Utility {
    public static boolean handleResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject=new JSONObject(response);
                JSONArray heWeather=jsonObject.getJSONArray("HeWeather6");
                JSONObject j=heWeather.getJSONObject(0);
                JSONArray basic=j.getJSONArray("basic");
                String status=basic.getString(1);
                if(status!="ok") return false;
                for(int i=0;i<basic.length();i++) {
                    JSONObject county = basic.getJSONObject(i);
                    County county1 = new County();
                    county1.setAdminArea(county.getString("admin_area"));
                    county1.setCid(county.getString("cid"));
                    county1.setCityName(county.getString("parent_city"));
                    county1.setCountyName(county.getString("location"));
                    county1.setCountryName(county.getString("cnty"));
                    county1.save();
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
