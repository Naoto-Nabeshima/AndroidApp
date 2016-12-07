package com.example.ndf25.favoritelocation.Model;

/**
 * Created by ndf25 on 2016/11/21.
 */

public class LocationDetail {
    private String mLocationName;
    private String mAddress;
    private String mStation;
    private String mMemo;

    public LocationDetail(){
//        mLocationName = locationName;
//        mAddress = address;
//        mStation = station;
//        mMemo = memo;
    }

    public void setLocationName(String locationName){
        mLocationName = locationName;
    }

    public void setAddress(String address){
        mAddress = address;
    }

    public void setStation(String station){
        mStation = station;
    }

    public  void setMemo(String memo){
        mMemo = memo;
    }

    public String getLocationName(){
        return mLocationName;
    }

    public  String getAddress(){
        return mAddress;
    }

    public  String getStation(){
        return  mStation;
    }

    public String getMemo(){
        return mStation;
    }
}
