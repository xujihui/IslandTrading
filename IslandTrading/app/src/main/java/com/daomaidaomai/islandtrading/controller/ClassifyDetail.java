package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
    private LinearLayout Back;
    private void getDate(){
        ls.add(new ItemDetail(1,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
        ls.add(new ItemDetail(2,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
        ls.add(new ItemDetail(3,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
        ls.add(new ItemDetail(4,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
        ls.add(new ItemDetail(5,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
        ls.add(new ItemDetail(6,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
        ls.add(new ItemDetail(7,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
        ls.add(new ItemDetail(8,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
        ls.add(new ItemDetail(9,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
    }
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
        setContentView(R.layout.classify_detail);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClassifyDetail.this.finish();
            }
        });
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
