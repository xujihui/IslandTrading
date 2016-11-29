package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.view.View;
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
