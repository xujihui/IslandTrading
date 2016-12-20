package com.daomaidaomai.islandtrading.autoclose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
