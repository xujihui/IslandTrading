package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.HomeAdapter;
import com.daomaidaomai.islandtrading.controller.ClassifyAllActivity;
import com.daomaidaomai.islandtrading.controller.HomePage;
import com.daomaidaomai.islandtrading.entity.Product;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Home extends Activity {
    private ImageView Img;
    private LinearLayout Chat;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;

    //定义动态数组
    private ArrayList<Product> listViewProducts = new ArrayList<Product>();
    //定义adapter
    private HomeAdapter homeAdapter;
    //用于存放获取的视图控件
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //得到数据源
        getListData();
        //创建adapter
        homeAdapter = new HomeAdapter(Home.this, listViewProducts);
        //得到视图控件
        lv = (ListView) findViewById(R.id.home_lv);
        //绑定adapter
        lv.setAdapter(homeAdapter);


        Chat = (LinearLayout) findViewById(R.id.chat);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);

        Img = (ImageView) findViewById(R.id.classfy);
        Img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ClassifyAllActivity.class);
                startActivity(i);

            }
        });


        Chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Chat.class);
                startActivity(i);

            }
        });
        Map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Map.class);
                startActivity(i);

            }
        });
        Sell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Sell.class);
                startActivity(i);

            }
        });

        Myself.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Myself.class);
                startActivity(i);

            }
        });

    }

    private void getListData() {
        listViewProducts.add(new Product(0L, R.mipmap.threesquirrel, "三只松鼠 能量果仁182g", 33.9, "每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。"));
        listViewProducts.add(new Product(0L, R.mipmap.chocolate, "德芙巧克力 黑巧克力/牛奶/榛仁 三种口味【一次吃个够】", 59.9, "德芙巧克力500克散装"));
        listViewProducts.add(new Product(0L, R.mipmap.napkin, "30包心逸原木抽纸巾 3层300张/包面巾纸", 27.9, "精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。"));
        listViewProducts.add(new Product(0L, R.mipmap.threesquirrel, "三只松鼠 能量果仁182g", 33.9, "每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。"));
        listViewProducts.add(new Product(0L, R.mipmap.chocolate, "德芙巧克力 黑巧克力/牛奶/榛仁 三种口味【一次吃个够】", 59.9, "德芙巧克力500克散装"));
        listViewProducts.add(new Product(0L, R.mipmap.napkin, "30包心逸原木抽纸巾 3层300张/包面巾纸", 27.9, "精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。"));
    }
}