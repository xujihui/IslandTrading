package com.daomaidaomai.islandtrading.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/12/20.
 */
public class GetLocation {
    private Context context;

    private LocationManager locationManager;// 位置管理类

    private String provider;// 位置提供器

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String currentLongitude;//经度
    private String currentLatitude;//维度
    private String currentTime;//获得当前时间

    public GetLocation(Context context) {
        super();
        this.context = context;
        init();
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void init(){
        // 获得LocationManager的实例
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            //优先使用gps
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // 没有可用的位置提供器
            Toast.makeText(context, "没有位置提供器可供使用", Toast.LENGTH_LONG).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            // 显示当前设备的位置信息
            String firstInfo = "第一次请求的信息";
            showLocation(location, firstInfo);
        } else {
            String info = "无法获得当前位置";
            Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
            //positionText.setText(info);
        }

        // 更新当前位置
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 10 * 1000, 1, locationListener);
    }

   public void ReleaseListener(){
       if (locationManager != null) {
           // 关闭程序时将监听器移除
           if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               // TODO: Consider calling
               //    ActivityCompat#requestPermissions
               // here to request the missing permissions, and then overriding
               //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
               //                                          int[] grantResults)
               // to handle the case where the user grants the permission. See the documentation
               // for ActivityCompat#requestPermissions for more details.
               return;
           }
           locationManager.removeUpdates(locationListener);
       }
   }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onLocationChanged(Location location) {
            // 设备位置发生改变时，执行这里的代码
            String changeInfo = "隔10秒刷新的提示：\n 时间：" + sdf.format(new Date())
                    + ",\n当前的经度是：" + location.getLongitude() + ",\n 当前的纬度是："
                    + location.getLatitude();
            showLocation(location, changeInfo);
        }
    };

    /**
     * 显示当前设备的位置信息
     *
     * @param location
     */
    private void showLocation(Location location, String changeInfo) {
        // TODO Auto-generated method stub
        String current = "当前的经度是：" + location.getLongitude() + ",\n"
                + "当前的纬度是：" + location.getLatitude()+",\n"+"时间"+ sdf.format(new Date());
        currentLongitude = location.getLongitude()+"";
        currentLatitude = location.getLatitude()+"";
        currentTime = sdf.format(new Date());
        //positionText.setText(currentLocation);
        //tipInfo.setText(changeInfo);
        //Toast.makeText(context, current, Toast.LENGTH_LONG).show();

    }

}
