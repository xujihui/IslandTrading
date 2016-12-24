package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.util.ImgLO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class Pay extends Activity {
    private Button Btn;
    private Button Cancel;
    private LinearLayout Back;
    private ImageView img;
    private TextView title;
    private TextView decrible;
    private TextView price;
    int oid; //商品id
    String user_name = "15232157618"; //以后通过网络请求获取用户名
    Time time = new Time();

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pay: {
                    //获取手机当前时间
                    time.setToNow();
                    RequestParams params = new RequestParams();
                    JSONObject params_json = new JSONObject();
                    try {
                        params_json.put("User_Name",user_name);
                        params_json.put("Product_Id",oid);
                        params_json.put("Order_Id",time.hour+""+time.minute);
                        params_json.put("ORDER_TIME",time.year+"-"+(time.month+1)+"-"+time.monthDay+" "+time.hour+":"+time.minute+":"+time.second);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    params.put("order",params_json);
                    String url = " http://182.61.37.142/IslandTrading/analysis/oreder";
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(url,params,new JsonHttpResponseHandler(){
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Intent i = new Intent(Pay.this, Facedeal.class);
                            startActivity(i);
                            Pay.this.finish();
                            System.out.print(responseString);
                        }
                    });

//                    Intent i = new Intent(Pay.this, Facedeal.class);
//                    startActivity(i);
//                    Pay.this.finish();
                    break;
                }
                case R.id.cancel: {
                    Intent i = new Intent(Pay.this, Canceldeal.class);
                    startActivity(i);
                    Pay.this.finish();
                    break;
                }
                case R.id.back:
                    Pay.this.finish();
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
        setContentView(R.layout.activity_pay);
        //得到视图控件
        getViews();
        //设置监听器
        Cancel.setOnClickListener(mylistener);
        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);

        Intent intent = getIntent();
        oid = intent.getIntExtra("oid",0);
//        Toast.makeText(Pay.this,oid+"",Toast.LENGTH_SHORT).show();
        RequestParams params = new RequestParams();
        JSONObject params_json = new JSONObject();
        try {
            params_json.put("Product_Id",oid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("pId",params_json);
        String url = "http://182.61.37.142/IslandTrading/analysis/lookupprice";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject content = response.getJSONObject("PRODUCT").getJSONObject("content");
                    title.setText(content.getString("Product_Name"));
                    decrible.setText(content.getString("Product_Describe"));
                    price.setText(content.getDouble("Product_Price") + "");
                    ImgLO.initImageLoader(getApplicationContext());
                    ImageLoader.getInstance().displayImage(content.getString("Product_Image_Url"),img);
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
        Btn = (Button) findViewById(R.id.pay);
        Cancel = (Button) findViewById(R.id.cancel);
        Back = (LinearLayout) findViewById(R.id.back);
        img = (ImageView) findViewById(R.id.PeImg);
        title = (TextView) findViewById(R.id.PeTil);
        decrible = (TextView) findViewById(R.id.PeDec);
        price = (TextView) findViewById(R.id.PePri);
    }
}
