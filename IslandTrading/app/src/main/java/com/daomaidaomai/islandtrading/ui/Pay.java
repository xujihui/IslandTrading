package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class Pay extends Activity {
    private Button Btn;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pay: {
                    Intent i = new Intent(Pay.this, Onlinedeal.class);
                    startActivity(i);
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
        setContentView(R.layout.activity_pay);
        Btn = (Button) findViewById(R.id.pay);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
    }
}
