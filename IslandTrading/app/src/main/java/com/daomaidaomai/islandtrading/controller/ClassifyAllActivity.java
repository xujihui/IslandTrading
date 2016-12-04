package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.GridAdapter;
import com.daomaidaomai.islandtrading.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/19.
 */
public class ClassifyAllActivity extends Activity {
    private List<Item> ls = new ArrayList<>();
    private GridView gv ;
    private GridAdapter girdAdapter;
    private LinearLayout Back;

    private void getDate(){
        ls.add(new Item(1,"女装",R.mipmap.women));
        ls.add(new Item(2,"男装", R.mipmap.man));
        ls.add(new Item(3,"自行车 ",R.mipmap.bike));
        ls.add(new Item(4,"手机",R.mipmap.phone));
        ls.add(new Item(5,"电脑",R.mipmap.pc));
        ls.add(new Item(6,"3C数码",R.mipmap.game));
        ls.add(new Item(7,"鞋包",R.mipmap.shoes));
        ls.add(new Item(8,"化妆品",R.mipmap.cosmetics));
        ls.add(new Item(9,"文具",R.mipmap.stationery));
        ls.add(new Item(10,"图书",R.mipmap.book));
        ls.add(new Item(11,"技能服务",R.mipmap.skills));
        ls.add(new Item(12,"其他",R.mipmap.other));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_all);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClassifyAllActivity.this.finish();
            }
        });

        gv = (GridView)findViewById(R.id.GvAllGoods);
        getDate();

        girdAdapter = new GridAdapter(ClassifyAllActivity.this, ls);//Utils.ls
        gv.setAdapter(girdAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ClassifyAllActivity.this,ClassifyDetail.class);
                startActivity(intent);
            }
        });
    }
}
