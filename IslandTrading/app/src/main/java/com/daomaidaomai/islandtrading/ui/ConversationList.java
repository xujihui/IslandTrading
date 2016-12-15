package com.daomaidaomai.islandtrading.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.daomaidaomai.islandtrading.R;
import com.daomaidaomai.islandtrading.controller.ChatActivity;
import com.daomaidaomai.islandtrading.easeui.EaseConstant;
import com.daomaidaomai.islandtrading.easeui.ui.EaseConversationListFragment;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class ConversationList extends AppCompatActivity {
    private LinearLayout Main;
    private LinearLayout Map;
    private LinearLayout Sell;
    private LinearLayout Myself;
    private LinearLayout Back;

    //会话列表
    private EaseConversationListFragment conversationListFragment;

    public View.OnClickListener mylistener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main: {
                    Intent i = new Intent(ConversationList.this, Home.class);
                    startActivity(i);
                    break;
                }
                case R.id.map: {
                    Intent i = new Intent(ConversationList.this, Map.class);
                    startActivity(i);
                    break;
                }
                case R.id.sell: {
                    Intent i = new Intent(ConversationList.this, Sell.class);
                    startActivity(i);
                    break;
                }
                case R.id.myself: {
                    Intent i = new Intent(ConversationList.this, Myself.class);
                    startActivity(i);
                    break;
                }
                case R.id.back:
                    ConversationList.this.finish();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_conversation_list);


        //另外如果登录过，APP 长期在后台再进的时候也可能会导致加载到内存的群组和会话为空，
        // 可以在主页面的 oncreate 里也加上这两句代码，当然，更好的办法应该是放在程序的开屏页，
        // 可参考 Demo 的 SplashActivity。SplashActivity是写在onStart()里面
        // 加载所有会话到内存
        EMClient.getInstance().chatManager().loadAllConversations();
        //会话列表
        conversationListFragment = new EaseConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(getApplication(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
            }
        });
        //通过getSupportFragmentManager启动fragment即可
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_list, conversationListFragment).commit();




        Main = (LinearLayout) findViewById(R.id.main);
        Map = (LinearLayout) findViewById(R.id.map);
        Sell = (LinearLayout) findViewById(R.id.sell);
        Myself = (LinearLayout) findViewById(R.id.myself);
        Back = (LinearLayout) findViewById(R.id.back);

        Main.setOnClickListener(mylistener);
        Map.setOnClickListener(mylistener);
        Sell.setOnClickListener(mylistener);
        Myself.setOnClickListener(mylistener);
        Back.setOnClickListener(mylistener);


    }
}