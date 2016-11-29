package com.daomaidaomai.islandtrading.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
