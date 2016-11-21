package com.example.ndf25.favoritelocation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ndf25 on 2016/11/21.
 */

public class SearchBoxFragment extends Fragment {

    // コンストラクタ
    public void SearchBoxFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.search_box, container, false);
        return RootView;
    }
}
