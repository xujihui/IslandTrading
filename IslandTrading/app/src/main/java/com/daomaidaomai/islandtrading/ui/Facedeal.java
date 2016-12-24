package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.autoclose.CountDownTextViewHelper;
import com.daomaidaomai.islandtrading.controller.ChatActivity;
import com.daomaidaomai.islandtrading.easeui.EaseConstant;


public class Facedeal extends Activity {
    //以后通过网络请求获取用户Id
    String conversation = "小明";

    private TextView tv_paysuccess_time;//开始是3秒
    private Boolean abc = false;

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
        setContentView(R.layout.activity_remind);
        tv_paysuccess_time = (TextView) findViewById(R.id.timenumber);


        CountDownTextViewHelper helper_pay = new CountDownTextViewHelper(tv_paysuccess_time, "0", 3, 1);
        helper_pay.setOnFinishListener(new CountDownTextViewHelper.OnFinishListener() {

            @Override
            public void finish() {
                if (abc == false) {
                    startActivity(new Intent(getApplication(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation));
                    Facedeal.this.finish();
                }
            }
        });
        helper_pay.start();
    }
}
