package com.daomaidaomai.islandtrading.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.adapter.AddAdapter;
import com.daomaidaomai.islandtrading.entity.Addres;

import java.util.ArrayList;
import java.util.List;


public class AddressChioce extends AppCompatActivity {

    private ListView lv;
    private List<Addres> ls = new ArrayList<Addres>();
    private AddAdapter maddAdapter;
    private LinearLayout Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.addres_choice);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddressChioce.this.finish();
            }
        });


        findViewById();
        getData();
        maddAdapter = new AddAdapter(this, ls);
        set();
    }

    private void findViewById() {
        lv = (ListView) findViewById(R.id.listview_lv);
    }

    private void set() {
        lv.setAdapter(maddAdapter);
    }

    private void getData() {
        ls.add(new Addres(01, "小红", "1523212xxxx", "河北师范大学新校区", "[默认地址]"));
        ls.add(new Addres(02, "小红", "1523212xxxx", "河北师范大学新校区", ""));
        ls.add(new Addres(03, "小红", "1523212xxxx", "河北师范大学新校区", ""));
        ls.add(new Addres(04, "小红", "1523212xxxx", "河北师范大学新校区", ""));
        ls.add(new Addres(05, "小红", "1523212xxxx", "河北师范大学新校区", ""));
        ls.add(new Addres(06, "小红", "1523212xxxx", "河北师范大学新校区", ""));
        ls.add(new Addres(07, "小红", "1523212xxxx", "河北师范大学新校区", ""));
    }
}
