package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;

import static android.R.attr.name;


public class Personal extends Activity {
    private Button bt;
    private TextView tv;
    Login login;

    private TextView tv_name;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_show);
        findViewById();
        Intent i=getIntent();
        String s= i.getStringExtra("a");
    }

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
    private void findViewById() {
        tv_name=(TextView)findViewById(R.id.setting_personal_information_user);

    }

}



