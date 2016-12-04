package com.example.administrator.loginpost;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NewsService  {
    /**
     * 登录验证
     * @param userName 姓名
     * @param password 密码
     * @return
     */
    public static boolean save(String userName, String password){
        String path = "http://localhost:8080/miao/ManageServlet/doGet";
       // String path = "http://localhost:8888/miao/user/login";
        Map<String, String> personal = new HashMap<String, String>();
        personal.put("userName", userName);
        personal.put("password", password);
        try {
            return SendGETRequest(path, personal, "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 发送GET请求
     * @param path  请求路径
     * @param personal  请求参数
     * @return 请求是否成功
     * @throws Exception
     */
    private static boolean SendGETRequest(String path, Map<String, String> personal, String ecoding) throws Exception{
        // http://127.0.0.1:8080/Register/ManageServlet?name=1233&password=abc
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        for(Map.Entry<String, String> map : personal.entrySet()){
            url.append(map.getKey()).append("=");
            url.append(URLEncoder.encode(map.getValue(), ecoding));
            url.append("&");
        }
        url.deleteCharAt(url.length()-1);
        System.out.println(url);
        HttpsURLConnection conn = (HttpsURLConnection)new URL(url.toString()).openConnection();
        conn.setConnectTimeout(100000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            return true;
        }
        return false;
    }
}
