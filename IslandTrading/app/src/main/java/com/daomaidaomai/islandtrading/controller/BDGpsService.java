package com.daomaidaomai.islandtrading.controller;
/*百度地图获取位置的函数*/
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.daomaidaomai.islandtrading.util.BDGpsServiceListener;


public class BDGpsService extends Service {

	private static final int minTime = 1000;
	private LocationClient locationClient;
	private BDLocationListener locationListener;
	private LocationClientOption lco;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		lco = new LocationClientOption();
		lco.setLocationMode(LocationMode.Hight_Accuracy);
		//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		lco.setScanSpan(minTime);
		//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		lco.setCoorType("bd09ll");
		//可选，默认gcj02，设置返回的定位结果坐标系
		lco.setOpenGps(true);
		//可选，默认false,设置是否使用gps
		lco.setIsNeedAddress(true);
		locationListener = new BDGpsServiceListener(getApplicationContext());
		locationClient = new LocationClient(getApplicationContext());//声明location类
		locationClient.setLocOption(lco);
		locationClient.registerLocationListener(locationListener);//注册监听函数
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (locationClient != null && !locationClient.isStarted()){
			locationClient.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (locationClient != null && locationClient.isStarted()){
			locationClient.stop();
		}
		locationClient.unRegisterLocationListener(locationListener);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
