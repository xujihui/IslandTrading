package com.example.administrator.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClassifyDetail extends Activity {
    private List<ItemDetail> ls = new ArrayList<ItemDetail>();
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
       // getDate();
        myListAdapter = new MyListAdapter(this,ls);
        lv.setAdapter(myListAdapter);
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://10.7.88.26:8080/supermarket/analysis/request_acts";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                for(int i=0;i<response.length();i++){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        JSONObject content=jsonObject.getJSONObject("content");
                        String output=content.getString("ACTIVITY_NAME");
                        String output1=content.getString("ACTIVITY_ORGANIZER");
                        String output2=content.getString("ACTIVITY_SITE");
                        String outpu3=content.getString("ACTIVITY_CONTENT");
                        String output4=content.getString("ACTIVITY_TIME");
                       Integer output5=content.getInt("ACTIVITY_ID");
                        ls.add(new ItemDetail(output5,output,outpu3,output2,output1,output4));
                        tv.setText(output);
                        Toast.makeText(getApplicationContext(),jsonObject.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });



    }

}
