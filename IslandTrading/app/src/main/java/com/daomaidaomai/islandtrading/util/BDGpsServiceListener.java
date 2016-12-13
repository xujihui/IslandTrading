package com.daomaidaomai.islandtrading.util;
/*设置百度地图的类*/
import android.content.Context;
import android.content.Intent;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;


public class BDGpsServiceListener implements BDLocationListener {

	private Context context;
	public static double longitude;
	public static double latitude;
	public static String time;

	public BDGpsServiceListener(){
		super();
	}
	public BDGpsServiceListener(Context context){
		super();
		this.context = context;
	}

	//发送广播，提示更新界面
	private void sendToActivity(String str){
		Intent intent = new Intent();
		intent.putExtra("newLoca", str);
		intent.setAction("NEW LOCATION SENT");
		context.sendBroadcast(intent);
	}
	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if(location == null){return;}
		StringBuffer sb = new StringBuffer();
		sb.append("经度=").append(location.getLongitude());
		sb.append("\n纬度=").append(location.getLatitude());
		sb.append("\n时间=").append(location.getTime());
		if (location.hasRadius()){
		}
		if (location.getLocType() == BDLocation.TypeGpsLocation){
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
		}
		//字符串
		sendToActivity(sb.toString());
		//获得
		longitude=location.getLongitude();
		latitude=location.getLatitude();
		time=location.getTime();
	}
}

