package com.daomaidaomai.islandtrading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class SoldAdapter extends BaseAdapter {
    private Context context;//上下文环境，相当于传this
    //一个由数组实现的链表，相当于容器，用来放有多少个Item项，动态添加，长度不是固定的
    private ArrayList<Product> products = new ArrayList<Product>();

    public SoldAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {//判断是不是空控件
            //获取布局，将xml文件转换成view，即把xml文件当成view来使用
            view = LayoutInflater.from(context).inflate(R.layout.sold_listview_item_layout,null);
        }
        ImageView soldImage = (ImageView) view.findViewById(R.id.sold_image);
        soldImage.setImageResource(products.get(i).getmImage());
        TextView soldTitle = (TextView) view.findViewById(R.id.sold_title);
        soldTitle.setText(products.get(i).getmTitle());
        TextView soldPrice = (TextView) view.findViewById(R.id.sold_price);
        soldPrice.setText(products.get(i).getmPrice()+"");
        return view;
    }
}
