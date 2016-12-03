package com.daomaidaomai.islandtrading.ui;


import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.util.AppConstant;
import com.daomaidaomai.islandtrading.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends Activity {
    private Button Btn;
    private TextView Tex;
    private Button Btnqq;
    private Tencent mTencent;
    public static QQAuth mQQAuth;
    public static String mAppid;
    public static String openidString;
    public static String nicknameString;
    public static String TAG = "Login";
    public Bitmap bitmap = null;
    Intent intent;
    ProgressDialog dialog = null;
    Util util;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(Login.this);
        findViewById();
        setOnclickLister();
    }

    private void findViewById() {
        Btn = (Button) findViewById(R.id.loginhome);
        Tex = (TextView) findViewById(R.id.regist);
        Btnqq = (Button) findViewById(R.id.new_login_btn);
    }
    private void setOnclickLister() {
        Btn.setOnClickListener(button);
        Tex.setOnClickListener(button);
        Btnqq.setOnClickListener(button);
    }
    View.OnClickListener button=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginhome:
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                    break;
                case R.id.regist:
                    Intent j = new Intent(Login.this, Regist.class);
                    startActivity(j);
                    break;
                case R.id.new_login_btn:
                    LoginQQ();
                    break;
            }

        }
    };
    // 这里是调用QQ登录的关键代码
    public void LoginQQ() {
        mAppid = AppConstant.APP_ID;
        mTencent = Tencent.createInstance(mAppid, getApplicationContext());
        mTencent.login(Login.this, "all", new BaseUiListener());

    }
    //注销时的代码
    public void Logout() {
        mTencent.logout(Login.this);
    }
    /**
     * 当自定义的监听器实现IUiListener接口后，必须要实现接口的三个方法， onComplete onCancel onError
     * 分别表示第三方登录成功，取消 ，错误。
     */
    private class BaseUiListener implements IUiListener {

        public void onCancel() {
            // TODO Auto-generated method stub

        }

        public void onComplete(Object response) {
            intent = new Intent(Login.this, Home.class);
            dialog.setMessage("正在登录");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();
            // TODO Auto-generated method stub
            // Toast.makeText(getApplicationContext(), "登录成功", 0).show();

            try {
                // 获得的数据是JSON格式的，获得你想获得的内容
                // 如果你不知道你能获得什么，看一下下面的LOG
                Log.e(TAG, "-------------" + response.toString());
                openidString = ((JSONObject) response).getString("openid");
                // openidTextView.setText(openidString);
                Log.e(TAG, "-------------" + openidString);
                // access_token= ((JSONObject)
                // response).getString("access_token"); //expires_in =
                // ((JSONObject) response).getString("expires_in");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /**
             * 到此已经获得OpneID以及其他你想获得的内容了
             * QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
             * sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
             * 如何得到这个UserInfo类呢？
             */
            QQToken qqToken = mTencent.getQQToken();
            UserInfo info = new UserInfo(getApplicationContext(), qqToken);
            // 这样我们就拿到这个类了，之后的操作就跟上面的一样了，同样是解析JSON
            info.getUserInfo(new IUiListener() {

                public void onComplete(final Object response) {
                    // TODO Auto-generated method stub
                    Log.e(TAG, "---------------111111");
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    // nicknameString = response.toString();
                    Log.e(TAG, "-----111---" + response.toString());
                    /**
                     * 由于图片需要下载所以这里使用了线程，如果是想获得其他文字信息直接 在mHandler里进行操作
                     *
                     */

                    new Thread() {

                        @Override
                        public void run() {

                            // TODO Auto-generated method stub
                            JSONObject json = (JSONObject) response;
                            try {
                                bitmap = util.getbitmap(json//Util.getbitmap
                                        .getString("figureurl_qq_2"));
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            Message msg = new Message();
                            msg.obj = bitmap;
                            msg.what = 1;

                            mHandler.sendMessage(msg);
                            dialog.dismiss();
//							customView.setVisibility(View.INVISIBLE);
                        }
                    }.start();
                }

                public void onCancel() {
                    Log.e(TAG, "--------------111112");
                    // TODO Auto-generated method stub
                }

                public void onError(UiError arg0) {
                    // TODO Auto-generated method stub
                    Log.e(TAG, "-111113" + ":" + arg0);
                }


            });
//这一行的变动
//             startActivity(new
//             Intent(Login.this,Personal.class));
            startActivity(intent);
        }

        public void onError(UiError arg0) {
            // TODO Auto-generated method stub

        }

    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        nicknameString = response.getString("nickname");
                        intent.putExtra("a", nicknameString);
                        // nicknameTextView.setText(nicknameString);
                        Log.e(TAG, "--" + nicknameString);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                // userlogo.setImageBitmap(bitmap);
                intent.putExtra("b", bitmap);
                startActivity(intent);
                finish();

            }
        }

    };
}