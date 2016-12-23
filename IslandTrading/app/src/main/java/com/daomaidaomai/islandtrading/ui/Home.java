package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.HomeAdapter;
import com.daomaidaomai.islandtrading.controller.ClassifyAllActivity;
import com.daomaidaomai.islandtrading.defineview.MyImgScroll;
import com.daomaidaomai.islandtrading.entity.Product;
import com.daomaidaomai.islandtrading.util.ImgLO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Home extends Activity {
    MyImgScroll myPager; // 图片容器
    LinearLayout ovalLayout; // 圆点容器
    private List<View> listViews = new ArrayList<View>(); // 图片组
    private TextView title; //用于存放获取的视图控件

    private List<String> strs = new ArrayList<String>();//存放图片的标题

    private ImageView Classfy;
    private ImageView Home;
    private LinearLayout Chat;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;


    //定义动态数组
    private ArrayList<Product> listViewProducts = new ArrayList<Product>();
    //定义adapter
    private HomeAdapter homeAdapter;
    //用于存放获取的视图控件
    private ListView lv;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chat: {
                    Intent i = new Intent(Home.this, ConversationList.class);
                    startActivity(i);
                    break;
                }
                case R.id.map: {
                    Intent i = new Intent(Home.this,  Map.class);
                    startActivity(i);
                    break;
                }
                case R.id.sell: {
                    Intent i = new Intent(Home.this, Sell.class);
                    startActivity(i);
                    break;
                }
                case R.id.myself: {
                    Intent i = new Intent(Home.this, Myself.class);
                    startActivity(i);
                    break;
                }
                case R.id.classfy: {
                    Intent i = new Intent(Home.this, ClassifyAllActivity.class);
                    startActivity(i);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_home_page);


        lv = (ListView) findViewById(R.id.home_lv);
        // ListView头部View
        View view = LayoutInflater.from(this).inflate(R.layout.viewpager, null);
        //获取视图控件
        myPager = (MyImgScroll) view.findViewById(R.id.myvp);
        ovalLayout = (LinearLayout) view.findViewById(R.id.vb);
        title = (TextView) view.findViewById(R.id.title);
//        //初始化图片
//        InitViewPager();


        //创建网络访问的类的对象
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://182.61.37.142/IslandTrading/analysis/request_acts";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //获取的是外层的属性，外层的属性默认放在response中
                        JSONObject a = response.getJSONObject(i);
                        //获取的是a的属性good
                        JSONObject goods = a.getJSONObject("good");
                        //获取的是good的属性content
                        JSONObject content = goods.getJSONObject("content");
                        //得到content下的属性对应的value值
                        String output = content.getString("Activity_Name");
                        String imageUrl = content.getString("Activity_Img");
                        //创建ImageView控件
                        ImageView imageView = new ImageView(Home.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        //初始化ImageLoader
                        ImgLO.initImageLoader(Home.this);
                        // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件
                        ImageLoader.getInstance().displayImage(imageUrl, imageView);
                        strs.add(output);
                        listViews.add(imageView);
                        //必须每次进来清除布局里的所有小圆点，不然每一次循环会在上一次的基础上增加圆点
                        ovalLayout.removeAllViews();
                        //开始滚动
                        myPager.start(Home.this, listViews, 5000, title, strs, ovalLayout,
                                R.layout.ad_scroll_dot_item, R.id.ad_item_v,
                                R.drawable.dot_focused, R.drawable.dot_normal);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


//        //开始滚动
//        myPager.start(this, listViews, 4000, title, titles, ovalLayout,
//                R.layout.ad_scroll_dot_item, R.id.ad_item_v,
//                R.drawable.dot_focused, R.drawable.dot_normal);
        // 把ViewPager做成ListView的Header,注意:addHeaderView一定要在setAdapter前调用
        lv.addHeaderView(view);

        //得到数据源
        getListData();
        //创建adapter
        homeAdapter = new HomeAdapter(getApplicationContext(), listViewProducts);
        //为ListView绑定adapter
        lv.setAdapter(homeAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(com.daomaidaomai.islandtrading.ui.Home.this, GoodsDetail.class);
                intent.putExtra("pid",listViewProducts.get(i-1).getId());
                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"点击了" + i + "项  " + l, Toast.LENGTH_SHORT).show();
            }
        });


        Chat = (LinearLayout) findViewById(R.id.chat);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);
        Classfy = (ImageView) findViewById(R.id.classfy);
        Home = (ImageView) findViewById(R.id.home);

        Chat.setOnClickListener(mylistener);
        Map.setOnClickListener(mylistener);
        Sell.setOnClickListener(mylistener);
        Myself.setOnClickListener(mylistener);
        Classfy.setOnClickListener(mylistener);
        Home.setOnClickListener(mylistener);
    }


    private void getListData() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://182.61.37.142/IslandTrading/analysis/getTop";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject item = response.getJSONObject(i);
                        int id = item.getInt("Product_Id");
                        String imageUrl = item.getString("Product_Image_Url");
                        String name = item.getString("Product_Name");
                        String describe = item.getString("Product_Describe");
                        Double price = item.getDouble("Product_Price");
                        listViewProducts.add(new Product(id, imageUrl, name, price, describe));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    @Override
    protected void onRestart() {
        myPager.startTimer();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        myPager.stopTimer();
        super.onStop();
    }

    public void stop(View v) {
        myPager.stopTimer();
    }



}