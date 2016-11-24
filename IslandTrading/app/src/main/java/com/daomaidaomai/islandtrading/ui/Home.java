package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.ClassifyAllActivity;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Home extends Activity {
    private ImageView Img;
    private LinearLayout Chat;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Chat = (LinearLayout) findViewById(R.id.chat);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);


        Img = (ImageView) findViewById(R.id.classfy);
        Img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ClassifyAllActivity.class);
                startActivity(i);

            }
        });


        Chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Chat.class);
                startActivity(i);

            }
        });
        Map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Map.class);
                startActivity(i);

            }
        });
        Sell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Sell.class);
                startActivity(i);

            }
        });

        Myself.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Myself.class);
                startActivity(i);

            }
        });




    }
}