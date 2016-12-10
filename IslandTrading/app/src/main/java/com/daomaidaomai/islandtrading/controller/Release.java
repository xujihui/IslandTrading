package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.autoclose.ReleaseConfirm;


public class Release extends Activity {
    private Button Btn;
    private LinearLayout Back;
    private RadioButton radio_btn;
    private TextView content;
    private LocationReceiver lr;
    private static final String LOCSTART = "START_LOCATING";


    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirm: {
                    Intent i = new Intent(Release.this, ReleaseConfirm.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "GPS测试开始", Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.back:
                    Release.this.finish();
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        Btn = (Button) findViewById(R.id.confirm);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
        radio_btn = (RadioButton) findViewById(R.id.radio_button);
        radio_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        lr = new LocationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("NEW LOCATION SENT");
        registerReceiver(lr, intentFilter);


    }
    class LocationReceiver extends BroadcastReceiver {

        String locationMsg = "";
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            locationMsg = intent.getStringExtra("newLoca");
            content.setText(locationMsg);

        }
    }


}
