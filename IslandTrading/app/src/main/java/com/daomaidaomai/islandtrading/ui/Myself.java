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

import static com.daomaidaomai.islandtrading.R.id.regist;

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
    private LinearLayout Help;
    private LinearLayout Setting;
    private LinearLayout Back;


    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back: {
                    Myself.this.finish();
                    break;
                }
                case R.id.mylogin: {
                    Intent i = new Intent(Myself.this, Login.class);
                    startActivity(i);
                    break;
                }
                case R.id.main: {
                    Intent i = new Intent(Myself.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.map: {
                    Intent i = new Intent(Myself.this, Map.class);
                    startActivity(i);
                    break;
                }
                case R.id.sell: {
                    Intent i = new Intent(Myself.this, Sell.class);
                    startActivity(i);
                    break;
                }
                case R.id.chat: {
                    Intent i = new Intent(Myself.this, Chat.class);
                    startActivity(i);
                    break;
                }
                case R.id.myoutput: {
                    Intent i = new Intent(Myself.this, MyPublish.class);
                    startActivity(i);
                    break;
                }
                case R.id.mysellout: {
                    Intent i = new Intent(Myself.this, MySold.class);
                    startActivity(i);
                    break;
                }
                case R.id.mybought: {
                    Intent i = new Intent(Myself.this, MyBuy.class);
                    startActivity(i);
                    break;
                }
                case R.id.setting: {
                    Intent i = new Intent(Myself.this, Set.class);
                    startActivity(i);
                    break;
                }
                case R.id.help: {
                    Intent i = new Intent(Myself.this, Help.class);
                    startActivity(i);
                    break;
                }
                default:
                    break;
            }
        }
    };

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
        Setting = (LinearLayout) findViewById(R.id.setting);
        Help = (LinearLayout) findViewById(R.id.help);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Main.setOnClickListener(mylistener);
        Map.setOnClickListener(mylistener);
        Sell.setOnClickListener(mylistener);
        Chat.setOnClickListener(mylistener);
        Output.setOnClickListener(mylistener);
        Sellout.setOnClickListener(mylistener);
        Bought.setOnClickListener(mylistener);
        Setting.setOnClickListener(mylistener);
        Help.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}