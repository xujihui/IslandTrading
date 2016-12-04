package com.zhy.baiduyun.im;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.baiduyun.im.bean.ChatMessage;
import com.zhy.baiduyun.im.bean.Message;
import com.zhy.baiduyun.im.bean.User;
import com.zhy.baiduyun.im.client.PushMessageReceiver;
import com.zhy.baiduyun.im.client.PushMessageReceiver.onNewMessageListener;
import com.zhy.baiduyun.im.dao.UserDB;
import com.zhy.baiduyun.im.utils.NetUtil;
import com.zhy.baiduyun.im.utils.SendMsgAsyncTask;
import com.zhy.baiduyun.im.utils.SharePreferenceUtil;
import com.zhy.baiduyun.im.utils.T;

public class ChattingActivity extends Activity implements onNewMessageListener
{

	private TextView mNickName;
	private EditText mMsgInput;
	private Button mMsgSend;

	private ListView mChatMessagesListView;
	private List<ChatMessage> mDatas = new ArrayList<ChatMessage>();
	private ChatMessageAdapter mAdapter;
	private PushApplication mApplication;

	private User mFromUser;
	private UserDB mUserDB;
	private Gson mGson;
	private SharePreferenceUtil mSpUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_chatting);

		initView();
		initEvent();

	}

	private void initView()
	{

		mChatMessagesListView = (ListView) findViewById(R.id.id_chat_listView);
		mMsgInput = (EditText) findViewById(R.id.id_chat_msg);
		mMsgSend = (Button) findViewById(R.id.id_chat_send);
		mNickName = (TextView) findViewById(R.id.id_nickname);

		mApplication = (PushApplication) getApplication();
		mUserDB = mApplication.getUserDB();
		mGson = mApplication.getGson();
		mSpUtil = mApplication.getSpUtil();

		Intent intent = getIntent();
		String userId = intent.getStringExtra("userId");
		Log.e("TAG", userId);

		if (TextUtils.isEmpty(userId))
		{
			finish();
		}

		mFromUser = mUserDB.getUser(userId);
		//未读消息更新为已经读取
		mApplication.getMessageDB().updateReaded(userId);
		
		Log.e("TAG", mFromUser.toString());

		mNickName.setText(mFromUser.getNick());
		// 获取10条聊天记录
		mDatas = mApplication.getMessageDB().find(mFromUser.getUserId(), 1, 10);
		mAdapter = new ChatMessageAdapter(this, mDatas);
		mChatMessagesListView.setAdapter(mAdapter);
		mChatMessagesListView.setSelection(mDatas.size() - 1);

		PushMessageReceiver.msgListeners.add(this);
	}

	private void initEvent()
	{
		mMsgSend.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String msg = mMsgInput.getText().toString();
				if (TextUtils.isEmpty(msg))
				{
					T.showShort(mApplication, "您还未填写消息呢!");
					return;
				}

				if (!NetUtil.isNetConnected(mApplication))
				{
					T.showShort(mApplication, "当前无网络连接！");
					return;
				}
				Message message = new Message(System.currentTimeMillis(), msg);
				new SendMsgAsyncTask(mGson.toJson(message), mFromUser
						.getUserId()).send();

				ChatMessage chatMessage = new ChatMessage();
				chatMessage.setComing(false);
				chatMessage.setDate(new Date());
				chatMessage.setMessage(msg);
				chatMessage.setNickname(mSpUtil.getNick());
				chatMessage.setUserId(mSpUtil.getUserId());
				//消息存入数据库
				mApplication.getMessageDB().add(mFromUser.getUserId(), chatMessage);
				
				mDatas.add(chatMessage);
				mAdapter.notifyDataSetChanged();
				mChatMessagesListView.setSelection(mDatas.size() - 1);
				mMsgInput.setText("");

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				// 得到InputMethodManager的实例
				if (imm.isActive())
				{
					// 如果开启
					imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
							InputMethodManager.HIDE_NOT_ALWAYS);
					// 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
				}

			}
		});
	}

	@Override
	protected void onDestroy()
	{
		PushMessageReceiver.msgListeners.remove(this);
		super.onDestroy();

	}

	@Override
	public void onNewMessage(Message message)
	{
		Log.e("TAG", "getMsg in chatActivity" + message.getNickname());

		// 获得回复的消息
		if (mFromUser.getUserId().equals(message.getUserId()))
		{
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setComing(true);
			chatMessage.setDate(new Date(message.getTimeSamp()));
			chatMessage.setMessage(message.getMessage());
			chatMessage.setUserId(message.getUserId());
			chatMessage.setNickname(message.getNickname());
			chatMessage.setReaded(true);
			mDatas.add(chatMessage);
			mAdapter.notifyDataSetChanged();
			mChatMessagesListView.setSelection(mDatas.size() - 1);
			// 存入数据库，当前聊天记录
			mApplication.getMessageDB().add(mFromUser.getUserId(), chatMessage);
		}
	}
}
