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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;

import static android.R.attr.name;


public class Personal extends Activity {
    private Button bt;
    private TextView tv;
    Login login;

    private TextView tv_name;
    private LinearLayout Back;
    private TextView Edit;

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
        setContentView(R.layout.personal_information_show);
        findViewById();
        Intent i = getIntent();
        String s = i.getStringExtra("a");
        Edit = (TextView) findViewById(R.id.edit);
        Back = (LinearLayout) findViewById(R.id.back);

        Edit.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
    }

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit: {
                    Intent i = new Intent(Personal.this, Personal_Edit.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Personal.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void findViewById() {
        tv_name = (TextView) findViewById(R.id.setting_personal_information_user);

    }


}



