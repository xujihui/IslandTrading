package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;


public class GoodsDetail extends Activity {

    private LinearLayout Lin;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        Lin = (LinearLayout) findViewById(R.id.buy);
        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GoodsDetail.this.finish();
            }
        });

        Lin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(GoodsDetail.this,Order.class);
                startActivity(i);

            }
        });



    }
}
