package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.ChatActivity;
import com.daomaidaomai.islandtrading.easeui.EaseConstant;
import com.daomaidaomai.islandtrading.util.ImgLO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class GoodsDetail extends Activity {
    //以后通过网络请求获取用户Id
    String conversation = "小明";

    private LinearLayout ChatMessage;
    private LinearLayout Lin;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buy:
                    Intent i = new Intent(GoodsDetail.this, Order.class);
                    startActivity(i);
                    break;
                case R.id.back:
                    GoodsDetail.this.finish();
                    break;
                case R.id.chatmessage:
                    startActivity(new Intent(getApplication(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation));
                    break;
                default:
                    break;
            }
        }
    };
    private TextView Tv_Product_Time;
    private TextView Tv_Product_Price;
    private TextView Tv_Product_Describe;
    private ImageView Iv_Product_Image_Url;

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
        setContentView(R.layout.goods_detail);
        Lin = (LinearLayout) findViewById(R.id.buy);
        Back = (LinearLayout) findViewById(R.id.back);
        ChatMessage = (LinearLayout) findViewById(R.id.chatmessage);
        Tv_Product_Time = (TextView)findViewById(R.id.Tv_Product_Time);
        Tv_Product_Price = (TextView)findViewById(R.id.Tv_Product_Price);
        Tv_Product_Describe = (TextView)findViewById(R.id.Tv_Product_Describe);
        Iv_Product_Image_Url = (ImageView)findViewById(R.id.Iv_Product_Image_Url);



        Lin.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
        ChatMessage.setOnClickListener(mylistener);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //获取从上一个页面带来的商品id
        Intent i = getIntent();
        long pid = i.getLongExtra("pid",0);
        Toast.makeText(getApplicationContext(),i.getLongExtra("pid",0)+"",Toast.LENGTH_SHORT).show();
        RequestParams params = new RequestParams();
        JSONObject params_json = new JSONObject();
        try {
            params_json.put("Product_Id",pid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("pId",params_json);
        String str_url = "http://10.7.88.37:8080/IslandTrading/analysis/lookupprice";
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    System.out.println("----得到的：" + response.toString());
                    JSONObject jsonObject = response.getJSONObject("PRODUCT").getJSONObject("content");
                    System.out.println("-----得到的字符串" + jsonObject.toString() + "     \n" + response.toString());
                    Tv_Product_Time.setText(jsonObject.getString("Product_Time"));
                    Tv_Product_Price.setText(jsonObject.getDouble("Product_Price") + "");
                    Tv_Product_Describe.setText(jsonObject.getString("Product_Describe"));
//                    ImgLO.initImageLoader(getApplicationContext());
                    ImageLoader.getInstance().displayImage(jsonObject.getString("Product_Image_Url"),Iv_Product_Image_Url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
