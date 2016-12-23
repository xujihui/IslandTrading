package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.MyListAdapter;
import com.daomaidaomai.islandtrading.adapter.PublishAdapter;
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
import java.util.List;

/*
* 2016-12-19
* 孙铖
* 使用了MyListAdapter
* */

public class MyPublish extends Activity {
//    private ArrayList<Product> publishProducts = new ArrayList<Product>(); //定义一个动态数组
//    private PublishAdapter publishAdapter;
    private ArrayList<ItemDetail> list = new ArrayList<>();
    private MyListAdapter adapter;
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
        setContentView(R.layout.my_publish_layout);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyPublish.this.finish();
            }
        });

        //得到数据源
//        getDatas();
        //建立adapter
//        publishAdapter = new PublishAdapter(getApplicationContext(), publishProducts);

        //获得ListView并为其绑定adapter
        lv = (ListView) findViewById(R.id.publish_lv);
//        lv.setAdapter(publishAdapter);


        //这里要获取登陆的账号id
        String str_url = "http://182.61.37.142/IslandTrading/analysis/myRelease?User_Id=\"800\""; //这里写死了
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++){
                    JSONObject content = new JSONObject();
                    try {
                        content = response.getJSONObject(i);
//                        Toast.makeText(getApplicationContext(),content.toString(),Toast.LENGTH_SHORT).show();
                        list.add(new ItemDetail(content.getInt("Product_Id"),content.getString("Product_Name"),content.getString("Product_Image_Url"),
                                null,content.getDouble("Product_Price")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }//for
                lv.setAdapter(new MyListAdapter(getApplicationContext(),list));
                lv.setOnItemClickListener(listener);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }


    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(MyPublish.this, GoodsDetail.class);
            i.putExtra("pid",id);
            startActivityForResult(i,1);
        }
    };

    /**
     * 得到数据源
     */
//    private void getDatas() {
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//        publishProducts.add(new Product(0L, R.mipmap.bag, "链条女包萌耳朵夹子包单肩", 40.0, "        挺可爱的单肩包。买回来就没有背过，全新的。对于我来说单肩背链子有点长，所以喜欢长一点的MM可以联系我哦！", "20天"));
//
//    }
}
