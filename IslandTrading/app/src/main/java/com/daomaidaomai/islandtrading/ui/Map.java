package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;

import com.baidu.mapapi.model.LatLng;
import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.entity.MapST;
import com.daomaidaomai.islandtrading.util.ImgLO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.daomaidaomai.islandtrading.ui.Login.TAG;

public class Map extends Activity implements BaiduMap.OnMapClickListener { /* 地图控件*/

    private ImageView Refresh;
    private ImageView Back;
    private ImageView good;
    private LinearLayout mapgood;
    private TextureMapView mMapView = null; /* 地图实例*/
    private BaiduMap mBaiduMap; /* 定位的客户端*/
    private LocationClient mLocationClient; /* 定位的监听器*/
    public MyLocationListener mMyLocationListener; /* 当前定位的模式*/
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    /* 是否是第一次定位*/
    private volatile boolean isFristLocation = true; /* 最新一次的经纬度*/
    private double mCurrentLantitude;
    private double mCurrentLongitude; /* 地图定位的模式*/
    private int mCurrentStyle = 0;
    private List<MapST> markInfoList;
    private LinearLayout markLayout;//点击事件的信息
    private DisplayImageOptions options;
    private static BitmapDescriptor myMarks;
    //private static BitmapDescriptor myMarks;
    private int mid;
    private ImageView iv;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back:
                    Map.this.finish();
                    break;
                case R.id.mark_layout:
                    //Toast.makeText(getApplication(),mid,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Map.this, GoodsDetail.class);
                    i.putExtra("pid", mid);
                    startActivity(i);
                    break;

                default:
                    break;
            }
        }
    };


    //初始化数据
    private void initMarksData() {
        markInfoList = new ArrayList<MapST>();
        BDmap();


    }


    /**
     * 添加标注覆盖物
     **/
    private void addMarkerOverlay1() {
        mBaiduMap.clear();//清理图层
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        // BitmapDescriptor myMarks = BitmapDescriptorFactory.fromResource(R.mipmap.mapcomputer);
        //遍历MarkInfo的List一个MarkInfo就是一个Mark
        for (int i = 0; i < markInfoList.size(); i++) {// markInfoList.size();
            // BitmapDescriptor myMarks = BitmapDescriptorFactory.fromBitmap(alterBitmap);
            int image_id = 0;
            switch (i) {
                case 0:
                    image_id = R.mipmap.ps_mgood1;
                    break;
                case 1:
                    image_id = R.mipmap.ps_mgood2;
                    break;
                case 2:
                    image_id = R.mipmap.ps_mgood3;
                    break;
                case 3:
                    image_id = R.mipmap.ps_mgood4;
                    break;
                case 4:
                    image_id = R.mipmap.ps_mgood5;
                    break;
                case 5:
                    image_id = R.mipmap.ps_mgood6;
                    break;
                case 6:
                    image_id = R.mipmap.ps_mgood7;
                    break;
                case 7:
                    image_id = R.mipmap.ps_mgood8;
                    break;
                case 8:
                    image_id = R.mipmap.ps_mgood9;
                    break;
                case 9:
                    image_id = R.mipmap.ps_mgood10;
                    break;
                case 10:
                    image_id = R.mipmap.ps_mgood11;
                    break;
                case 11:
                    image_id = R.mipmap.ps_mgood12;
                    break;
                case 12:
                    image_id = R.mipmap.ps_mgood13;
                    break;
                case 13:
                    image_id = R.mipmap.ps_mgood14;
                    break;
                case 14:
                    image_id = R.mipmap.ps_mgood15;
                    break;


                default:
                    break;

            }
//            mapgood = (LinearLayout) (LayoutInflater.from(Map.this)
//                    .inflate(R.layout.mapgood, null)
//                    .findViewById(R.id.mapgoodgroup));
//            good = (ImageView) (LayoutInflater.from(Map.this)
//                    .inflate(R.layout.mapgood, null)
//                    .findViewById(R.id.mapgoodpic));
//            good.setImageResource(image_id);
            //myMarks=BitmapDescriptorFactory.fromView(mapgood);//引入自定义的覆盖物图标，将其转化成一个BitmapDescriptor对象
            //经纬度对象
            myMarks = BitmapDescriptorFactory.fromResource(image_id);
            latLng = new LatLng(markInfoList.get(i).getLatitude(), markInfoList.get(i).getLongitude());//需要创建一个经纬对象，通过该对象就可以定位到处于地图上的某个具体点
            //图标
            options = new MarkerOptions().position(latLng).icon(myMarks).zIndex(6);
            marker = (Marker) mBaiduMap.addOverlay(options);//将覆盖物添加到地图上
            Bundle bundle = new Bundle();//创建一个Bundle对象将每个mark具体信息传过去，当点击该覆盖物图标的时候就会显示该覆盖物的详细信息
            bundle.putSerializable("mark", markInfoList.get(i));
            marker.setExtraInfo(bundle);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //锁定横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //初始化百度地图SDK
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_map);
        initBaiduMap();
        initMyLocation();
        inits();
        Refresh = (ImageView) findViewById(R.id.refresh);
        Back = (ImageView) findViewById(R.id.back);
        markLayout = (LinearLayout) findViewById(R.id.mark_layout);//获取标志物的点击事件id
        Refresh.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
        markLayout.setOnClickListener(mylistener);//弹出框的点击事件

        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        initMarksData();//初始化数据
        mBaiduMap.setOnMapClickListener(this);
        //添加覆盖物响应事件
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
//                Intent i = new Intent(Map.this, Map_GoodsDetail.class);
//                startActivity(i)
                Bundle bundle = marker.getExtraInfo();
                MapST MyMarker = (MapST) bundle.getSerializable("mark");
                iv = (ImageView) markLayout.findViewById(R.id.map_goodsdetails_img);
                TextView nameTv = (TextView) markLayout.findViewById(R.id.map_goodsdetails_title);
                TextView contentTv = (TextView) markLayout.findViewById(R.id.map_goodsdetails_content);
                //iv.setImageResource(MyMarker.getImageId());
                ImgLO.initImageLoader(Map.this);
                ImageLoader.getInstance().displayImage(MyMarker.getImageId(), iv);
                nameTv.setText(MyMarker.getmName());
                contentTv.setText(MyMarker.getmContent());
                mid = MyMarker.getId();
                //System.out.print("-------"+MyMarker.getId());
                //id1=MyMarker.getId();
                //初始化一个InfoWindow
                initInfoWindow(MyMarker, marker);
                markLayout.setVisibility(View.VISIBLE);
                return true;
            }


        });
        //

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                addMarkerOverlay1();
            }
        }

    };


    //百度地图的网络请求
//    private void BDmap(){
//        String url = "http://182.61.37.142/IslandTrading/analysis/type_collection";
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params= new RequestParams();
//        params.add("pType","{pType:数码3CC}");
//        client.get(url,params,new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                System.out.println("--"+response.toString());
//                for(int i=0;i<response.length();i++){
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        JSONObject good = jsonObject.getJSONObject("good");
//                        JSONObject content = good.getJSONObject("content");
//                        int id = content.getInt("Product_Id");
//                        String name=content.getString("Product_Name");
//                        String describe=content.getString("Product_Describe");
//                        //double price=content.getDouble("Product_Price");
//                        //uil=http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1
//                        String picture=content.getString("Product_Image_Url");
//                        double lagitude=content.getDouble("Product_Lagitude");
//                        double longgitude=content.getDouble("Product_Longgitude");//38.0432
//                        markInfoList.add(new  MapST(id,longgitude,lagitude,picture,name,describe));
//                         Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
//                       //调用handle
//                        Message msg = handler.obtainMessage();
//                        msg.what = 0;
//                        handler.sendMessage(msg);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        });
//       // addMarkerOverlay();
//
//    }
    private void BDmap() {
        String url = "http://182.61.37.142/IslandTrading/analysis/getTop";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        // params.add("pType","{pType:数码3CC}");
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("--" + response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //  JSONObject good = jsonObject.getJSONObject("good");
                        // JSONObject content = good.getJSONObject("content");
                        int id = jsonObject.getInt("Product_Id");
                        String name = jsonObject.getString("Product_Name");
                        String describe = jsonObject.getString("Product_Describe");
                        //double price=content.getDouble("Product_Price");
                        //uil=http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1
                        String picture = jsonObject.getString("Product_Image_Url");
                        double lagitude = jsonObject.getDouble("Product_Lagitude");
                        double longgitude = jsonObject.getDouble("Product_Longgitude");//38.0432
                        markInfoList.add(new MapST(id, longgitude, lagitude, picture, name, describe));
                        //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                        //调用handle
                        Message msg = handler.obtainMessage();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        // addMarkerOverlay();

    }

    //图片的初始化请求的一些东西
    private void inits() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.odetailone) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.odetailone)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.odetailone)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)  // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)  // 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))// 设置成圆角图片
                .build();// 创建配置过得DisplayImageOption对象

    }


    /**
     * 初始化出一个InfoWindow
     */
    private void initInfoWindow(MapST MyMarker, Marker marker) {
        // TODO Auto-generated method stub
        InfoWindow infoWindow;
        //InfoWindow中显示的View内容样式，显示一个TextView
        TextView infoWindowTv = new TextView(Map.this);
        //infoWindowTv.setBackgroundResource(R.drawable.location_tips);
        infoWindowTv.setPadding(10, 10, 10, 10);
        //infoWindowTv.setText(MyMarker.getmName());
        // infoWindowTv.setText(MyMarker.getmName());
        //infoWindowTv.setTextColor(Color.parseColor("#FFFFFF"));
        final LatLng latLng = marker.getPosition();
        Point p = mBaiduMap.getProjection().toScreenLocation(latLng);//将地图上的经纬度转换成屏幕中实际的点
        p.y -= 47;//设置屏幕中点的Y轴坐标的偏移量
        LatLng ll = mBaiduMap.getProjection().fromScreenLocation(p);//把修改后的屏幕的点有转换成地图上的经纬度对象
        infoWindow = new InfoWindow(infoWindowTv, ll, 10);
        mBaiduMap.showInfoWindow(infoWindow);//显示InfoWindow


    }

    //点击地图的其他部分，然后可以收回弹出框
    @Override
    public void onMapClick(LatLng arg0) {
        markLayout.setVisibility(View.GONE);
        mBaiduMap.hideInfoWindow();//隐藏InfoWindow
        Log.e(TAG, "--------------看看这个方法有没有加载进去");
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }


    /**
     * 初始化百度地图
     */
    private void initBaiduMap() {
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19.0f);
        mBaiduMap.setMapStatus(msu);
    }

    /**
     * 初始化定位相关代码
     */
    private void initMyLocation() { // 定位SDK初始化
        mLocationClient = new LocationClient(getApplicationContext()); // 设置定位的相关配置
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000); // 自动定位间隔
        option.setIsNeedAddress(true);// 是否需要地址
        option.setIsNeedLocationPoiList(true); // 定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy); // 根据配置信息对定位客户端进行设置

        mLocationClient.setLocOption(option); // 注册定位监听
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        // 设置定位图标
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.tree);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
    }


    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) { // mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return; // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            // 设置BaiduMap的定位数据
            mBaiduMap.setMyLocationData(locData); // 记录位置信息
            mCurrentLantitude = location.getLatitude();
            mCurrentLongitude = location.getLongitude(); // 第一次定位时，将地图位置移动到当前位置

            if (isFristLocation) {
                isFristLocation = false;
                center2myLoc();
            } // Log记录位置信息
            StringBuffer sb = new StringBuffer(256);
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\naddress : ");
            sb.append(location.getAddrStr());
            for (int i = 0; i < location.getPoiList().size(); i++) {
                Poi p = location.getPoiList().get(i);
                sb.append("\nPoi NO.");
                sb.append(i);
                sb.append(" : ");
                sb.append(p.getId());
                sb.append("-");
                sb.append(p.getName());
                sb.append("-");
                sb.append(p.getRank());
            }
            Log.i("BaiduLocationInfo", sb.toString());
        }
    }


    /**
     * BaiduMap移动到我的位置
     */
    private void center2myLoc() {
        LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
        // 设置当前定位位置为BaiduMap的中心点，并移动到定位位置
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
    }


    @Override
    protected void onStart() { // 开启图层定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        super.onStart();
    }

    @Override
    protected void onStop() { // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume(); // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause(); // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}