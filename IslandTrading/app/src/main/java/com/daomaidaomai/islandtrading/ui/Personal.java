package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;


public class Personal extends Activity {
    private Button bt;
    private TextView tv;
    Login login;

    private TextView Tex;
    private LinearLayout Back;

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

}



