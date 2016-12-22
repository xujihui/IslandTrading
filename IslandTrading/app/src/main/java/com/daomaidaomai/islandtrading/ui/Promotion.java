package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Promotion extends Activity {
    private LinearLayout Back;
    //用于存放获得的活动名称视图控件
    private TextView title;
    //用于存放获得的活动组织视图控件
    private TextView name;
    //用于存放获得的活动时间视图控件
    private TextView time;
    //用于存放获得的活动内容视图控件
    private TextView mainText;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.back:
                    Promotion.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

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
        setContentView(R.layout.activity_promotion);
        //得到视图控件
        getViews();

        Back.setOnClickListener(mylistener);

        //获取从上一个页面携带过来的下标
        Intent intent = getIntent();
        final int index = intent.getIntExtra("index",0);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://182.61.37.142/IslandTrading/analysis/request_acts";
        client.get(this,url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject a = response.getJSONObject(index);
                    JSONObject goods = a.getJSONObject("good");
                    JSONObject content = goods.getJSONObject("content");
                    String atitle = content.getString("Activity_Name");//活动名称
                    String aname = content.getString("Activity_Organizer"); //活动组织
                    String atime = content.getString("Activity_Time"); //活动时间
                    String amainText = content.getString("Activity_Content"); //活动内容
                    //把获取到的值放到指定位置
                    title.setText(atitle);
                    name.setText(aname);
                    time.setText(atime);
                    mainText.setText(amainText);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 得到视图控件
     */
    void getViews(){
        Back = (LinearLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        name = (TextView) findViewById(R.id.name);
        time = (TextView) findViewById(R.id.time);
        mainText = (TextView) findViewById(R.id.maintext);
    }
}
