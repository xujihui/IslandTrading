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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

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
    private List<View> listViews; // 图片组
    private TextView title; //用于存放获取的视图控件
//    //存放图片的标题
//    private String[] titles = new String[]{
//            "BEATS SOLO2 WIRELESS",
//            "国际大牌 低至一折",
//            "极有家推荐 品质生活 便宜不贵",
//            "施华洛世奇 秋冬新款",
//    };

    private List<String> strs = new ArrayList<String>();

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
                    Intent i = new Intent(Home.this, Map.class);
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

    //Get方法
    public String get(String urlPath) {
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setDoInput(true);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);

                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                connection = null;
            }
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }


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
        //初始化图片
        InitViewPager();


        //创建网络访问的类的对象
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://10.7.92.57:8080/IslandTrading/analysis/request_acts";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject a = response.getJSONObject(i);
                        JSONObject goods = a.getJSONObject("good");
                        JSONObject content = goods.getJSONObject("content");
                        String output = content.getString("Activity_Name");
                        strs.add(output);
                        //开始滚动
                        myPager.start(Home.this, listViews, 4000, title, strs, ovalLayout,
                                R.layout.ad_scroll_dot_item, R.id.ad_item_v,
                                R.drawable.dot_focused, R.drawable.dot_normal);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


//        //开始滚动
//        myPager.start(this, listViews, 4000, title, strs, ovalLayout,
//                R.layout.ad_scroll_dot_item, R.id.ad_item_v,
//                R.drawable.dot_focused, R.drawable.dot_normal);
        // 把ViewPager做成ListView的Header,注意:addHeaderView一定要在setAdapter前调用
        lv.addHeaderView(view);

        //得到数据源
        getListData();
        //创建adapter
        homeAdapter = new HomeAdapter(Home.this, listViewProducts);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplication(), GoodsDetail.class));
            }
        });
        //为ListView绑定adapter
        lv.setAdapter(homeAdapter);


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
        listViewProducts.add(new Product(0L, R.mipmap.swatch, "Swatch手表2016七夕情人节限定", 459, "Swatch系列Special edition特别款系列"));
        listViewProducts.add(new Product(0L, R.mipmap.schaebens, " Schaebens雪本诗面膜 补水保湿美白提拉紧致祛痘", 9, "亲表姐德国代购，保证正品"));
        listViewProducts.add(new Product(0L, R.mipmap.coach, "coach加拿大代购 长款男钱包", 558, "PVC/牛皮 长20CM*宽10CM*厚2.5CM"));
        listViewProducts.add(new Product(0L, R.mipmap.ysl, "YSL 亮泽滋润唇膏", 298, "圣罗兰方管口红迷魅唇膏滋润保湿限量星辰"));
        listViewProducts.add(new Product(0L, R.mipmap.zuixie, "一字鲜醉蟹", 138, "一字鲜醉蟹醉大闸蟹红膏全母蟹2.4-2两熟醉蟹共8只花雕熟醉蟹"));
        listViewProducts.add(new Product(0L, R.mipmap.teenmix, "Teenmix/天美意 长靴", 1239, "Teenmix/天美意冬季专柜同款打蜡牛皮女过膝长靴6D480DG5"));
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

    /**
     * 初始化图片
     */
    private void InitViewPager() {
        //显示的图片
        listViews = new ArrayList<View>();
        int[] imageResId = new int[]{R.mipmap.viewpager1, R.mipmap.viewpager2};// R.mipmap.viewpager3, R.mipmap.viewpager4
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResId[i]);
            listViews.add(imageView);
        }
    }

}