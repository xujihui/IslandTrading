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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;


public class Map extends Activity implements BaiduMap.OnMapClickListener { /* 地图控件*/
    private ImageView Refresh;
    private ImageView Back;
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


    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back:
                    Map.this.finish();
                    break;
                default:
                    break;
            }
        }
    };


    //初始化数据
    private void initMarksData() {
        markInfoList = new ArrayList<MapST>();
        markInfoList.add(new MapST(1, 38.000076, 114.52447, R.mipmap.mapcomputer, "联想笔记本电脑 超极本", "Lenovo联想 商务游戏本"));
        markInfoList.add(new MapST(2, 38.003385, 114.527106, R.mipmap.chat6, "迷你包包", "真的很好看，用了不长的时间"));
        markInfoList.add(new MapST(3, 38.004057, 114.529366, R.mipmap.chat8, "懒人腮红", "一共就用过几次，现在出售"));
    }

    /**
     * 添加标注覆盖物
     **/
    private void addMarkerOverlay() {
        // 图片合成-画布 先去画A 再去画B
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.pxdingsi); // bitmap为只读的
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.local);

        Bitmap alterBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), bitmap2.getConfig());

        Canvas canvas = new Canvas(alterBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawBitmap(bitmap1, 18, 36, paint);
        canvas.drawBitmap(bitmap2, 0, 0, paint);

        // 定义Maker坐标点
      /*  LatLng point = new LatLng(38.000076, 114.52447);
        LatLng point1 = new LatLng(38.003385, 114.527106);
        LatLng point2 = new LatLng(38.004057, 114.529366);

        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(alterBitmap);
        // 构建MarkerOption，用于在地图上添加Marker
        // OverlayOptions option = new MarkerOptions().anchor(0.5f, 1.0f)
        OverlayOptions option = new MarkerOptions()//设置锚点
                .position(point)// 设置marker的位置
                .icon(bitmap); // 必须设置marker图标
        OverlayOptions option1 = new MarkerOptions()//设置锚点
                .position(point1)// 设置marker的位置
                .icon(bitmap); // 必须设置marker图标
        OverlayOptions option2 = new MarkerOptions()//设置锚点
                .position(point2)// 设置marker的位置
                .icon(bitmap); // 必须设置marker图标
        // 在地图上添加Marker，并显示
        Marker marker = (Marker) mBaiduMap.addOverlay(option);

        Marker marker1 = (Marker) mBaiduMap.addOverlay(option1);
        Marker marker2 = (Marker) mBaiduMap.addOverlay(option2);*/
        initMarksData();
        mBaiduMap.clear();//清理图层
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        BitmapDescriptor myMarks = BitmapDescriptorFactory.fromResource(R.mipmap.mapcomputer);
        //遍历MarkInfo的List一个MarkInfo就是一个Mark
        for (int i = 0; i < markInfoList.size(); i++) {
            //经纬度对象
            latLng = new LatLng(markInfoList.get(i).getLatitude(), markInfoList.get(i).getLongitude());//需要创建一个经纬对象，通过该对象就可以定位到处于地图上的某个具体点
            //图标
            options = new MarkerOptions().position(latLng).icon(myMarks).zIndex(6);
            marker = (Marker) mBaiduMap.addOverlay(options);//将覆盖物添加到地图上
            Bundle bundle = new Bundle();//创建一个Bundle对象将每个mark具体信息传过去，当点击该覆盖物图标的时候就会显示该覆盖物的详细信息
            bundle.putSerializable("mark", markInfoList.get(i));
            marker.setExtraInfo(bundle);
        }
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);//通过这个经纬度对象，地图就可以定位到该点
        mBaiduMap.animateMapStatus(msu);

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
        Refresh = (ImageView) findViewById(R.id.refresh);
        Back = (ImageView) findViewById(R.id.back);
        markLayout = (LinearLayout) findViewById(R.id.mark_layout);//获取标志物的点击事件id
        Refresh.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);

        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        //添加覆盖物响应事件
        addMarkerOverlay();

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
//                Intent i = new Intent(Map.this, Map_GoodsDetail.class);
//                startActivity(i);
                Bundle bundle = marker.getExtraInfo();
                MapST MyMarker = (MapST) bundle.getSerializable("mark");
                ImageView iv = (ImageView) markLayout.findViewById(R.id.map_goodsdetails_img);
                TextView nameTv = (TextView) markLayout.findViewById(R.id.map_goodsdetails_title);
                TextView contentTv = (TextView) markLayout.findViewById(R.id.map_goodsdetails_content);
                iv.setImageResource(MyMarker.getImageId());
                nameTv.setText(MyMarker.getmName());
                contentTv.setText(MyMarker.getmContent());
                //初始化一个InfoWindow
                initInfoWindow(MyMarker, marker);
                markLayout.setVisibility(View.VISIBLE);
                return true;
            }
        });


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
        infoWindowTv.setText(MyMarker.getmName());
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