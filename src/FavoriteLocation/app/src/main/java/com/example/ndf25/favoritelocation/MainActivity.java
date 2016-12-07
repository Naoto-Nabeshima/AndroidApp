package com.example.ndf25.favoritelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ndf25.favoritelocation.Control.LocationForm;
import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.Model.LocationList;
import com.example.ndf25.favoritelocation.View.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    // メンバ変数の宣言
    private ListView mListView;
    private ImageView mImageView;
    private LocationForm mLocationForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // メンバ変数にコントロールを設定
        mListView = (ListView) findViewById(R.id.all_list_view);

        mImageView = new ImageView(this);
        mImageView.setImageResource(R.drawable.top_bg);

        mLocationForm = new LocationForm();

        // リスナーの設定
        final AdapterView.OnItemClickListener mListViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // 選択アイテムを取得
                mLocationForm.setSelectedIndex(i);

                // 詳細画面に遷移
                Intent intent = new Intent();
                intent = intent.setClass(MainActivity.this, LocationDetailActivity.class);
                startActivity(intent);
            }
        };

         mListView.setOnItemClickListener(mListViewListener);

        // ListViewにデータを設定
        LocationList locationListModel = LocationList.getInstance();

        LocationDetail temp = new LocationDetail();
        LocationDetail temp2 = new LocationDetail();
        LocationDetail temp3 = new LocationDetail();

        temp.setLocationName("sample1");
        temp.setAddress("");
        temp.setMemo("");
        temp.setStation("");

        temp2.setLocationName("sample2");
        temp2.setAddress("");
        temp2.setMemo("test");
        temp2.setStation("");
        temp3.setLocationName("sample3");
        temp3.setAddress("");
        temp3.setMemo("");
        temp3.setStation("");

        locationListModel.addLocationList(temp);
        locationListModel.addLocationList(temp2);
        locationListModel.addLocationList(temp3);


        CustomAdapter mListAdapter = new CustomAdapter(this, R.layout.support_simple_spinner_dropdown_item, locationListModel.getAllLocationList());
        mListView.setAdapter(mListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
