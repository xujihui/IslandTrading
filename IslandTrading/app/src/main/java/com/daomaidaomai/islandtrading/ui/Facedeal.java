package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class Facedeal extends Activity {
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               Facedeal.this.finish();
            }
        });

    }
}
