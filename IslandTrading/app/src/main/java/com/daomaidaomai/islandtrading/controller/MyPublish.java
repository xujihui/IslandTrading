package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.PublishAdapter;
import com.daomaidaomai.islandtrading.entity.Product;


import java.util.ArrayList;



public class MyPublish extends Activity {
    private ArrayList<Product> publishProducts = new ArrayList<Product>(); //定义一个动态数组
    private PublishAdapter publishAdapter;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_publish_layout);

        //得到数据源
        getDatas();
        //建立adapter
        publishAdapter = new PublishAdapter(getApplication(),publishProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.publish_lv);
        lv.setAdapter(publishAdapter);

    }

    /**
     * 得到数据源
     */
    private void getDatas() {
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));
        publishProducts.add(new Product(0L,R.mipmap.bag,"链条女包萌耳朵夹子包单肩",40.0,"        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！","20天"));

    }
}
