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

public class Regist extends Activity {
    private Button Btn;
    private LinearLayout Back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Btn = (Button) findViewById(R.id.registed);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Regist.this.finish();
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regist.this, Home.class);
                startActivity(i);

            }
        });

    }
}