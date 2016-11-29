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
import com.daomaidaomai.islandtrading.controller.ClassifyAllActivity;

import static com.daomaidaomai.islandtrading.R.id.regist;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Login extends Activity {
    private Button Btn;
    private TextView Tex;
    private LinearLayout Back;


    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back: {
                    Login.this.finish();
                    break;
                }
                case R.id.loginhome: {
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.regist: {
                    Intent i = new Intent(Login.this, Regist.class);
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
        setContentView(R.layout.activity_login);

        Btn = (Button) findViewById(R.id.loginhome);
        Tex = (TextView) findViewById(regist);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Tex.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}