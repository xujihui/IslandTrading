package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.BuyAdapter;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;


public class MyBuy extends Activity {
    private ArrayList<Product> buyProducts = new ArrayList<Product>(); //定义一个动态数组
    private BuyAdapter buyAdapter;
    private ListView lv;
    private ImageView buyBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_buy_layout);

        //得到数据源
        getDatas();
        //建立adapter
        buyAdapter = new BuyAdapter(getApplication(),buyProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.buy_lv);
        lv.setAdapter(buyAdapter);

    }

    /**
     * 得到数据源
     */
    private void getDatas() {
        buyProducts.add(new Product(0L,R.mipmap.memory,"八个笔记本儿内存条",50.0));
        buyProducts.add(new Product(0L,R.mipmap.memory,"八个笔记本儿内存条",50.0));
        buyProducts.add(new Product(0L,R.mipmap.memory,"八个笔记本儿内存条",50.0));
        buyProducts.add(new Product(0L,R.mipmap.memory,"八个笔记本儿内存条",50.0));
        buyProducts.add(new Product(0L,R.mipmap.memory,"八个笔记本儿内存条",50.0));
    }
}
