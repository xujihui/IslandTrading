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

public class Myself extends Activity {
    private Button Btn;
    private LinearLayout Main;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Chat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Btn = (Button) findViewById(R.id.mylogin);
        Main = (LinearLayout) findViewById(R.id.main);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Chat = (LinearLayout) findViewById(R.id.chat);

        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Login.class);
                startActivity(i);

            }
        });

        Main.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Home.class);
                startActivity(i);

            }
        });
        Map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Map.class);
                startActivity(i);

            }
        });
        Sell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Sell.class);
                startActivity(i);

            }
        });
        Chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Chat.class);
                startActivity(i);

            }
        });


    }
}