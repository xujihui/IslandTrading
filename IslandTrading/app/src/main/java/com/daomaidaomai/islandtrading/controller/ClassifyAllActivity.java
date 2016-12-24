package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.GridAdapter;
import com.daomaidaomai.islandtrading.entity.Item;
import com.daomaidaomai.islandtrading.ui.Aboutus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/19.
 */
public class ClassifyAllActivity extends Activity {
    private List<Item> ls = new ArrayList<>();
    private GridView gv;
    private GridAdapter girdAdapter;
    private LinearLayout Back;

    private void getDate() {
        ls.add(new Item(1, "数码", R.mipmap.classfy_shuma));
        ls.add(new Item(2, "男装", R.mipmap.classfy_nanzhuang));
        ls.add(new Item(3, "女装 ", R.mipmap.classfy_nvzhuang));
        ls.add(new Item(4, "食品", R.mipmap.classfy_food));
        ls.add(new Item(5, "鞋靴", R.mipmap.classfy_xiexue));
        ls.add(new Item(6, "箱包", R.mipmap.classfy_xiangbao));
        ls.add(new Item(7, "运动", R.mipmap.classfy_yundong));
        ls.add(new Item(8, "户外", R.mipmap.classfy_huwai));
        ls.add(new Item(9, "图书", R.mipmap.classfy_book));
        ls.add(new Item(10, "音像", R.mipmap.classfy_luxiang));
        ls.add(new Item(11, "电子书", R.mipmap.classfy_dianzishu));
        ls.add(new Item(12, "奢侈品", R.mipmap.classfy_shechipin));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        setContentView(R.layout.classify_all);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClassifyAllActivity.this.finish();
            }
        });

        gv = (GridView) findViewById(R.id.GvAllGoods);
        getDate();

        girdAdapter = new GridAdapter(ClassifyAllActivity.this, ls);//Utils.ls
        gv.setAdapter(girdAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ClassifyDetail.class);
                switch (position){
                    case 0:
                        intent.putExtra("ClassifyName","数码");
                        break;
                    case 1:
                        intent.putExtra("ClassifyName","男装");
                        break;
                    case 2:
                        intent.putExtra("ClassifyName","女装");
                        break;
                    case 3:
                        intent.putExtra("ClassifyName","食品");
                        break;
                    case 4:
                        intent.putExtra("ClassifyName","鞋靴");
                        break;
                    case 5:
                        intent.putExtra("ClassifyName","箱包");
                        break;
                    case 6:
                        intent.putExtra("ClassifyName","运动");
                        break;
                    case 7:
                        intent.putExtra("ClassifyName","户外");
                        break;
                    case 8:
                        intent.putExtra("ClassifyName","图书");
                        break;
                    case 9:
                        intent.putExtra("ClassifyName","音像");
                        break;
                    case 10:
                        intent.putExtra("ClassifyName","电子书");
                        break;
                    case 11:
                        intent.putExtra("ClassifyName","奢侈品");
                        break;
                }
                startActivityForResult(intent,1);
            }
        });
    }
}
