package com.scc.www.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.net.URL;

/**
 * Created by 孙铖铖 on 2016/12/10.
 */

public class Fragment02 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {
            postFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment02,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void postFile() throws Exception{
        File file = new File(Environment.getExternalStorageDirectory() + "/Download/img.jpg");

//        String path ="E:\\迅雷下载\\img.jpg";
//        File file = new File(path);
        if(file.exists() && file.length()>0){
            Log.e("tag",file.getPath());
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("profile_picture", file);
            String str_url = "http://192.168.100.2:8080/supermarket/analysis/uploadImg";    //终归到底是ip的问题
            client.post(str_url, params,new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    Toast.makeText(getContext(), "成功", Toast.LENGTH_LONG).show();
                    System.out.println("----上传成功！");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] responseBody, Throwable error) {
                    Toast.makeText(getContext(), "失败", Toast.LENGTH_LONG).show();
                    System.out.println("----上传成功！");
                }
            });
        }else{
            Toast.makeText(getContext(), "文件不存在", Toast.LENGTH_SHORT).show();
            System.out.println("----文件不存在！");
        }

    }

}
