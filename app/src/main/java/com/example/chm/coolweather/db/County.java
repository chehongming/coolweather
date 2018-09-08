package com.example.chm.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by chm on 18-9-4.
 */

public class County extends DataSupport{
    private String countyName;
    private String cityName;
    private String cid;
    private String parentCity;
    private String adminArea;
    private String countryName;
    public String getCid(){return cid;}
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    //public String getParentCity() {return parentCity;}
    //public void setParentCity(String parentCity){this.parentCity=parentCity;}
    public String getAdminArea(){return adminArea;}
    public void setAdminArea(String adminArea){this.adminArea=adminArea;}
    public String getCountryName(){return countryName;}
    public void setCountryName(String countryName){this.countryName=countryName;}
}
