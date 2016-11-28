package com.daomaidaomai.islandtrading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.daomaidaomai.islandtrading.controller.HomePage;
import com.daomaidaomai.islandtrading.ui.First_Login;
import com.daomaidaomai.islandtrading.ui.Login;

public class MainActivity extends Activity implements Animation.AnimationListener{
    private RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //得到视图控件
        rl = (RelativeLayout) findViewById(R.id.rl);
        //创建透明度动画对象
        AlphaAnimation anima = new AlphaAnimation(0.3f,1.0f);
        //设置动画显示时间
        anima.setDuration(1000);
        //启动动画
        rl.startAnimation(anima);
        //为动画对象设置动画监听器
        anima.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        skip();  //动画结束后跳转到别的页面
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 动画结束后跳转到别的页面
     */
    private void skip() {
        startActivity(new Intent(MainActivity.this, First_Login.class));
        finish();
    }
}
