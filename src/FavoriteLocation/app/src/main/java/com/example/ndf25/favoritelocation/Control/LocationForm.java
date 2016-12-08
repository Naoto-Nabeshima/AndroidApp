package com.example.ndf25.favoritelocation.Control;

import com.example.ndf25.favoritelocation.Model.LocationList;

/**
 * Created by ndf25 on 2016/12/07.
 */

public class LocationForm {

    private  LocationList mLocationList;

    public LocationForm(){
        mLocationList = LocationList.getInstance();
    }

    public void setLocationName(String locationName, int index){
        mLocationList.setLocationName(locationName, index);
    }

    public void setAddress(String address, int index){
        mLocationList.setAddress(address, index);
    }

    public void setStation(String station, int index){
        mLocationList.setStation(station, index);
    }

    public  void setMemo(String memo, int index){
        mLocationList.setMemo(memo, index);
    }

    public String getLocationName(int index){
        return mLocationList.getLocationName(index);
    }

    public  String getAddress(int index){
        return mLocationList.getAddress(index);
    }

    public  String getStation(int index){
        return mLocationList.getStation(index);
    }

    public String getMemo(int index){
        return mLocationList.getMemo(index);
    }
}
