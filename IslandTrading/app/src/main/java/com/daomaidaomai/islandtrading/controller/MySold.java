package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.SoldAdapter;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;


public class MySold extends Activity {
    private ArrayList<Product> soldProducts = new ArrayList<Product>(); //定义一个动态数组
    private SoldAdapter soldAdapter;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_sold_layout);

        //得到数据源
        getDatas();
        //建立adapter
        soldAdapter = new SoldAdapter(getApplication(),soldProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.sold_lv);
        lv.setAdapter(soldAdapter);

    }

    /**
     * 得到数据源
     */
    private void getDatas() {
        soldProducts.add(new Product(0L,R.mipmap.earphone,"美讯Masentek D8蓝牙耳机",60.0));
        soldProducts.add(new Product(0L,R.mipmap.earphone,"美讯Masentek D8蓝牙耳机",60.0));
        soldProducts.add(new Product(0L,R.mipmap.earphone,"美讯Masentek D8蓝牙耳机",60.0));
        soldProducts.add(new Product(0L,R.mipmap.earphone,"美讯Masentek D8蓝牙耳机",60.0));
        soldProducts.add(new Product(0L,R.mipmap.earphone,"美讯Masentek D8蓝牙耳机",60.0));
    }
}
