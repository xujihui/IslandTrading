package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.UiSettings;

import android.widget.ZoomControls;

import com.daomaidaomai.islandtrading.R;

public class Map extends Activity {
    private ImageView Refresh;
    private ImageView Back;
    private MapView mMapView = null;
    //TextureMapView mapView = null;
    //private TextureMapView mMapView;
    private BaiduMap mBaiduMap = null;// 百度地图控制器
    private UiSettings mUiSettings = null;// 百度地图UI控制器
    private LocationClient mLocationClient;
    /* 定位的监听器*/


    public void refresh() {
        onCreate(null);
    }


    /* 初始化Baidu地图相关设置*/
    private void initBaiduMap() {
        // 获取地图视图
        //获取百度地图视图控件
        mMapView = (MapView) findViewById(R.id.bmapView);
        //获取百度地图控制器
        mBaiduMap = mMapView.getMap();
        // 获取地图UI控制器
        mUiSettings = mBaiduMap.getUiSettings();
        // 设置比例尺为500M
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19.0f);
        mBaiduMap.setMapStatus(msu);
        // 隐藏指南针
        mUiSettings.setCompassEnabled(false);
        setMarkerListener();
    }

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.refresh: {
                    Map.this.refresh();
                    break;
                }
                case R.id.back:
                    Map.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setMarkerListener() {
        // 调用BaiduMap对象的setOnMarkerClickListener方法
        // 设置marker点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                // 点击处理
                Toast.makeText(Map.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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

        setContentView(R.layout.ditu);
        initBaiduMap();

        Refresh = (ImageView) findViewById(R.id.refresh);
        Back = (ImageView) findViewById(R.id.back);
        Refresh.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);

        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }


}
