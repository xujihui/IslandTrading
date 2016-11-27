package com.example.administrator.baidumap_schoool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* 地图控件 */
    private MapView mMapView = null;
    /* 百度地图控制器 */
    private BaiduMap mBaiduMap = null;
    /* 百度地图UI控制器 */
    private UiSettings mUiSettings = null;
    /* 定位的客户端 */
    private LocationClient mLocationClient = null;
    /* 周边雷达管理器 */
    private RadarSearchManager mRadarSearchManager = null;
    /* 定位的监听器 */
    public MyLocationListener mMyLocationListener;
    /* 周边雷达的监听器 */
    MyRadarSearchListener mRadarSerchListener = null;
    /* 当前定位的模式 */
    private MyLocationConfiguration.LocationMode mCurrentMode =
            MyLocationConfiguration.LocationMode.NORMAL;
    /* 是否是第一次定位 */
    private volatile boolean isFristLocation = true;

    /* 最新一次的经纬度 */
    private double mCurrentLantitude;
    private double mCurrentLongitude;

    /* UserID */
    private String mUserID = "蝙蝠侠";

    /* 地图定位的模式*/
    private String[] mStyles = new String[]{"地图模式【正常】", "地图模式【跟随】", "地图模式【罗盘】"};

    /* 当前地图定位模式的 Index */
    private int mCurrentStyle = 0;


    /**
     * 初始化百度地图
     */
    private void initBaiduMap() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        // 获取百度地图控制器
        mBaiduMap = mMapView.getMap();
        // 获取地图UI控制器
        mUiSettings = mBaiduMap.getUiSettings();
        // 设置比例尺为500M
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        // 隐藏指南针
        mUiSettings.setCompassEnabled(false);
        setMarkerListener();
    }


    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // mapView销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    // 此处设置开发者获取到的方向信息,顺时针 0-360
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置BaiduMap的定位数据
            mBaiduMap.setMyLocationData(locData);
            // 记录位置信息
            mCurrentLantitude = location.getLatitude();
            mCurrentLongitude = location.getLongitude();
            // 第一次定位时，将地图位置移动到当前位置
            if (isFristLocation) {
                isFristLocation = false;
                center2myLoc();
            }
            // Log记录位置信息
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
     * 设置周边雷达监听
     */
    public class MyRadarSearchListener
            implements RadarSearchListener, RadarUploadInfoCallback {
        /* 上传位置时自动调用的回调接口 */
        @Override
        public RadarUploadInfo onUploadInfoCallback() {
            // 将要上传的Info
            RadarUploadInfo info = new RadarUploadInfo();
            // Info的备注信息
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhmmss");
            Date curDate = new Date(System.currentTimeMillis());
            String str = simpleDateFormat.format(curDate);
            info.comments = str;
            // Info的点信息
            LatLng pt = new LatLng(mCurrentLantitude, mCurrentLongitude);
            info.pt = pt;
            // 返回要上传的信息，即上传信息
            return info;
        }

        /* 上传状态监听 */
        @Override
        public void onGetUploadState(RadarSearchError radarSearchError) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss :");
            Date curDate = new Date(System.currentTimeMillis());
            String strTime = simpleDateFormat.format(curDate);
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                // 上传成功
                Log.i("RadarUpload", strTime + "上传成功");
            } else {
                // 上传失败
                Log.i("RadarUpload", strTime + "上传错误：" + radarSearchError.toString());
            }
        }

        /* 查询周边的人监听 */
        @Override
        public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult,
                                        RadarSearchError radarSearchError) {
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                Log.i("RadarUpload", "查询周边成功");
                // 清理覆盖物
                mBaiduMap.clear();
                for (int i = 0; i < radarNearbyResult.infoList.size(); i++) {
                    Log.i("RadarUpload", "NO." + i + " : " +
                            radarNearbyResult.infoList.get(i).userID + "\n" +
                            radarNearbyResult.infoList.get(i).comments + "\n" +
                            radarNearbyResult.infoList.get(i).distance + "\n" +
                            radarNearbyResult.infoList.get(i).pt + "\n" +
                            radarNearbyResult.infoList.get(i).timeStamp);
                    addMarker(radarNearbyResult.infoList.get(i).userID,
                            radarNearbyResult.infoList.get(i).pt);
                }
            } else {
                Log.i("RadarUpload", "查询周边错误：" + radarSearchError.toString());
            }
        }

        /* 清除位置信息监听 */
        @Override
        public void onGetClearInfoState(RadarSearchError radarSearchError) {
            if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                //清除成功
                Log.i("RadarUpload", "清除位置成功");
            } else {
                //清除失败
                Log.i("RadarUpload", "清除位置失败");
            }
        }
    }


    /**
     * 添加标注覆盖物
     */
    private void addMarker(String userID, LatLng pt) {
        int n;
        if (userID.equals("钢铁侠"))
            n = R.drawable.gangtiexia;
        else if (userID.equals("蝙蝠侠"))
            n = R.drawable.bianfuxia;
        else if (userID.equals("闪电侠"))
            n = R.drawable.shandianxia;
        else
            n = R.drawable.sishen;
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(n);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(pt)// 设置marker的位置
                .icon(bitmap);  // 必须设置marker图标
        //在地图上添加Marker，并显示
        Marker marker = (Marker) mBaiduMap.addOverlay(option);
    }


    /**
     * 初始化定位相关代码
     */
    private void initMyLocation() {
        // 定位SDK初始化
        mLocationClient = new LocationClient(getApplicationContext());
        // 设置定位的相关配置
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        // 打开gps
        option.setCoorType("bd09ll");
        // 设置坐标类型
        option.setScanSpan(1000);
        // 自动定位间隔
        option.setIsNeedAddress(true);
        // 是否需要地址
        option.setIsNeedLocationPoiList(true);
        // 定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 根据配置信息对定位客户端进行设置
        mLocationClient.setLocOption(option);
        // 注册定位监听
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        // 设置定位图标
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.location);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
    }

    @Override
    protected void onStart() {
        // 开启图层定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        super.onStop();
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


    /**
     * BaiduMap移动到我的位置
     */
    private void center2myLoc() {
        LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
        // 设置当前定位位置为BaiduMap的中心点,并移动到定位位置
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_map_mark: // 标注覆盖物
                addMarkerOverlay();
                break;
            case R.id.id_map_polygon: // 多边形覆盖物
                addPolygonOverlay();
                break;
            case R.id.id_map_text: // 文本覆盖物
                addTextOverLay();
                break;
            case R.id.id_map_clear: // 清空
                mBaiduMap.clear();
                break;
            case R.id.id_menu_map_upload: // 上传位置
                mRadarSearchManager.startUploadAuto(mRadarSerchListener, 5000);
                break;
            case R.id.id_menu_map_destory: // 销毁位置
                // 停止上传位置信息
                mRadarSearchManager.stopUploadAuto();
                // 清除用户信息
                mRadarSearchManager.clearUserInfo();
                break;
            case R.id.id_menu_map_search:  // 查询周边
                // 构造请求参数，其中 centerPt 是自己的位置坐标
                LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
                RadarNearbySearchOption option = new RadarNearbySearchOption()
                        .centerPt(ll) // 搜索中心点
                        .pageNum(0) // 分页编号
                        .pageCapacity(50) // 每页容量
                        .radius(2000); // 检索半径
                // 发起查询请求
                mRadarSearchManager.nearbyInfoRequest(option);
                break;
            case R.id.id_menu_map_myLoc: // 标注覆盖物
                center2myLoc();
                break;
            case R.id.id_menu_map_style: // 地图模式
                mCurrentStyle = (++mCurrentStyle) % mStyles.length;
                item.setTitle(mStyles[mCurrentStyle]);
                // 设置自定义图标
                switch (mCurrentStyle) {
                    case 0:
                        mCurrentMode =
                                MyLocationConfiguration.LocationMode.NORMAL;
                        break;
                    case 1:
                        mCurrentMode =
                                MyLocationConfiguration.LocationMode.FOLLOWING;
                        break;
                    case 2:
                        mCurrentMode =
                                MyLocationConfiguration.LocationMode.COMPASS;
                        break;
                }
                BitmapDescriptor mCurrentMarker =
                        BitmapDescriptorFactory.fromResource(R.drawable.location);
                MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfigeration(config);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 设置标注覆盖物监听
     */
    private void setMarkerListener() {
        // 调用BaiduMap对象的setOnMarkerClickListener方法
        // 设置marker点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                // 点击处理
                Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // 调用BaiduMap对象的setOnMarkerDragListener方法
        // 设置marker拖拽事件的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            private boolean bFirst = true;

            public void onMarkerDrag(Marker marker) {
                // 拖拽中
                if (bFirst) {
                    Toast.makeText(MainActivity.this, "拖拽中", Toast.LENGTH_SHORT).show();
                    bFirst = false;
                }
            }

            public void onMarkerDragEnd(Marker marker) {
                // 拖拽结束
                Toast.makeText(MainActivity.this, "拖拽结束", Toast.LENGTH_SHORT).show();
                bFirst = true;
            }

            public void onMarkerDragStart(Marker marker) {
                // 开始拖拽
                Toast.makeText(MainActivity.this, "开始拖拽", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化百度地图SDK
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initBaiduMap();
        initMyLocation();

        // 初始化周边雷达
        initRadarSearch();
    }


    /**
     * 初始化周边雷达相关
     */
    private void initRadarSearch() {
        // 获取周边雷达实例
        mRadarSearchManager = RadarSearchManager.getInstance();
        // 周边雷达设置监听，RadarSearchListener接口实现
        mRadarSerchListener = new MyRadarSearchListener();
        mRadarSearchManager.addNearbyInfoListener(mRadarSerchListener);
        // 周边雷达设置用户身份标识，id为null是设备标识，必须设置
        mRadarSearchManager.setUserID(mUserID);
    }


    /**
     * 添加标注覆盖物
     */
    private void addMarkerOverlay() {
        // 定义Maker坐标点
        LatLng point = new LatLng(38.004043, 114.529128);
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().anchor(0.5492f, 0.0f)// 设置锚点
                .position(point) // 设置marker的位置
                .draggable(true) // 设置是否允许拖拽
                .title("软件学院") // 设置marker的title
                .icon(bitmap); // 必须设置marker图标
        // 在地图上添加Marker，并显示
        Marker marker = (Marker) mBaiduMap.addOverlay(option);

        LatLng point1 = new LatLng(38.003272, 114.52725);
        // 构建Marker图标
        BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option1 = new MarkerOptions().anchor(0.5492f, 0.0f)//设置锚点
                .position(point1) // 设置marker的位置
                .draggable(true) // 设置是否允许拖拽
                .title("时光塔") // 设置marker的title
                .icon(bitmap1); // 必须设置marker图标
        // 在地图上添加Marker，并显示
        Marker marker1 = (Marker) mBaiduMap.addOverlay(option1);

    }


    /**
     * 添加多边形覆盖物
     */
    private void addPolygonOverlay() {
        // 河北师范大学的四个顶点坐标
        LatLng pt1 = new LatLng(38.007178, 114.519372);
        LatLng pt2 = new LatLng(38.007263, 114.53608);
        LatLng pt3 = new LatLng(37.99879, 114.536152);
        LatLng pt4 = new LatLng(37.998761, 114.519695);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        // 构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts) // 点信息
                .stroke(new Stroke(5, 0xAAFF0000)) // 边框
                .fillColor(0xAAFFFF00); // 填充颜色
        // 在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);
    }


    /**
     * 添加文本覆盖物
     */
    private void addTextOverLay() {
        // 定义文字所显示的坐标点114.529123,38.004036
        LatLng llText = new LatLng(38.004036, 114.529123);
        // 构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00) // 背景颜色
                .fontSize(32) // 字号
                .fontColor(0xFFFF00FF) // 字体颜色
                .text("软件学院") // 文本
                .position(llText); // 位置
        // 在地图上添加该文字对象并显示114.525302,38.004337
        mBaiduMap.addOverlay(textOption);
        LatLng llText1 = new LatLng(38.004337, 114.525302);
        // 构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption1 = new TextOptions()
                .bgColor(0xAAFFFF00) // 背景颜色
                .fontSize(32) // 字号
                .fontColor(0xFFFF00FF) // 字体颜色
                .text("博物馆") // 文本
                .position(llText1); // 位置
        // 在地图上添加该文字对象并显示114.520648,38.00238
        mBaiduMap.addOverlay(textOption1);
        LatLng llText2 = new LatLng(38.00238, 114.520648);
        // 构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption2 = new TextOptions()
                .bgColor(0xAAFFFF00) // 背景颜色
                .fontSize(32) // 字号
                .fontColor(0xFFFF00FF) // 字体颜色
                .text("科技园") // 文本
                .position(llText2); // 位置
        // 在地图上添加该文字对象并显示114.526675,38.002557
        mBaiduMap.addOverlay(textOption2);
        LatLng llText3 = new LatLng(38.002557, 114.526675);
        // 构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption3 = new TextOptions()
                .bgColor(0xAAFFFF00) // 背景颜色
                .fontSize(32) // 字号
                .fontColor(0xFFFF00FF) // 字体颜色
                .text("公交楼b座  ") // 文本
                .position(llText3); // 位置
        // 在地图上添加该文字对象并显示
        mBaiduMap.addOverlay(textOption3);
    }
}
