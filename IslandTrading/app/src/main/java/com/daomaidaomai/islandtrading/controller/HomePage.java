package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.HomeAdapter;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;

public class HomePage extends Activity {
    //定义动态数组
    private ArrayList<Product> listViewProducts = new ArrayList<Product>();
    //定义adapter
    private HomeAdapter homeAdapter;
    //用于存放获取的视图控件
    private ListView lv;


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
        setContentView(R.layout.activity_home_page);

        //得到数据源
        getListData();
        //创建adapter
        homeAdapter = new HomeAdapter(HomePage.this,listViewProducts);
        //得到视图控件
        lv = (ListView)findViewById(R.id.home_lv);
        //绑定adapter
        lv.setAdapter(homeAdapter);
    }

    /**
     *
     */
    private void getListData() {
        listViewProducts.add(new Product(0L,R.mipmap.threesquirrel,"三只松鼠 能量果仁182g",33.9,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。"));
        listViewProducts.add(new Product(0L,R.mipmap.chocolate,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味【一次吃个够】",59.9,"德芙巧克力500克散装"));
        listViewProducts.add(new Product(0L,R.mipmap.napkin,"30包心逸原木抽纸巾 3层300张/包面巾纸",27.9,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。"));
        listViewProducts.add(new Product(0L,R.mipmap.threesquirrel,"三只松鼠 能量果仁182g",33.9,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。"));
        listViewProducts.add(new Product(0L,R.mipmap.chocolate,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味【一次吃个够】",59.9,"德芙巧克力500克散装"));
        listViewProducts.add(new Product(0L,R.mipmap.napkin,"30包心逸原木抽纸巾 3层300张/包面巾纸",27.9,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。"));
    }
}
