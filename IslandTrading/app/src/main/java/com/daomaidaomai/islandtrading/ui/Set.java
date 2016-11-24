package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.daomaidaomai.islandtrading.R;


public class Set extends Activity {
    private RelativeLayout Personal;
    private RelativeLayout Aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        Personal = (RelativeLayout) findViewById(R.id.personal);
        Aboutus = (RelativeLayout) findViewById(R.id.aboutus);

        Personal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Set.this, Personal.class);
                startActivity(i);

            }
        });

       Aboutus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Set.this, Aboutus.class);
                startActivity(i);

            }
        });




    }
}
