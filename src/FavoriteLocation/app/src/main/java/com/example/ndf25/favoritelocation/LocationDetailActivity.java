package com.example.ndf25.favoritelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ndf25.favoritelocation.Control.LocationForm;
import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.Model.LocationList;


public class LocationDetailActivity extends AppCompatActivity {

    // メンバ変数の宣言
    private Button mRegistButton;
    private EditText mLocationNameEditText;
    private EditText mLocationAddressEditText;
    private EditText mLocationMemoEditText;
    private LocationForm mLocationForm;
    private static LocationList mLocationList;
    private static int mIndex = -1;

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

       // リスナーの設定
        View.OnClickListener mRegistButtonClickListener = new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TODO
            }
        };

        mRegistButton.setOnClickListener(mRegistButtonClickListener);

        // EditTextにデータを設定
        LocationDetail selectedItem = mLocationList.getLocationList(mIndex);
        mLocationNameEditText.setText(selectedItem.getLocationName(), TextView.BufferType.NORMAL);
        mLocationAddressEditText.setText(selectedItem.getAddress(), TextView.BufferType.NORMAL);
    }

    public static int getIndex(){
        return mIndex;
    }

    public static void setIndex(int index){
        mIndex = index;
    }
}
