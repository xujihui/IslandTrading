package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class Map_GoodsDetail extends Activity {
    private Button Back;
    private Button Details;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.details: {
                    Intent i = new Intent(Map_GoodsDetail.this, GoodsDetail.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Map_GoodsDetail.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //锁定横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.map_goodsdetails);


        Details = (Button) findViewById(R.id.details);

        Back = (Button) findViewById(R.id.back);

        Details.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
    }
}
