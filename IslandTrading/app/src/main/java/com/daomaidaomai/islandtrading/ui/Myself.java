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
import com.daomaidaomai.islandtrading.controller.MyBuy;
import com.daomaidaomai.islandtrading.controller.MyPublish;
import com.daomaidaomai.islandtrading.controller.MySold;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Myself extends Activity {
    private Button Btn;
    private LinearLayout Main;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Chat;

    private LinearLayout Output;
    private LinearLayout Sellout;
    private LinearLayout Bought;
    private LinearLayout Favourite;
    private LinearLayout Help;
    private LinearLayout Setting;
    private LinearLayout Back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Btn = (Button) findViewById(R.id.mylogin);
        Main = (LinearLayout) findViewById(R.id.main);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Chat = (LinearLayout) findViewById(R.id.chat);

        Output = (LinearLayout) findViewById(R.id.myoutput);
        Sellout = (LinearLayout) findViewById(R.id.mysellout);
        Bought = (LinearLayout) findViewById(R.id.mybought);
        Favourite = (LinearLayout) findViewById(R.id.myfavourite);
        Setting = (LinearLayout) findViewById(R.id.setting);
        Help = (LinearLayout) findViewById(R.id.help);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Myself.this.finish();
            }
        });
        Output.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, MyPublish.class);
                startActivity(i);

            }
        });
        Sellout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, MySold.class);
                startActivity(i);

            }
        });
        Bought.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this,MyBuy.class);
                startActivity(i);

            }
        });

        //Favourite.setOnClickListener(new View.OnClickListener() {

         //   @Override
         //   public void onClick(View v) {
         //       Intent i = new Intent(Myself.this, Login.class);
         //       startActivity(i);

         //   }
       // });

        Help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Help.class);
                startActivity(i);

            }
        });
        Setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Myself.this, Set.class);
                startActivity(i);

            }
        });


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