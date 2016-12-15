package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.Face;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class Order extends Activity {

    private Button Facedeal;
    private Button Onlinedeal;
    private Button Canceldeal;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.facedeal: {
                    Intent i = new Intent(Order.this, Facedeal.class);
                    startActivity(i);
                    break;
                }
                case R.id.onlinedeal: {
                    Intent i = new Intent(Order.this, Pay.class);
                    startActivity(i);
                    break;
                }
                case R.id.canceldeal: {
                    Intent i = new Intent(Order.this, Canceldeal.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Order.this.finish();
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
        setContentView(R.layout.activity_order);

        Facedeal = (Button) findViewById(R.id.facedeal);
        Onlinedeal = (Button) findViewById(R.id.onlinedeal);
        Canceldeal = (Button) findViewById(R.id.canceldeal);
        Back = (LinearLayout) findViewById(R.id.back);

        Facedeal.setOnClickListener(mylistener);
        Onlinedeal.setOnClickListener(mylistener);
        Canceldeal.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}
