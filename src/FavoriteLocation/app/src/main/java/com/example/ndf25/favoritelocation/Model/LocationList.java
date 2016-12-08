package com.example.ndf25.favoritelocation.Model;

import java.util.ArrayList;

/**
 * Created by ndf25 on 2016/11/29.
 */

public class LocationList {

    // メンバ変数の宣言（Singleton)
    private static LocationList mLocationListInstance = new LocationList();;
    private static ArrayList<LocationDetail> mLocationArrayList = new ArrayList<LocationDetail>();

    // コンストラクタ
    private LocationList(){
    }

    // インスタンスの取得
    public static LocationList getInstance(){
        if (mLocationListInstance == null){
            mLocationListInstance = new LocationList();
            mLocationArrayList = new ArrayList<LocationDetail>();
        }
        return mLocationListInstance;
    }

    public void addLocationList(LocationDetail data) {
        mLocationArrayList.add(data);
    }

    public LocationDetail getLocationList(int index){
        return mLocationArrayList.get(index);
    }

    public ArrayList<LocationDetail> getAllLocationList(){
        return mLocationArrayList;
    }

    public int getListSize(){
        return mLocationArrayList.size();
    }

    public void setLocationName(String locationName, int index){
        mLocationArrayList.get(index).setLocationName(locationName);
    }

    public void setAddress(String address, int index){
        mLocationArrayList.get(index).setAddress(address);
    }

    public void setStation(String station, int index){
        mLocationArrayList.get(index).setStation(station);
    }

    public  void setMemo(String memo, int index){
        mLocationArrayList.get(index).setMemo(memo);
    }

    public String getLocationName(int index){
        return mLocationArrayList.get(index).getLocationName();
    }

    public  String getAddress(int index){
        return mLocationArrayList.get(index).getAddress();
    }

    public  String getStation(int index){
        return mLocationArrayList.get(index).getStation();
    }

    public String getMemo(int index){
        return mLocationArrayList.get(index).getMemo();
    }
}
