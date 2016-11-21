package com.daomaidaomai.islandtrading.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.entity.Addres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20.
 */

public class AddAdapter extends BaseAdapter {
    private Context context;
    private List<Addres> laddres=new ArrayList<>();

    public AddAdapter(Context context, List<Addres> laddres) {
        this.context = context;
        this.laddres = laddres;
    }

    @Override
    public int getCount() {
        return laddres.size();
    }

    @Override
    public Object getItem(int position) {
        return laddres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return laddres.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null==convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_choice, null);
        }
        TextView tvName=(TextView)convertView.findViewById(R.id.lv_name2);
        tvName.setText(laddres.get(position).getsName());
        TextView tvNum=(TextView)convertView.findViewById(R.id.tv_number2);
        tvNum.setText(laddres.get(position).getsNumber());
        TextView tvStyle=(TextView)convertView.findViewById(R.id.style2);
        tvStyle.setText(laddres.get(position).getsStyle());
        TextView tvAddres=(TextView)convertView.findViewById(R.id.list_chioce_addres);
        tvAddres.setText(laddres.get(position).getsAddres());
        return convertView;
        }
    }

