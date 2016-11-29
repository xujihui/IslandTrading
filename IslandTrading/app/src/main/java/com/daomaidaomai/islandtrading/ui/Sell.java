package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.Release;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Sell extends Activity {
    private TextView Tex;
    private ImageView Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tosell: {
                    Intent i = new Intent(Sell.this, Release.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Sell.this.finish();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_sell_layout);
        Tex = (TextView) findViewById(R.id.tosell);
        Back = (ImageView) findViewById(R.id.back);

        Tex.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);

    }
}