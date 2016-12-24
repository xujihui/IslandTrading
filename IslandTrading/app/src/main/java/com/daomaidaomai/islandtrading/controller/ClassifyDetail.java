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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.MyListAdapter;
import com.daomaidaomai.islandtrading.entity.ItemDetail;
import com.daomaidaomai.islandtrading.ui.GoodsDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassifyDetail extends Activity {
    private List<ItemDetail> ls = new ArrayList<ItemDetail>();
    private ListView lv ;
    private MyListAdapter myListAdapter;
    private LinearLayout Back;
    private DisplayImageOptions options ;
//    private void getDate(){
//        ls.add(new ItemDetail2(1,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
//        ls.add(new ItemDetail2(2,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
//        ls.add(new ItemDetail2(3,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
//        ls.add(new ItemDetail(4,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
//        ls.add(new ItemDetail(5,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
//        ls.add(new ItemDetail(6,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
//        ls.add(new ItemDetail(7,"德芙巧克力 黑巧克力/牛奶/榛仁 三种口味",R.mipmap.odetailone,"德芙巧克力500克散装",59.90));
//        ls.add(new ItemDetail(8,"30包心逸原木抽纸巾 3层300张/包面巾纸", R.mipmap.odetailtwo,"精选优质原生木桨，温柔呵护肌肤，温水依然柔韧，孕婴都适用。",27.90));
//        ls.add(new ItemDetail(9,"三只松鼠 能量果仁182g",R.mipmap.odetailthree,"每天一包坚果，健康欢乐的生活，9种坚果搭配，颜值与口感双百分。",33.90));
//    }
private TextView Classify;
    private String type;
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
        Classify = (TextView)findViewById(R.id.TvType);
        Intent i = getIntent();

        type = i.getStringExtra("ClassifyName");
        Classify.setText(type);

        lv = (ListView)findViewById(R.id.lv);

       getHttp();//网络请求
        inits();
        myListAdapter = new MyListAdapter(ClassifyDetail.this,ls);
        lv.setAdapter(myListAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /* Intent intent = new Intent();
                intent.setClass(ClassifyDetail.this,ClassifyDetail.class);
                startActivity(intent);*/
                Intent intent = new Intent(ClassifyDetail.this, GoodsDetail.class);
                intent.putExtra("pid",ls.get(i).getId());
                startActivity(intent);
            }
        });


    }

    private void getHttp() {
        String url = "http://182.61.37.142/IslandTrading/analysis/type_collection";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params= new RequestParams();

       // params.add("pType","{pType:数码3CC}");

        params.add("pType","{pType:"+type+"}");

        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("--"+response.toString());
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        JSONObject good = jsonObject.getJSONObject("good");
                        JSONObject content = good.getJSONObject("content");
                        int id = content.getInt("Product_Id");
                        String name=content.getString("Product_Name");
                        String describe=content.getString("Product_Describe");
                        double price=content.getDouble("Product_Price");
                       String picture=content.getString("Product_Image_Url");
                        ls.add(new ItemDetail(id,name,picture,describe,price));

                        //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getApplicationContext(),jsonObject.toString(),Toast.LENGTH_SHORT).show();
                        System.out.println("name-------" + name);
                        System.out.println("ls-------" + ls);
                        myListAdapter.notifyDataSetChanged();
                        myListAdapter.notifyDataSetInvalidated();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    private void inits() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.odetailone) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.odetailone)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.odetailone)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)  // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)  // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))// 设置成圆角图片
                .build();// 创建配置过得DisplayImageOption对象

    }
}
