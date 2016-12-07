package com.example.ndf25.favoritelocation.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ndf25.favoritelocation.Model.LocationDetail;
import com.example.ndf25.favoritelocation.R;

import java.util.List;

/**
 * Created by ndf25 on 2016/12/02.
 */

public class CustomAdapter extends ArrayAdapter<LocationDetail> {

    private LayoutInflater mLayoutInflater;

    // コンストラクタ
    public CustomAdapter(Context context, int textViewResourceId, List<LocationDetail> locationDetail){
        super(context, textViewResourceId, locationDetail);
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 一度Viewを表示したら使いまわす
    public View getView(int position, View convertView, ViewGroup parent) {
        LocationDetail item = (LocationDetail)getItem(position);

        if (null == convertView){
            convertView = mLayoutInflater.inflate(R.layout.custom_adapter, null);
        }

        TextView textView= (TextView)convertView.findViewById(R.id.text);
        textView.setText(item.getLocationName());

        return convertView;
    }
}
