package com.example.administrator.json;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ClassifyDetail extends Activity {
    private List<ItemDetail> ls = new ArrayList<ItemDetail>();
    private DisplayImageOptions options;
    private ListView lv ;
    private MyListAdapter myListAdapter;
    private TextView tv;
//    private void getDate(){
//        ls.add(new ItemDetail(1,"自愿者","植树","师生活动中心","青协","2014-12-12"));
//        ls.add(new ItemDetail(2,"自愿者1","植树","师生活动中心","青协","2014-12-12"));
//        ls.add(new ItemDetail(3,"自愿者2","植树","师生活动中心","青协","2014-12-12"));
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        lv = (ListView)findViewById(R.id.lv);
        tv=(TextView)findViewById(R.id.tv);
        init();
       // getDate();
        myListAdapter = new MyListAdapter(this,ls);
        lv.setAdapter(myListAdapter);
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://10.7.88.26:8080/IslandTrading/analysis/request_acts";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                for(int i=0;i<response.length();i++){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        JSONObject good=jsonObject.getJSONObject("good");
                       JSONObject content=good.getJSONObject("content");
                        String output=content.getString("Activity_Name");
                        String output1=content.getString("Activity_Organizer");
                        String output2=content.getString("Activity_Site");
                        String outpu3=content.getString("Activity_Content");
                        String output4=content.getString("Activity_Time");
                        Integer output5=content.getInt("Activity_Id");
                        ls.add(new ItemDetail(output5,output,outpu3,output2,output1,output4,"http+"));
                       // tv.setText(output);
                       // Toast.makeText(getApplicationContext(),jsonObject.toString(),Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });



    }

    private void init() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.odetailone) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.odetailone)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.odetailone)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)  // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)  // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))// 设置成圆角图片
                .build();// 创建配置过得DisplayImageOption对象

    }

}
