package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.BuyAdapter;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;


public class MyBuy extends Activity {
    private ArrayList<Product> buyProducts = new ArrayList<Product>(); //定义一个动态数组
    private BuyAdapter buyAdapter;
    private ListView lv;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.my_buy_layout);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyBuy.this.finish();
            }
        });
        //得到数据源
        getDatas();
        //建立adapter
        buyAdapter = new BuyAdapter(getApplication(), buyProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.buy_lv);
        lv.setAdapter(buyAdapter);

    }

    /**
     * 得到数据源
     */
    private void getDatas() {
        buyProducts.add(new Product(0L, R.mipmap.memory, "八个笔记本儿内存条", 50.0));
        buyProducts.add(new Product(0L, R.mipmap.memory, "八个笔记本儿内存条", 50.0));
        buyProducts.add(new Product(0L, R.mipmap.memory, "八个笔记本儿内存条", 50.0));
        buyProducts.add(new Product(0L, R.mipmap.memory, "八个笔记本儿内存条", 50.0));
        buyProducts.add(new Product(0L, R.mipmap.memory, "八个笔记本儿内存条", 50.0));
    }
}
