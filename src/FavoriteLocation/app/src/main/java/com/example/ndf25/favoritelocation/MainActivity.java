package com.example.ndf25.favoritelocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ndf25.favoritelocation.Control.LocationForm;
import com.example.ndf25.favoritelocation.Model.DrawerItemList;
import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.Model.LocationList;
import com.example.ndf25.favoritelocation.View.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    // メンバ変数の宣言
    private ListView mMainContentListView;
    private ImageView mImageView;
    private LocationForm mLocationForm;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private static DrawerItemList mDrawerMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // メンバ変数にコントロールを設定
        // コンテンツ部分
        mMainContentListView = (ListView) findViewById(R.id.all_list_view);
        mImageView = new ImageView(this);
        mImageView.setImageResource(R.drawable.top_bg);
        mLocationForm = new LocationForm();

        // ドロワー部分
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_menu);
        mDrawerMenuItem = DrawerItemList.getInstance();

        // イベント設定
        final AdapterView.OnItemClickListener mListViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 選択アイテムを取得
                LocationDetailActivity.setIndex(i);

                // 詳細画面に遷移
                Intent intent = new Intent();
                intent = intent.setClass(MainActivity.this, LocationDetailActivity.class);
                startActivity(intent);
            }
        };
        mMainContentListView.setOnItemClickListener(mListViewListener);
        setNavigationDrawer();

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
        mMainContentListView.setAdapter(mListAdapter);

        String[] mDrawerMenu = new String[mDrawerMenuItem.getDrawerMenuListSize()];
        for (int i = 0; i < mDrawerMenuItem.getDrawerMenuListSize(); i++) {
            mDrawerMenu[i] = mDrawerMenuItem.getDrawerMenuItem(i);
        }

        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerMenu));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setNavigationDrawer(){
        AdapterView.OnItemClickListener drawerListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // ドロワーアイテムが選択された時の処理
            }
        };

        mDrawerListView.setOnItemClickListener(drawerListener);
    }
}



