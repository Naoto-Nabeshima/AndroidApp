package com.example.ndf25.favoritelocation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.ndf25.favoritelocation.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        // メイン画面に検索ボックスを表示
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new SearchBoxFragment()).commit();
        }
    }
}
