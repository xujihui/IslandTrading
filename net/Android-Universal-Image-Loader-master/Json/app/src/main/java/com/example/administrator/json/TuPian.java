package com.example.administrator.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TuPian extends AppCompatActivity {
   // private ImageView img_info;
    private static int j = 0;
    ImageView img_info[]=new ImageView[2];
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bitmap bitmap = BitmapFactory.decodeFile(msg.obj.toString());
            img_info[msg.arg1].setImageBitmap(bitmap);
        }
    };
    String[] urls=new String[]{"http://10.7.88.26:8080/IslandTrading/analysis/downloadImg?Product_Id=1",
            "http://10.7.88.26:8080/IslandTrading/analysis/downloadImg?Product_Id=2"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tupian);
        img_info[0]=(ImageView)findViewById(R.id.img);
        img_info[1]=(ImageView)findViewById(R.id.img2);
        System.out.print(urls.length+"----------");
        show();
//        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//        asyncHttpClient.get(urls[0], new FileAsyncHttpResponseHandler(TuPian.this) {
//            @Override
//            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {
////                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
////                img_info[0].setImageBitmap(bitmap);
//                System.out.println("---失败");
//            }
//
//            @Override
//            public void onSuccess(int i, Header[] headers, File file) {
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//                img_info[0].setImageBitmap(bitmap);
//            }
//        });

    }
    public void show() {
        for( j=0;j<urls.length;j++) {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            // String str_url = "http://10.7.88.26:8080/IslandTrading/analysis/downloadImg?Product_Id=1";
            asyncHttpClient.get(urls[j].toString(), new FileAsyncHttpResponseHandler(TuPian.this) {
                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, File file) {
                    System.out.println("-----获取文件失败！" + throwable.toString());
                }

                @Override
                public void onSuccess(int i, Header[] headers, File file) {
                    System.out.println("-----获取文件成功！" + file.getPath());
                    Message message = new Message();
                    message.arg1 = j;
                    message.obj = file.getPath();
                    handler.sendMessage(message);

//                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//                    img_info[j].setImageBitmap(bitmap);

                }
            });
        }
    }
}
