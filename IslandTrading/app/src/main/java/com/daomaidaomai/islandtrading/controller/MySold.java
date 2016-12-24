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


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.MyListAdapter;
import com.daomaidaomai.islandtrading.adapter.SoldAdapter;
import com.daomaidaomai.islandtrading.entity.ItemDetail;
import com.daomaidaomai.islandtrading.entity.Product;
import com.daomaidaomai.islandtrading.ui.GoodsDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MySold extends Activity {
    //    private ArrayList<Product> soldProducts = new ArrayList<Product>(); //定义一个动态数组
//    private SoldAdapter soldAdapter;
    private MyListAdapter adapter;
    private ArrayList<ItemDetail> list = new ArrayList<>();
    private ListView lv;
    private LinearLayout Back;

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
        setContentView(R.layout.my_sold_layout);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MySold.this.finish();
            }
        });


        //得到数据源
//        getDatas();
        //建立adapter
//        soldAdapter = new SoldAdapter(getApplication(), soldProducts);
        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.sold_lv);
//        lv.setAdapter(soldAdapter);

        RequestParams params = new RequestParams();
        int User_Id = 800;      //需要获取登陆的User_Id
        params.put("User_Id",User_Id);
        String str_url = "http://182.61.37.142/IslandTrading/analysis/mySell";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    JSONObject content = new JSONObject();
                    try {
                        content = response.getJSONObject(i);
//                        Toast.makeText(getApplicationContext(),content.toString(),Toast.LENGTH_SHORT).show();
                        list.add(new ItemDetail(content.getInt("Product_Id"), content.getString("Product_Name"), content.getString("Product_Image_Url"),
                                null, content.getDouble("Product_Price")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }//for
                lv.setAdapter(new MyListAdapter(getApplicationContext(), list));
                lv.setOnItemClickListener(listener);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        /**
         * 得到数据源
         */
//    private void getDatas() {
//        soldProducts.add(new Product(0L, R.mipmap.earphone, "美讯Masentek D8蓝牙耳机", 60.0));
//        soldProducts.add(new Product(0L, R.mipmap.earphone, "美讯Masentek D8蓝牙耳机", 60.0));
//        soldProducts.add(new Product(0L, R.mipmap.earphone, "美讯Masentek D8蓝牙耳机", 60.0));
//        soldProducts.add(new Product(0L, R.mipmap.earphone, "美讯Masentek D8蓝牙耳机", 60.0));
//        soldProducts.add(new Product(0L, R.mipmap.earphone, "美讯Masentek D8蓝牙耳机", 60.0));
//    }
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(MySold.this, GoodsDetail.class);
            i.putExtra("pid",id);
            startActivityForResult(i,1);
        }
    };

}
