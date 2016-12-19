package com.example.administrator.json;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.security.AccessController.getContext;

public class Main extends AppCompatActivity {


    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://localhost:8888/supermarket/analysis/lookupprice?pId={PRODUCT_ID:3}";
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());
                String res=null;
                try {
                    JSONObject PRODUCT = response.getJSONObject("PRODUCT");
                    res = PRODUCT.getString("res");
                    JSONObject content = PRODUCT.getJSONObject("content");
                    String output=content.getBoolean("PRODUCT_STATUS")+"\n"
                            +content.getBoolean("PRODUCT_TOP")+"\n"
                            +content.getString("CLASSIFY_NAME")+"\n"
                            +content.getString("PRODUCT_TIME")+"\n"
                            +content.getString("PRODUCT_DESCRIBE")+"\n"
                            +content.getString("PRODUCT_SITE")+"\n"
                            +content.getString("PRODUCT_TOP")+"\n"
                            +content.getInt("PRODUCT_FAVOUR")+"\n"
                            +content.getInt("PRODUCT_ID")+"\n"
                            +content.getInt("PRODUCT_HIT")+"\n"
                            + content.getDouble("PRODUCT_PRICE");
                    tv.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                    tv.setText(res+"\n" );
                }
            }
        });
    }
    
}
