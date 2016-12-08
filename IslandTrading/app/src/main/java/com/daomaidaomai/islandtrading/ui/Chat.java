package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Chat extends Activity {
    private LinearLayout Main;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main: {
                    Intent i = new Intent(Chat.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.map: {
                    Intent i = new Intent(Chat.this, Map.class);
                    startActivity(i);
                    break;
                }
                case R.id.sell: {
                    Intent i = new Intent(Chat.this, Sell.class);
                    startActivity(i);
                    break;
                }
                case R.id.myself: {
                    Intent i = new Intent(Chat.this, Myself.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Chat.this.finish();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Main = (LinearLayout) findViewById(R.id.main);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);
        Back = (LinearLayout) findViewById(R.id.back);

        Main.setOnClickListener(mylistener);
        Map.setOnClickListener(mylistener);
        Sell.setOnClickListener(mylistener);
        Myself.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}