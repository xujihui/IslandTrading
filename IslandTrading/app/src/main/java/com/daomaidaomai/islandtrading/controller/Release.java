package com.daomaidaomai.islandtrading.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.autoclose.ReleaseConfirm;


public class Release extends Activity {
    private Button Btn;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirm: {
                    Intent i = new Intent(Release.this, ReleaseConfirm.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Release.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_layout);
        Btn = (Button) findViewById(R.id.confirm);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }

}
