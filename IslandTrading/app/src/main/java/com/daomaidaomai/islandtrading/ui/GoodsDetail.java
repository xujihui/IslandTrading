package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.ChatActivity;
import com.daomaidaomai.islandtrading.easeui.EaseConstant;


public class GoodsDetail extends Activity {
    //以后通过网络请求获取用户Id
    String conversation = "小明";

    private LinearLayout ChatMessage;
    private LinearLayout Lin;
    private LinearLayout Back;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buy:
                    Intent i = new Intent(GoodsDetail.this, Order.class);
                    startActivity(i);
                    break;
                case R.id.back:
                    GoodsDetail.this.finish();
                    break;
                case R.id.chatmessage:
                    startActivity(new Intent(getApplication(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation));
                    break;
                default:
                    break;
            }
        }
    };

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
        setContentView(R.layout.goods_detail);
        Lin = (LinearLayout) findViewById(R.id.buy);
        Back = (LinearLayout) findViewById(R.id.back);
        ChatMessage = (LinearLayout) findViewById(R.id.chatmessage);

        Lin.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);
        ChatMessage.setOnClickListener(mylistener);

    }
}
