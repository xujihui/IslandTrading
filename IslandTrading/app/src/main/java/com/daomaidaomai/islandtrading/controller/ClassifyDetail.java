package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.MyListAdapter;
import com.daomaidaomai.islandtrading.entity.ItemDetail;

import java.util.ArrayList;
import java.util.List;

public class ClassifyDetail extends Activity {
    private List<ItemDetail> ls = new ArrayList<ItemDetail>();
    private ListView lv ;
    private MyListAdapter myListAdapter;
    private void getDate(){
        ls.add(new ItemDetail(1,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味【一次吃个够】",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
        ls.add(new ItemDetail(2,"30包心逸原木抽纸巾 3层300张/包面巾纸【单包仅需0.9元】", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
        ls.add(new ItemDetail(3,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_detail);
        lv = (ListView)findViewById(R.id.lv);
        getDate();
        myListAdapter = new MyListAdapter(this,ls);
        lv.setAdapter(myListAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /* Intent intent = new Intent();
                intent.setClass(ClassifyDetail.this,ClassifyDetail.class);
                startActivity(intent);*/
            }
        });

    }
}
