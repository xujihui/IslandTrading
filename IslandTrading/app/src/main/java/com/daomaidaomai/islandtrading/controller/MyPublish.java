package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.PublishAdapter;
import com.daomaidaomai.islandtrading.entity.Product;


import java.util.ArrayList;


public class MyPublish extends Activity {
    private ArrayList<Product> publishProducts = new ArrayList<Product>(); //定义一个动态数组
    private PublishAdapter publishAdapter;
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
        setContentView(R.layout.my_publish_layout);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyPublish.this.finish();
            }
        });

        //得到数据源
        getDatas();
        //建立adapter
        publishAdapter = new PublishAdapter(getApplication(), publishProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.publish_lv);
        lv.setAdapter(publishAdapter);

    }

    /**
     * 得到数据源
     */
    private void getDatas() {
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));

    }
}
