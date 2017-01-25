package com.example.ndf25.favoritelocation;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ndf25.favoritelocation.Control.LocationForm;
import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.Model.LocationList;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class LocationDetailActivity extends Activity implements OnMapReadyCallback {

    // メンバ変数の宣言
    private Button mRegistButton;
    private EditText mLocationNameEditText;
    private EditText mLocationAddressEditText;
    private EditText mLocationMemoEditText;
    private LocationForm mLocationForm;
    private static LocationList mLocationList = LocationList.getInstance();
    private static int mIndex = -1;
    private LocationDetail mItemDetail;
    public static boolean misNewItem = false;
    public LocationManager mLocationManager;
    public Location mCurrentLocate;
    private String TABLE_NAME = "locationDB";
    private SQLiteDatabase db;
    private MapView mMap;
    private MapFragment mMapFragment;
    private FragmentManager mFragmentManeger;
    private GoogleMap mGoogleMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.location_detail);

        // マップを取得して初期化の検知を登録
        MapFragment map = (MapFragment) getFragmentManager().findFragmentById( R.id.map );
        map.getMapAsync( this );

        // メンバ変数にコントロールを設定
        mRegistButton = (Button) findViewById(R.id.location_regist);
        mLocationNameEditText = (EditText) findViewById(R.id.location_name);
        mLocationAddressEditText = (EditText) findViewById(R.id.location_address);
        mLocationMemoEditText = (EditText) findViewById(R.id.location_memo);
        mMapFragment = MapFragment.newInstance();
        mFragmentManeger = getFragmentManager();

        // 新規作成の場合
        if (misNewItem == true) {
            mItemDetail = new LocationDetail();
        } else {
            mItemDetail = mLocationList.getLocationList(mIndex);
        }

        // リスナーの設定
        View.OnClickListener mRegistButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 登録ボタンが押されたら、入力情報をモデルに格納
                mItemDetail.setLocationName((mLocationNameEditText.getText()).toString());
                mItemDetail.setAddress((mLocationAddressEditText.getText()).toString());
                mItemDetail.setMemo((mLocationMemoEditText.getText()).toString());

                if (misNewItem == true) {
                    mLocationList.addLocationList(mItemDetail);
                }

                // saveDb(misNewItem);
            }
        };

//        MapFragment mapobj = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//        if( mapobj != null){
//            // Mapは利用可能
//        }
//        FragmentTransaction ft  = mFragmentManeger.beginTransaction();
//        ft.add(android.R.id.content, mMapFragment);
//        ft.commit();

        mRegistButton.setOnClickListener(mRegistButtonClickListener);

    }

    @Override
    public void onMapReady( GoogleMap googleMap ) {
        mGoogleMap = googleMap;

        // 新規作成する場合
        if (misNewItem == true) {
                mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                if (mLocationManager != null) {
                    // パーミッションの確認
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mCurrentLocate = mLocationManager.getLastKnownLocation("gps");
                       // mGoogleMap.setMyLocationEnabled(true);

                        // 現在の緯度経度を取得
                        Location currentPosition = getCurrentPosition();
                        if (currentPosition != null) {
                            final double latitude = currentPosition.getLatitude();
                            final double longitude = currentPosition.getLongitude();

                            if (!Geocoder.isPresent()) return;
                            final Geocoder coder = new Geocoder(this);
                            // 地図表示
                            CameraPosition cameraPos = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15.0f).bearing(0).build();
                            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));

                            // 現在地住所を取得
                            new Thread (new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        List<android.location.Address> addresses = coder.getFromLocation(latitude, longitude, 1);
                                        setText(addresses);
                                    } catch (IOException e){
                                        Log.d("", e.toString());
                                    }
                                }
                            }) .start();
                        }
                    }
                }
        } else{
            Geocoder gcoder = new Geocoder(this, Locale.getDefault());
            List<android.location.Address> lstAddr;
            try {
                lstAddr = gcoder.getFromLocationName(mItemDetail.getAddress().toString(), 1);
                if (lstAddr != null && lstAddr.size() > 0) {
                    // 緯度/経度取得
                    android.location.Address targetAddress = lstAddr.get(0);
                    double latitude = (double) (targetAddress.getLatitude());
                    double longitude = (double) (targetAddress.getLongitude());

                    // 地図表示
                   CameraPosition cameraPos = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15.0f).bearing(0).build();
                   mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
//                    CameraPosition cameraPos = mGoogleMap.getCameraPosition();
//                    Barcode.GeoPoint gpo = new Barcode.GeoPoint(latitude, longitude);
//                    mGoogleMap.s
                }
              }catch (IOException e) {
            }
        }
    }

    public void setText(List<android.location.Address> addresses)
    {
        android.location.Address targetAddress = addresses.get(0);

        StringBuilder sb = new StringBuilder();

        if (targetAddress.getAdminArea() != null){
            sb.append(targetAddress.getAdminArea());
        }
        if (targetAddress.getLocality() != null){
            sb.append(targetAddress.getLocality());
        }
        if (targetAddress.getSubLocality() != null){
            sb.append(targetAddress.getSubLocality());
        }
        if (targetAddress.getThoroughfare() != null){
            sb.append(targetAddress.getThoroughfare());
        }
        if (targetAddress.getSubThoroughfare() != null){
            sb.append(targetAddress.getSubThoroughfare());
        }
        if (targetAddress.getFeatureName() != null){
            sb.append("番地");
            sb.append(targetAddress.getFeatureName());
        }

        final String editAddress = sb.toString();
        mItemDetail.setAddress(editAddress);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLocationAddressEditText.setText(editAddress, TextView.BufferType.NORMAL);
            }
        });
    }

    public void saveDb(boolean isNewItem) {
        if (isNewItem == true) {
            String sql = "insert into " + TABLE_NAME + " (name,address,memo)" + " values('" + mItemDetail.getLocationName() + "'," + mItemDetail.getAddress() + "'," + mItemDetail.getMemo() + ");";
            try {
                db.execSQL(sql);
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        } else {
            String sql = "update " + TABLE_NAME + " set " + "name='" + mItemDetail.getLocationName() + "',address=" + mItemDetail.getAddress() + ",memo=" + mItemDetail.getMemo() + " where id=" + (mIndex + 1) + ";";
            try {
                db.execSQL(sql);
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
    }

    public  Location getCurrentPosition() {
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mCurrentLocate = mLocationManager.getLastKnownLocation("gps");
            }
        }
        return mCurrentLocate;
    }

    public static int getIndex() {
        return mIndex;
    }

    public static void setIndex(int index) {
        mIndex = index;
    }

    @Override
    protected void onPause() {
        super.onPause();
        misNewItem = false;
    }


    @Override
    public void onStart() {
        super.onStart();
        mLocationNameEditText.setText(mLocationList.getLocationName(mIndex), TextView.BufferType.NORMAL);
        mLocationAddressEditText.setText(mLocationList.getAddress(mIndex), TextView.BufferType.NORMAL);
        mLocationMemoEditText.setText(mLocationList.getMemo(mIndex), TextView.BufferType.NORMAL);

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
