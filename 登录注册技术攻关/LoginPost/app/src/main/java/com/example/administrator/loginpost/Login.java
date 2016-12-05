package com.example.administrator.loginpost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends AppCompatActivity {

    private EditText user;
    private EditText pswd;
    private Button btn;
    private String userName;
    private String password;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById();
        setlister();
    }

    private void findViewById() {
        user=(EditText)findViewById(R.id.user);
        pswd=(EditText)findViewById(R.id.password);
        btn=(Button) findViewById(R.id.btn);
    }
    private void setlister(){
        btn.setOnClickListener(button);

    }


    View.OnClickListener button=new View.OnClickListener(){
        boolean b=false;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn:
                    //获取控件的文本内容
                    userName=user.getText().toString();
                    password=pswd.getText().toString();
                    if (TextUtils.isEmpty(userName.trim())
                            || TextUtils.isEmpty(password.trim())) {
                        Toast.makeText(Login.this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        // 开启线程处理
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                               // loginByHttpClientPost(userName,password);
//                                result = NewsService.save(userName,password);
//                                if(result){
//                                    Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
//                                }else{
//                                    Toast.makeText(Login.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }).start();

                        b = NewsService.save(userName,password);
                                if(b){
                                    Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(Login.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
                                }
                    }
                    break;
            }

        }
    };
    /*public void loginByHttpClientPost(String userName,String password) {

       /* try {
            //请求登录的地址
            String addres="http://172.16.237.200:8080/video/login.do";
            //根据地址创建url对象
            URL url=new URL(addres);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 传递的数据
            String data = "username=" + URLEncoder.encode(userName, "UTF-8")
                    + "&userpass=" + URLEncoder.encode(password, "UTF-8");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.getBytes().length));
            // 设置请求的头
            urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());

                // 通过runOnUiThread方法进行修改主线程的控件内容
                Login.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里把返回的数据写在控件上 会出现什么情况尼
                      //  tv_result.setText(result);
                    }
                });

            } else {
                System.out.println("链接失败.........");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
