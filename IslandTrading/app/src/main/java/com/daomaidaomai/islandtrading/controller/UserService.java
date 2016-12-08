package com.daomaidaomai.islandtrading.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
/*
* 验证用户登录的合法性
* */
public class UserService {
    public static boolean check(String name, String pass) {
        String path="http://10.7.88.4:8080/supermarket/analysis/reg_log_user";
       //将用户名密码添加进去
        Map<String,String> params=new HashMap<String,String>();
        params.put("pwd", pass);
        params.put("user", name);
        try {
            return sendGETRequest(path,params,"UTF-8");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    private static boolean sendGETRequest(String path, Map<String, String> params,String encode) throws MalformedURLException, IOException {
        StringBuilder url=new StringBuilder(path);
        url.append("?");
        url.append("mode=register");
        url.append("&");
        for(Map.Entry<String, String> entry:params.entrySet())
        {
            System.out.println("------" + entry.toString());
            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(),encode));
            url.append("&");
        }
        //删掉最后一个&
        url.deleteCharAt(url.length()-1);
        HttpURLConnection conn=(HttpURLConnection)new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        System.out.println("----------"+url);
        if(conn.getResponseCode()==200)
        {
            return true;
        }
        System.out.println("----------"+conn.getRequestMethod());

        return false;
    }
}
