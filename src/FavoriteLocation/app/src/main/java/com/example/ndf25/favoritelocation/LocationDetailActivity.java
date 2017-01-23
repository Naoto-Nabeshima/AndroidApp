package com.example.ndf25.favoritelocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ndf25.favoritelocation.Control.LocationForm;
import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.Model.LocationList;
//import com.google.android.maps.MapActivity;
import com.google.android.gms.maps.GoogleMap;


public class LocationDetailActivity extends AppCompatActivity {

    // メンバ変数の宣言
    private Button mRegistButton;
    private EditText mLocationNameEditText;
    private EditText mLocationAddressEditText;
    private EditText mLocationMemoEditText;
    private LocationForm mLocationForm;
    private static LocationList mLocationList;
    private static int mIndex = -1;
    private  LocationDetail mItemDetail;
    public static boolean misNewItem = false;
    public LocationManager mLocationManager;
    public Location mCurrentLocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_detail);

        // メンバ変数にコントロールを設定
        mRegistButton = (Button)findViewById(R.id.location_regist);
        mLocationNameEditText = (EditText)findViewById(R.id.location_name);
        mLocationAddressEditText = (EditText)findViewById(R.id.location_address);
        mLocationMemoEditText = (EditText)findViewById(R.id.location_memo);

        mLocationForm = new LocationForm();
        mLocationList = LocationList.getInstance();

        // EditTextにデータを設定
        if (misNewItem == false){
            mItemDetail = mLocationList.getLocationList(mIndex);
        }
        else {
            mItemDetail = new LocationDetail();
        }
        mLocationNameEditText.setText( mItemDetail.getLocationName(), TextView.BufferType.NORMAL);
        mLocationAddressEditText.setText( mItemDetail.getAddress(), TextView.BufferType.NORMAL);
        mLocationMemoEditText.setText( mItemDetail.getMemo(), TextView.BufferType.NORMAL);

       // リスナーの設定
        View.OnClickListener mRegistButtonClickListener = new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 登録ボタンが押されたら、入力情報をモデルに格納
                 mItemDetail.setLocationName((mLocationNameEditText.getText()).toString());
                 mItemDetail.setAddress((mLocationAddressEditText.getText()).toString());
                 mItemDetail.setMemo((mLocationMemoEditText.getText()).toString());

                if (misNewItem == true){
                    mLocationList.addLocationList(mItemDetail);
                }
            }
        };

        mRegistButton.setOnClickListener(mRegistButtonClickListener);

        // 現在地の取得
        getCurrentPosition();
    }

    public void getCurrentPosition(){

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (mLocationManager != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mCurrentLocate = mLocationManager.getLastKnownLocation("gps");
               double temp = mCurrentLocate.getLatitude();
            }
        }
    }

    public static int getIndex(){
        return mIndex;
    }

    public static void setIndex(int index){
        mIndex = index;
    }

    @Override
    protected void onPause() {
        super.onPause();
        misNewItem = false;
    }
}
