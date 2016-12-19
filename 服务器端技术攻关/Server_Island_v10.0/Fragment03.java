package com.scc.www.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;

import java.io.File;

/**
 * Created by 孙铖铖 on 2016/12/10.
 * 通过框架下载图片
 */

public class Fragment03 extends Fragment {
    private ImageView Iv_03;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFile();
    }

    private void getFile() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        String str_url = "http://192.168.194.2:8080/IslandTrading/analysis/downloadImg?Product_Id=3";
        asyncHttpClient.get(str_url, new FileAsyncHttpResponseHandler(getContext()) {
            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {
                System.out.println("-----获取文件失败！" + throwable.toString());
            }

            @Override
            public void onSuccess(int i, Header[] headers, File file) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                Iv_03.setImageBitmap(bitmap);
                System.out.println("-----获取文件成功！" + file.getPath());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment03,container,false);
        Iv_03 = (ImageView) view.findViewById(R.id.Iv_03);
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
}
