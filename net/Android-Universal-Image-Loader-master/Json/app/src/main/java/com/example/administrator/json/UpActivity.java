package com.example.administrator.json;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


/**
 * Created by Administrator on 2016/12/15.
 */

public class UpActivity extends AppCompatActivity {
    private EditText name;
    private EditText pswd;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById();
        setOnClickLister();
    }
    private void findViewById() {
        name=(EditText)findViewById(R.id.user);
        pswd=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.btn);
    }
    private void setOnClickLister() {
        btn.setOnClickListener(button);
    }
    View.OnClickListener button=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn:
                    getHttp();
                    break;
            }

        }
    };
    private void getHttp(){
        String url = "http://10.7.88.26:8080/supermarket/analysis/reg_log_user";
        //String url = "http://localhost:8080/supermarket/analysis/reg_log_user";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params= new RequestParams();
        params.add("mode","register");
        params.add("USER_NICKNAME","韩寒");
        params.add("USER_USERNAME", name.toString());//15230153135
        params.add("USER_PASSWORD",pswd.toString());//1234
        params.add("USER_TAKINGID","");//15686565
        params.add("USER_CONTACT","15230153136");
        params.add("USER_HUAN_USER","a12345");
        params.add("USER_HUAN_PWD","12345");
        client.get(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                System.out.println("----调用onSuccess:"+responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                System.out.println("----调用onFailure:"+responseString);
                String result = responseString.substring(0,5);
                if( result.equals("注册成功！") ){
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpActivity.this, CeSHi.class);
                   startActivity(i);
                }else if( result.equals("注册失败！") ){
                    Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
