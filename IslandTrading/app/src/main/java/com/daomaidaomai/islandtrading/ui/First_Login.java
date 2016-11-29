package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class First_Login extends Activity {
    private Button Btn;
    private TextView Tex;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginhome: {
                    Intent i = new Intent(First_Login.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.regist: {
                    Intent i = new Intent(First_Login.this, Regist.class);
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
        setContentView(R.layout.activity_first_login);

        Btn = (Button) findViewById(R.id.loginhome);
        Tex = (TextView) findViewById(R.id.regist);


        Btn.setOnClickListener(mylistener);
        Tex.setOnClickListener(mylistener);


    }
}