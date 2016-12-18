package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daomaidaomai.islandtrading.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Help extends Activity {
    private LinearLayout Back;
    private String time , content , contact;
    private EditText ed_content  , ed_contact;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        setContentView(R.layout.help);

        Back = (LinearLayout) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Help.this.finish();
            }
        });

        ed_content = (EditText)findViewById(R.id.Et_content);
        ed_contact = (EditText)findViewById(R.id.Ed_contact);
        btn_submit = (Button)findViewById(R.id.Btn_submit);

        content = ed_content.getText().toString().trim();
        contact = ed_contact.getText().toString().trim();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                time = formatter.format(curDate);

                String url = "http://10.7.92.57:8080/IslandTrading/analysis/submit_fb";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params= new RequestParams();

//      params.add("fb", "{\"FB_CONTENT\":\"反馈1\",\"FB_CONTACT\":\"1523015666\",\"FB_TIME\":"+"'"+time+"'"+"}" );
                params.add("fb", "{\"FB_CONTENT\":" + "'" + content + "'" + ",\"FB_CONTACT\":" + "'" + contact + "'" +",\"FB_TIME\":"+"'"+time+"'"+"}" );

                client.get(url, params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        super.onSuccess(statusCode, headers, responseString);
                        System.out.println("----成功调用onSuccess:"+responseString);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        System.out.println("----成功调用onFailure:"+responseString);
                        Toast.makeText(getApplicationContext(),responseString,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
