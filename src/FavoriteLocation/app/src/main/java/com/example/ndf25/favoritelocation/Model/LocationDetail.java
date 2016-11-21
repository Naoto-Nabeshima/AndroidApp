package com.example.ndf25.favoritelocation.Model;

/**
 * Created by ndf25 on 2016/11/21.
 */

public class LocationDetail {
    String mLocationName;
    String mAddress;
    String mStation;
    String mMemo;

    public void SetLocationName(String locationName){
        mLocationName = locationName;
    }

    public void SetAddress(String address){
        mAddress = address;
    }

    public void SetStation(String station){
        mStation = station;
    }

    public  void SetMemo(String memo){
        mMemo = memo;
    }

    public String GetLocationName(){
        return mLocationName;
    }

    public  String GetAddress(){
        return mAddress;
    }

    public  String GetStation(){
        return  mStation;
    }

    public String GetMemo(){
        return mStation;
    }
}
