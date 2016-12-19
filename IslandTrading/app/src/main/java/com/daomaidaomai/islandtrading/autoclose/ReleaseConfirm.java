package com.daomaidaomai.islandtrading.autoclose;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.ui.Home;


public class ReleaseConfirm extends Activity {
    private Button Btn;
    private TextView tv_paysuccess_time;//开始是3秒
    private Boolean abc = false;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backtohome: {
                    Intent i = new Intent(ReleaseConfirm.this, Home.class);
                    startActivity(i);
                    abc = true;
                    break;
                }
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
        setContentView(R.layout.release_confirm_layout);

        Btn = (Button) findViewById(R.id.backtohome);
        Btn.setOnClickListener(mylistener);

        tv_paysuccess_time = (TextView) findViewById(R.id.timenumber);

        CountDownTextViewHelper helper_pay = new CountDownTextViewHelper(tv_paysuccess_time, "0", 5, 1);
        helper_pay.setOnFinishListener(new CountDownTextViewHelper.OnFinishListener() {

            @Override
            public void finish() {
                if (abc == false) {
                    Intent intent2 = new Intent(ReleaseConfirm.this, Home.class);
                    startActivity(intent2);
                }
            }
        });
        helper_pay.start();
    }

}
