package com.example.administrator.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://japi.juhe.cn/funny/type.from?key=d1e72abd76d3625c9529c3065d045d5f";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                try {
                    JSONObject result1 = response.getJSONObject("result");
                    JSONArray data=result1.getJSONArray("data");
                    tv.setText("");
                    for(int i=0;i<response.length();i++){
                        tv.setText(tv.getText()+"\n"+data.getJSONObject(i)+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
