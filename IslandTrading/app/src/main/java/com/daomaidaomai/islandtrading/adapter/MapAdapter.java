package com.daomaidaomai.islandtrading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.entity.MapST;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MapAdapter extends BaseAdapter {
    private Context context;
    private List<MapST> markInfoList =new ArrayList<>();

    public MapAdapter(Context context, List<MapST> markInfoList) {
        this.context = context;
        this.markInfoList = markInfoList;
    }

    @Override
    public int getCount() {
        return markInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return markInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return markInfoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.map_goodsdetails, null);
        ImageView mapImg = (ImageView) convertView.findViewById(R.id.map_img);
        mapImg.setImageResource(markInfoList.get(position).getImageId());
        TextView mapTitle=(TextView)convertView.findViewById(R.id.map_title);
        mapTitle.setText(markInfoList.get(position).getmName());
        TextView mapContent=(TextView)convertView.findViewById(R.id.map_content);
        mapContent.setText(markInfoList.get(position).getmContent());
        return convertView;
    }
}
