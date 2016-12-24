package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.autoclose.ReleaseConfirm;
import com.daomaidaomai.islandtrading.util.BDGpsServiceListener;
import com.daomaidaomai.islandtrading.util.BaiduMapUtils;
import com.daomaidaomai.islandtrading.util.GetLocation;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Release extends Activity {
    private Button Btn, btn_submit;
    private LinearLayout Back;
    private RadioButton radio_btn;
//    private TextView content;
//    private LocationReceiver lr;

    private PopupWindow mPopWindow;

    private Spinner spinner;
//    private static final String LOCSTART = "START_LOCATING";

    // 临时文件
    private File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
    private int PHOTO_REQUEST_GALLERY = 1;// 表示调用系统的图库
    private int PHOTO_REQUEST_CUT = 2;// 表示图片剪裁
    private int PHOTO_REQUEST_CAREMA = 3;// 表示调用系统拍照功能


    //商品信息
    private String mid;
    private EditText mEt_ProductName;
    private EditText mEt_ProductDescribe;
    private EditText mEt_TradeSite;
    private EditText mEt_ProducePrice;
    private ImageView imageView;
    private String type;

    private String ProductName;

    //定位
    private GetLocation mLocation;

    //图片
    private Uri ImageUri;//Uri
    private int ImageSelect;//区别是照相图片还是图库图片

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
        setContentView(R.layout.release_layout);

        findView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sort,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setListener();

//        lr = new LocationReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("NEW LOCATION SENT");
//        registerReceiver(lr, intentFilter);

        //设置输入框内容监听
        mEt_ProductName.addTextChangedListener(textWatcher);

        //定位对象
        mLocation = new GetLocation(this);

        BaiduMapUtils.reverseGeoParse(mLocation.getCurrentLongitude(), mLocation.getCurrentLatitude(), new OnGetGeoCoderResultListener() {
            //获取正向解析结果时执行函数
            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
            }

            //获取反向解析结果时执行函数
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                    Toast.makeText(getApplication(), "抱歉，未能找到结果!", Toast.LENGTH_LONG);
                } else {////得到结果后处理方法
                    Toast.makeText(getApplication(), "地址为：" + result.getAddress(), Toast.LENGTH_LONG);
                }
            }

        });
    }

    public void findView() {
        Btn = (Button) findViewById(R.id.confirm);
        btn_submit = (Button) findViewById(R.id.Btn_submit);
        Back = (LinearLayout) findViewById(R.id.back);
        imageView = (ImageView) findViewById(R.id.IvImg);
        radio_btn = (RadioButton) findViewById(R.id.radio_button);
        spinner = (Spinner) findViewById(R.id.spinner);
        mEt_ProductName = (EditText) findViewById(R.id.Et_ProductName);
        mEt_ProductDescribe = (EditText) findViewById(R.id.Et_ProductDescribe);
        mEt_ProducePrice = (EditText) findViewById(R.id.Et_ProductPrice);
        mEt_TradeSite = (EditText) findViewById(R.id.Et_TradeSite);
    }

    public void setListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplication(),selected,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Btn.setOnClickListener(mylistener);
        btn_submit.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);

        radio_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

//        lr = new LocationReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("NEW LOCATION SENT");
//        registerReceiver(lr, intentFilter);

    }

    public void getHttp() {
        String UserName = "孙铖铖";//后期要改
        ProductName = mEt_ProductName.getText().toString().trim();
        String ProductPirce = mEt_ProducePrice.getText().toString().trim();
        String ProductDescribe = mEt_ProductDescribe.getText().toString().trim();
        String TradeSite = mEt_TradeSite.getText().toString().trim();
        String Longitude = null;//经度
        String Latitude = null;//维度
        String currentTime = null;//当前时间


        if (mEt_ProductDescribe.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "商品描述不能为空", Toast.LENGTH_SHORT).show();
        } else if (mEt_TradeSite.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "交易地点不能为空", Toast.LENGTH_SHORT).show();
        } else if (mEt_ProducePrice.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "商品价格不能为空", Toast.LENGTH_SHORT).show();
        } else {
            String url = "http://182.61.37.142/IslandTrading/analysis/addGoods";
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            //经度
            Longitude = mLocation.getCurrentLongitude() + "";
            //纬度
            Latitude = mLocation.getCurrentLatitude() + "";
            currentTime = mLocation.getCurrentTime();
            System.out.println("----Location: " + Longitude + " Latitude: " + Latitude + " currentTime: " + currentTime);
            //params.add("goods","{User_Username:"+UserName+",Product_Name:"+ProductName+",Product_Price:"+ProductPirce+",Product_Describe:"+ProductDescribe+",Product_Time:"+currentTime+",Product_Site:"+TradeSite+",Product_View:0,Product_Positive:0,Product_Status:false,Product_Top:false,Product_Longgitude:"+Longitude+",Product_Lagitude:"+Latitude+",Product_Type="+type+"}");
            params.add("goods", "{User_Username:" + UserName + ",Product_Name:" + ProductName + ",Product_Price:" + ProductPirce + ",Product_Describe:" + ProductDescribe + ",Product_Time:" + "'" + currentTime + "'" + ",Product_Site:" + TradeSite + ",Product_View:0,Product_Positive:0,Product_Status:false,Product_Top:false,Product_Longgitude:" + Longitude + ",Product_Lagitude:" + Latitude + ",Product_Type=" + type + "}");
            client.get(url, params, new JsonHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    if (responseString.substring(0, 6).equals("商品发布成功")) {
                        Intent i = new Intent(Release.this, ReleaseConfirm.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                        System.out.println("----成功调用onFailure:" + responseString);
                        getProductId();
                        if (Release.this.isFinishing()) {
                            mLocation.ReleaseListener();
                        }
                    }

                }
            });
        }

    }

    //得到商品id并且商品图片上传
    public void getProductId() {
        System.out.println("----调用了这个方法了啊");
        String url = "http://182.61.37.142/IslandTrading/analysis/lookupprice";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("pName", "{Product_Name:" + "'" + ProductName + "'" + "}");//商品名称一会儿设置看看汉字可不可以提交ProductName
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject product = response.getJSONObject("PRODUCT");
                        JSONObject content = product.getJSONObject("content");
                        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("----调用了啊" + content.toString());
                        mid = content.getInt("Product_Id") + "";
                        Toast.makeText(getApplicationContext(), "得到的id" + mid, Toast.LENGTH_SHORT).show();
                        System.out.println("----商品id" + mid);
                        System.out.println("----难道没有在这里获得URI吗" + ImageUri);
                        getImage(ImageUri, ImageSelect, mid);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    /*
    * 输入框内容状态改变监听*/
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mEt_ProductName.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "商品名称不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirm: {
                    getHttp();
                    break;
                }
                case R.id.back:
                    Release.this.finish();
                    mLocation.ReleaseListener();//移除定位监听
                    break;
                case R.id.Btn_submit:
                    showPopupWindow();
                    break;
                case R.id.pop_camera: {
                    Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 指定调用相机拍照后照片的储存路径
                    cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                    startActivityForResult(cameraintent, PHOTO_REQUEST_CAREMA);
                    mPopWindow.dismiss();
                }
                break;
                case R.id.pop_album: {
                    // 激活系统图库，选择一张图片
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                    mPopWindow.dismiss();
                }
                break;
                default:
                    break;
            }
        }
    };

    //选择拍照或者从图库上传照片，需要黑雾效果
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(Release.this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_camera);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_album);
        tv1.setOnClickListener(mylistener);
        tv2.setOnClickListener(mylistener);
        //显示PopupWindow
        View rootview = LayoutInflater.from(Release.this).inflate(R.layout.release_layout, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);

    }

    //    class LocationReceiver extends BroadcastReceiver {
//
//        String locationMsg = "";
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // TODO Auto-generated method stub
//            locationMsg = intent.getStringExtra("newLoca");
//            content.setText(locationMsg);
//
//        }
//    }
    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                ImageUri = uri;
                ImageSelect = 0;
                System.out.println("---从相册返回的数据图片的URI  " + uri);
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            String SDState = Environment.getExternalStorageState();
            if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                ImageUri = Uri.fromFile(tempFile);
                //尝试在这儿调一下getImage(uri,1,"180");可以吗
                crop(Uri.fromFile(tempFile));
                //尝试在这儿调一下getImage(uri,1,"180");可以吗
                ImageSelect = 1;
            } else {
                Toast.makeText(Release.this, "未找到存储卡，无法存储照片！",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                imageView.setImageBitmap(bitmap);
            }
            /*try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        //getImage(uri,1,"180");
        ImageUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        //尝试一下放在这儿可以吗getImage(uri,1,"180");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public void getImage(Uri uri, int i, String productid) {
        try {
            File file = null;
            if (i == 0) {//相册
                String url = getRealPathFromUri(getApplicationContext(), uri);
                file = new File(url);
            } else if (i == 1) {//照相机
                System.out.println("----到底有没有URI啊啊啊！" + uri);
                file = new File(new URI(uri.toString()));
                if (!file.exists()) {
                    System.out.println("----文件不存在啊啊啊！");
                }
            }
            System.out.println("-----商品id" + productid);
            System.out.println("uri: ----- " + uri);
            if (file.exists() && file.length() > 0) {
                Log.e("tag", file.getPath());
                //ip是 虚拟机使用的电脑的某个ip，不是电脑ip
                String str_url = "http://182.61.37.142/IslandTrading/analysis/uploadImg";

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                try {
                    params.put("profile_picture", file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                params.add("Product_Id", productid);

                client.post(str_url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
                        System.out.println("----上传成功！");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG).show();
                        System.out.println("----上传失败！statusCode:" + statusCode + "  " + error.toString());
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "文件不存在", Toast.LENGTH_SHORT).show();
                System.out.println("----文件不存在！");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            // 将临时文件删除
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
