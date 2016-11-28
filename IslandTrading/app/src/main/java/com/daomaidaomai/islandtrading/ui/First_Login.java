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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        Btn = (Button) findViewById(R.id.loginhome);
        Tex = (TextView) findViewById(R.id.regist);


        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(First_Login.this, Home.class);
                startActivity(i);

            }
        });

        Tex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(First_Login.this, Regist.class);
                startActivity(i);

            }
        });

    }
}