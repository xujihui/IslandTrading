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
import com.daomaidaomai.islandtrading.controller.Release;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Sell extends Activity {
    private TextView Tex;
    private ImageView Back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_sell_layout);
        Tex = (TextView) findViewById(R.id.tosell);
        Back = (ImageView) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Sell.this.finish();
            }
        });

        Tex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sell.this, Release.class);
                startActivity(i);

            }
        });
    }
}