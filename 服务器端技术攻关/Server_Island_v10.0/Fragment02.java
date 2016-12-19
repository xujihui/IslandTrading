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
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by 孙铖铖 on 2016/12/10.
 * 通过框架上传图片
 */

public class Fragment02 extends Fragment {
    private Button btn_upload;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02,container,false);
        btn_upload = (Button)view.findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
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
        File file = new File(Environment.getExternalStorageDirectory() + "/Download/a.png");
        System.out.println("----上传路径：" + Environment.getExternalStorageDirectory() + "/Download/img.jpg");
        if(file.exists() && file.length()>0){
            Log.e("tag",file.getPath());
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put(URLEncoder.encode("profile_picture"),file);
            String str_url = "http://192.168.194.2:8080/IslandTrading/analysis/uploadImg";    //ip是 虚拟机使用的电脑的某个ip，不是电脑ip
            String str_url1 = "http://192.168.194.2:8080/IslandTrading/analysis/uploadImg?Product_Id=1";
            client.post(str_url1, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    Toast.makeText(getContext(), "成功", Toast.LENGTH_LONG).show();
                    System.out.println("----上传成功！");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] responseBody, Throwable error) {
                    Toast.makeText(getContext(), "失败", Toast.LENGTH_LONG).show();
                    System.out.println("----上传失败！statusCode:" + statusCode + "  " + error.toString());
                }
            });
        }else{
            Toast.makeText(getContext(), "文件不存在", Toast.LENGTH_SHORT).show();
            System.out.println("----文件不存在！");
        }

    }

}
