package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daomaidaomai.islandtrading.R;


public class Set extends Activity {
    private RelativeLayout Personal;
    private RelativeLayout Aboutus;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.personal: {
                    Intent i = new Intent(Set.this, Personal.class);
                    startActivity(i);
                    break;
                }
                case R.id.aboutus: {
                    Intent i = new Intent(Set.this, Aboutus.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Set.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        Personal = (RelativeLayout) findViewById(R.id.personal);
        Aboutus = (RelativeLayout) findViewById(R.id.aboutus);
        Back = (LinearLayout) findViewById(R.id.back);

        Personal.setOnClickListener(mylistener);
        Aboutus.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}
