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

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buy: {
                    Intent i = new Intent(GoodsDetail.this,Order.class);
                    startActivity(i);
                    break;
                }
                case R.id.back: {
                    GoodsDetail.this.finish();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        Lin = (LinearLayout) findViewById(R.id.buy);
        Back = (LinearLayout) findViewById(R.id.back);

        Lin.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);




    }
}
