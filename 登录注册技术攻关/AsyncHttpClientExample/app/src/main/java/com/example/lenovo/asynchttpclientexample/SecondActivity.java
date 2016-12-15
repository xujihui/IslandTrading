package com.example.lenovo.asynchttpclientexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et = (EditText) findViewById(R.id.et);

        //请求API接口
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://v.juhe.cn/xiangji_weather/real_time_weather.php?areaid=101010100&key=72d9f3e2318af725da072a37f012c4d9";
        client.get(getApplicationContext(), url
                , new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response.toString());
                        try {
                            JSONObject result = response.getJSONObject("result");
                            JSONObject data = result.getJSONObject("data");
                            String output = data.getString("cw") + "\n"
                                    + data.getString("w") + "\n"
                                    + data.getInt("rh") + "\n"
                                    + data.getString("wd");
                            et.setText(output);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
