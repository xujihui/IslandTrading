package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Chat extends Activity {
    private LinearLayout Main;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Main = (LinearLayout) findViewById(R.id.main);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);

        Main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chat.this, Home.class);
                startActivity(i);

            }
        });
        Map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chat.this, Map.class);
                startActivity(i);

            }
        });
        Sell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chat.this, Sell.class);
                startActivity(i);

            }
        });

        Myself.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chat.this, Myself.class);
                startActivity(i);

            }
        });


    }
}