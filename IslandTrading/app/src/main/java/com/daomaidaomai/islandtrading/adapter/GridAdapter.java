package com.daomaidaomai.islandtrading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/19.
 */
public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<Item> litems = new ArrayList<>();//待加载的列表项item布局资源

    public GridAdapter(Context c, List<Item> ls) {
        context = c;
        litems = ls;
    }

    @Override
    public int getCount() {//adapter提供多少条数据，ArrayList有多少条及有多少条
        return litems.size();
    }

    @Override
    public Object getItem(int i) {//第几项
        return litems.get(i);
    }

    @Override
    public long getItemId(int i) {//唯一标识某一项
        return litems.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_goods, null);

        TextView TvItemName = (TextView) view.findViewById(R.id.TvItemTitle);
        TvItemName.setText(litems.get(i).getmName());
        ImageView TvItemImage = (ImageView) view.findViewById(R.id.IvItemImg);
        TvItemImage.setImageResource(litems.get(i).getmPicture());
        return view;
    }
}
