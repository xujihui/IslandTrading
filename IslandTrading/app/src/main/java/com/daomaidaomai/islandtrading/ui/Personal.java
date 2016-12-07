package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;


public class Personal extends Activity {
    private LinearLayout Back;
    private TextView Edit;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit: {
                    Intent i = new Intent(Personal.this, Personal_Edit.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    Personal.this.finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_show);

        Edit = (TextView) findViewById(R.id.edit);
        Back = (LinearLayout) findViewById(R.id.back);

        Edit.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }


}



