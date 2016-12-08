package com.example.ndf25.favoritelocation.Model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ndf25 on 2016/12/08.
 */

public class DrawerItemList {

    // メンバ変数の宣言（Singleton)
    private static DrawerItemList mDrawerListInstance = null;
    private static ArrayList<String> mDrawerMenuList;

    // コンストラクタ
    private DrawerItemList(){
        mDrawerMenuList.add("New Record");
        mDrawerMenuList.add("Settings");
    }

    // インスタンスの取得
    public static DrawerItemList getInstance(){
        if (mDrawerListInstance == null){
            mDrawerMenuList = new ArrayList<String>();
            mDrawerListInstance = new DrawerItemList();
        }
        return mDrawerListInstance;
    }

    // アイテムの設定
    public void setDrawerMenuItem(){

    }

    // アイテムの取得
    public String getDrawerMenuItem(int index){
        return mDrawerMenuList.get(index);
    }

    // アイテムの個数取得
    public int getDrawerMenuListSize(){
        return mDrawerMenuList.size();
    }

    public ArrayList<String> getAllDrawerMenuList(){
        return mDrawerMenuList;
    }
}
