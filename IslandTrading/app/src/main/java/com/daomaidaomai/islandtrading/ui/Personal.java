package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daomaidaomai.islandtrading.R;


public class Personal extends Activity {

    private TextView Tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_show);

        Tex = (TextView) findViewById(R.id.edit);

        Tex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Personal.this,Personal_Edit.class);
                startActivity(i);

            }
        });

    }
}
