package com.example.administrator.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UPActivity extends AppCompatActivity {


    private TextView tv;
    private EditText user;
    private EditText pswd;
    private Button btn;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById();
        set();
    }

    private void findViewById() {
        user=(EditText)findViewById(R.id.user);
        pswd=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.btn);
        tv_result=(TextView)findViewById(R.id.tv_result);
    }
    private void set(){
        btn.setOnClickListener(button);

    }
    View.OnClickListener button=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn:
                    String userName = user.getText().toString();// 用户名
                    String userPass = pswd.getText().toString();// 用户密码
                    loginByAsyncHttpClientGet(userName, userPass);
                    break;
            }

        }
    };

    public void loginByAsyncHttpClientGet(String userName, String userPass) {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://localhost:8080/supermarket/analysis/reg_log_user";
        RequestParams params = new RequestParams();
        params.put("mode", "check");
        params.put("username", userName);
        params.put("userpass", userPass);

        client.get(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                System.out.println("statusCode------------------- "+ statusCode);

                for (int i = 0; i < headers.length; i++) {
                    Header header = headers[i];
                    System.out.println("header------------Name:"
                            + header.getName() + ",--Value:"
                            + header.getValue());

                }
                tv_result.setText(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                error.printStackTrace();
            }
        });
    }

}
