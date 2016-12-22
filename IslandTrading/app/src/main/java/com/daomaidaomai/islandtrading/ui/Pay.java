package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class Pay extends Activity {
    private Button Btn;
    private Button Cancel;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pay: {
                    Intent i = new Intent(Pay.this, Facedeal.class);
                    startActivity(i);
                    Pay.this.finish();
                    break;
                }
                case R.id.cancel: {
                    Intent i = new Intent(Pay.this, Canceldeal.class);
                    startActivity(i);
                    Pay.this.finish();
                    break;
                }
                case R.id.back:
                    Pay.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

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
        setContentView(R.layout.activity_pay);
        //得到视图控件
        getViews();
        //设置监听器
        Cancel.setOnClickListener(mylistener);
        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
    }

    /**
     * 得到视图控件
     */
    void getViews(){
        Btn = (Button) findViewById(R.id.pay);
        Cancel = (Button) findViewById(R.id.cancel);
        Back = (LinearLayout) findViewById(R.id.back);
    }
}
