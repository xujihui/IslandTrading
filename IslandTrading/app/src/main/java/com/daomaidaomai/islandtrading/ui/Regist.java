package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.UserService;
import com.daomaidaomai.islandtrading.controller.UserServiceRegist;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static cn.smssdk.SMSSDK.getSupportedCountries;
import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;


/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Regist extends Activity {
    private LinearLayout Back;
    private final String TAG="--MainActivity--";
    private final String appKey="1977b299804e6";
    private final String appSecret="76348e09c80f3974726941347a73b9d7";

    private EventHandler eh;
    private Button bt_getCode;
    private String phone; //手机号码
    private String code; //验证码
    private Button vertify;

    private boolean isChange;//控制按钮样式是否改变
    private boolean tag = true; //每次验证请求需要间隔60S
    private int i=60;
    private EditText pswd;   //输入密码
    private EditText repswd;  //确认密码

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.back:
                    Regist.this.finish();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(mylistener);

        // 启动短信验证sdk
        SMSSDK.initSDK(this, appKey, appSecret);
        findViewById();
        set();

        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Message msg = new Message();
                        msg.arg1 = 0;
                        msg.obj = data;
                        handler.sendMessage(msg);
                        Log.d(TAG, "提交验证码成功");
                        Intent i = new Intent(Regist.this, Home.class);
                        startActivity(i);
                       // regist();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Message msg = new Message();
                        //获取验证码成功
                        msg.arg1 = 1;
                        msg.obj = "获取验证码成功";
                        handler.sendMessage(msg);
                        Log.d(TAG, "获取验证码成功");
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        Message msg = new Message();
                        //返回支持发送验证码的国家列表
                        msg.arg1 = 2;
                        msg.obj = "返回支持发送验证码的国家列表";
                        handler.sendMessage(msg);
                        Log.d(TAG, "返回支持发送验证码的国家列表");
                    }
                } else {
                    Message msg = new Message();
                    //返回支持发送验证码的国家列表
                    msg.arg1 = 3;
                    msg.obj = "验证失败";
                    handler.sendMessage(msg);
                    Log.d(TAG, "验证失败");
                    ((Throwable) data).printStackTrace();
                }
            }
        };

        SMSSDK.registerEventHandler(eh); //注册短信回调

    }

    private void findViewById() {
        bt_getCode= (Button) findViewById(R.id.bt_getCode);
        bt_getCode.setClickable(false);
        vertify= (Button) findViewById(R.id.bt_verify);
        pswd=(EditText)findViewById(R.id.password);//密码的获取
        repswd=(EditText)findViewById(R.id.repassword);
        //设置密码的默认为不可见
        pswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        repswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }
    private void set() {
        bt_getCode.setOnClickListener(buton);
        vertify.setOnClickListener(buton);
    }
    View.OnClickListener buton=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_getCode:
                    //获取验证码操作
                    phone=((EditText)findViewById(R.id.et_phone)).getText().toString();
                    if(phone.equals("")){
                        Toast.makeText(Regist.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    }else{
                        //填写了手机号码
                        if(isMobileNO(phone)){
                            //如果手机号码无误，则发送验证请求
                            bt_getCode.setClickable(true);
                            changeBtnGetCode();
                            getSupportedCountries();
                            getVerificationCode("86", phone);
                        }else{
                            //手机号格式有误
                            Toast.makeText(Regist.this,"手机号格式错误，请检查",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.bt_verify:
                    //验证操作
                    code=((EditText)findViewById(R.id.et_code)).getText().toString();

                    if (code.equals("")){
                        Toast.makeText(Regist.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    else if(pswd.getText().toString().trim().length()<6||pswd.getText().toString().trim().length()>12){
                        Toast.makeText(Regist.this, "密码不能少于6为多余12位！！", Toast.LENGTH_LONG).show();
                    }
                    else if(pswd.getText().toString().equals("")){
                        Toast.makeText(Regist.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    else if(!repswd.getText().toString().equals(pswd.getText().toString())){
                        Toast.makeText(Regist.this,"密码输入不一致",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //填写了验证码，进行验证
                        submitVerificationCode("86", phone, code);
                    }
                    break;
            }

        }
    };

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 0:
                    //客户端验证成功，可以进行注册,返回校验的手机和国家代码phone/country
                    Toast.makeText(Regist.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    //获取验证码成功
                    Toast.makeText(Regist.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(Regist.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    /*
  * 改变按钮样式
  * */
    private void changeBtnGetCode() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        //如果活动为空
                        if (Regist.this == null) {
                            break;
                        }

                        Regist.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bt_getCode.setText("获取验证码(" + i + ")");
                                bt_getCode.setClickable(false);
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tag = false;
                }
                i = 60;
                tag = true;

                if (Regist.this != null) {
                    Regist.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bt_getCode.setText("获取验证码");
                            bt_getCode.setClickable(true);
                        }
                    });
                }
            }
        };
        thread.start();
    }

    private boolean isMobileNO(String phone) {

        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phone)) return false;
        else return phone.matches(telRegex);
    }
    private void  regist(){
        //取得用户输入的账号和密码
        String name1=phone;
        String pass1=pswd.getText().toString();
        boolean result= UserServiceRegist.check(name1,pass1);
        if(result)
        {
            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Regist.this, Login.class);
            startActivity(i);
        }else
        {
            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
        }

    }
}