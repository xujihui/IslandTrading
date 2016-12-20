package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.app.ListActivity;
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
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.MyListAdapter;
import com.daomaidaomai.islandtrading.controller.MyBuy;
import com.daomaidaomai.islandtrading.entity.ItemDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyCollection extends Activity {
    private LinearLayout Back;
    private ArrayList<ItemDetail> itemDetailArrayList = new ArrayList<>();
//    private ListView list_mycol;
    private ListView lv;

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
        setContentView(R.layout.my_collection);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyCollection.this.finish();
            }
        });

        RequestParams params = new RequestParams();
        JSONObject param_json = new JSONObject();
        int User_Id = 2014011905;       //这里要获取登陆的账号id
        try {
            param_json.put("User_Id",User_Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("User_Id",param_json);
        String str_url = "http://10.7.88.37:8080/IslandTrading/analysis/request_col?User_Id={User_Id:2014011905}";      //这里写死了
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject content = response.getJSONObject(i).getJSONObject("good").getJSONObject("content");
                        itemDetailArrayList.add(new ItemDetail(content.getInt("Product_Id"),content.getString("Product_Name"),
                                content.getString("Product_Image_Url"),content.getString("Product_Describe"),content.getDouble("Product_Price")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),"JSONObject",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Toast.makeText(getApplicationContext(),"String",Toast.LENGTH_SHORT).show();
            }
        });
        lv = (ListView)findViewById(R.id.lv);
        MyListAdapter adapter = new MyListAdapter(getApplicationContext(),itemDetailArrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listener);
//        list_mycol = (ListView)findViewById(android.R.id.list);
//        list_mycol.setOnItemClickListener(listener);
    }


    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(MyCollection.this, GoodsDetail.class);
            i.putExtra("pid",id);
            startActivityForResult(i,1);
        }
    };

}
