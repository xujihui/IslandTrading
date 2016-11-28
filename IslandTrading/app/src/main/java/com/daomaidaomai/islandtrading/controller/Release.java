package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.ui.Sell;


public class Release extends Activity {
    private Button Btn;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_layout);
        Btn = (Button) findViewById(R.id.confirm);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Release.this.finish();
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Release.this, ReleaseConfirm.class);
                startActivity(i);

            }
        });

    }

}
