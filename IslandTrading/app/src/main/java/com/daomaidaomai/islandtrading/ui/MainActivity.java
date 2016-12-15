package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.daomaidaomai.islandtrading.R;

public class MainActivity extends Activity implements Animation.AnimationListener {
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.welcome);
        //得到视图控件
        rl = (RelativeLayout) findViewById(R.id.rl);
        //创建透明度动画对象
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
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
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }
}
