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

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.registed: {
                    Intent i = new Intent(Regist.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Regist.this.finish();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Btn = (Button) findViewById(R.id.registed);
        Back = (LinearLayout) findViewById(R.id.back);

        Btn.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}