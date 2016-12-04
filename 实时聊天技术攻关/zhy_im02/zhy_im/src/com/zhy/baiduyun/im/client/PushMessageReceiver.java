package com.zhy.baiduyun.im.client;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.zhy.baiduyun.im.MainActivity;
import com.zhy.baiduyun.im.PushApplication;
import com.zhy.baiduyun.im.R;
import com.zhy.baiduyun.im.bean.ChatMessage;
import com.zhy.baiduyun.im.bean.Message;
import com.zhy.baiduyun.im.bean.User;
import com.zhy.baiduyun.im.dao.UserDB;
import com.zhy.baiduyun.im.utils.NetUtil;
import com.zhy.baiduyun.im.utils.PreUtils;
import com.zhy.baiduyun.im.utils.SendMsgAsyncTask;
import com.zhy.baiduyun.im.utils.SharePreferenceUtil;
import com.zhy.baiduyun.im.utils.T;
import com.zhy.baiduyun.im.utils.TimeUtil;

public class PushMessageReceiver extends FrontiaPushMessageReceiver
{
	public static final int NOTIFY_ID = 0x000;

	public static int mNewNum = 0;// 通知栏新消息条目，我只是用了一个全局变量，

	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	public static ArrayList<onNewMessageListener> msgListeners = new ArrayList<onNewMessageListener>();
	public static ArrayList<onNewFriendListener> friendListeners = new ArrayList<onNewFriendListener>();
	public static ArrayList<onNetChangeListener> netListeners = new ArrayList<onNetChangeListener>();
	public static ArrayList<onBindListener> bindListeners = new ArrayList<onBindListener>();

	public static interface onNewMessageListener
	{
		public abstract void onNewMessage(Message message);
	}

	public static interface onNewFriendListener
	{
		public abstract void onNewFriend(User u);
	}

	public static interface onNetChangeListener
	{
		public abstract void onNetChange(boolean isNetConnected);
	}

	public static interface onBindListener
	{
		public abstract void onBind(String userId, int errorCode);
	}

	@Override
	public void onBind(final Context context, int errorCode, String appid,
			String userId, String channelId, String requestId)
	{
		String responseString = "onBind errorCode=" + errorCode + " appid="
				+ appid + " userId=" + userId + " channelId=" + channelId
				+ " requestId=" + requestId;
		Log.e(TAG, responseString);

		if (errorCode == 0)
		{
			SharePreferenceUtil util = PushApplication.getInstance()
					.getSpUtil();
			util.setAppId(appid);
			util.setChannelId(channelId);
			util.setUserId(userId);
			util.setTag("美女");
		} else
		// 如果网络正常，则重试
		{
			if (NetUtil.isNetConnected(context))
			{

				T.showLong(context, "启动失败，正在重试...");
				new Handler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						PushManager.startWork(context,
								PushConstants.LOGIN_TYPE_API_KEY,
								PushApplication.API_KEY);
					}
				}, 2000);// 两秒后重新开始验证
			} else
			{
				T.showLong(context, R.string.net_error_tip);
			}
		}
		// 回调函数
		for (int i = 0; i < bindListeners.size(); i++)
			bindListeners.get(i).onBind(userId, errorCode);
	}

	private void showNotify(Message message)
	{
		mNewNum++;
		// 更新通知栏
		PushApplication application = PushApplication.getInstance();

		int icon = R.drawable.copyright;
		CharSequence tickerText = message.getNickname() + ":"
				+ message.getMessage();
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		notification.flags = Notification.FLAG_NO_CLEAR;
		// 设置默认声音
		// notification.defaults |= Notification.DEFAULT_SOUND;
		// 设定震动(需加VIBRATE权限)
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.contentView = null;

		Intent intent = new Intent(application, MainActivity.class);
		// 当点击通知时，我们让原有的Activity销毁，重新实例化一个
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(application, 0,
				intent, 0);
		notification.setLatestEventInfo(PushApplication.getInstance(),
				application.getSpUtil().getNick() + " (" + mNewNum + "条新消息)",
				tickerText, contentIntent);
		application.getNotificationManager().notify(NOTIFY_ID, notification);// 通知一下才会生效哦
	}

	@Override
	public void onMessage(Context context, String message,
			String customContentString)
	{
		String messageString = "收到消息 message=\"" + message
				+ "\" customContentString=" + customContentString;
		Log.e(TAG, messageString);
		Message receivedMsg = PushApplication.getInstance().getGson()
				.fromJson(message, Message.class);
		// 对接收到的消息进行处理
		parseMessage(receivedMsg);

	}

	/**
	 * 消息：1、携带hello，表示新人加入，应该自动回复一个world 消息； 2、普通消息；
	 * 
	 * @param msg
	 */
	private void parseMessage(Message msg)
	{
		String userId = msg.getUserId();
		// 自己的消息
		if (userId
				.equals(PushApplication.getInstance().getSpUtil().getUserId()))
			return;
		if (checkHasNewFriend(msg) || checkAutoResponse(msg))
			return;
		// 普通消息
		UserDB userDB = PushApplication.getInstance().getUserDB();
		User user = userDB.selectInfo(userId);
		// 漏网之鱼
		if (user == null)
		{
			user = new User(userId, msg.getChannelId(), msg.getNickname(), 0, 0);
			userDB.addUser(user);
			// 通知监听的面板
			for (onNewFriendListener listener : friendListeners)
				listener.onNewFriend(user);
		}
		if (msgListeners.size() > 0)
		{// 有监听的时候，传递下去
			for (int i = 0; i < msgListeners.size(); i++)
				msgListeners.get(i).onNewMessage(msg);
		} else
		// 当前没有任何监听，即处理后台状态
		{
			// 将新来的消息进行存储
			ChatMessage chatMessage = new ChatMessage(msg.getMessage(), true,
					userId, msg.getHeadIcon(), msg.getNickname(), false,
					TimeUtil.getTime(msg.getTimeSamp()));
			PushApplication.getInstance().getMessageDB()
					.add(userId, chatMessage);
			showNotify(msg);
		}
	}

	/**
	 * 检测是否是自动回复
	 * 
	 * @param msg
	 */
	private boolean checkAutoResponse(Message msg)
	{
		String world = msg.getWorld();
		String userId = msg.getUserId();
		if (!TextUtils.isEmpty(world))
		{
			User u = new User(userId, msg.getChannelId(), msg.getNickname(),
					msg.getHeadIcon(), 0);
			PushApplication.getInstance().getUserDB().addUser(u);// 存入或更新好友
			// 通知监听的面板
			for (onNewFriendListener listener : friendListeners)
				listener.onNewFriend(u);

			return true;
		}
		return false;
	}

	/**
	 * 检测是否是新人加入
	 * 
	 * @param msg
	 */
	private boolean checkHasNewFriend(Message msg)
	{
		String userId = msg.getUserId();
		String hello = msg.getHello();
		// 新人发送的消息
		if (!TextUtils.isEmpty(hello))
		{
			Log.e("checkHasNewFriend", msg.getUserId());

			// 新人
			User u = new User(userId, msg.getChannelId(), msg.getNickname(),
					msg.getHeadIcon(), 0);
			PushApplication.getInstance().getUserDB().addUser(u);// 存入或更新好友
			T.showShort(PushApplication.getInstance(), u.getNick() + "加入");

			// 给新人回复一个应答
			Message message = new Message(System.currentTimeMillis(), "");
			message.setWorld("world");
			new SendMsgAsyncTask(PushApplication.getInstance().getGson()
					.toJson(message), userId);
			// 通知监听的面板
			for (onNewFriendListener listener : friendListeners)
				listener.onNewFriend(u);

			return true;
		}

		return false;
	}

	@Override
	public void onNotificationClicked(Context context, String title,
			String description, String customContentString)
	{

		String notifyString = "通知点击 title=\"" + title + "\" description=\""
				+ description + "\" customContent=" + customContentString;
		Log.e(TAG, notifyString);

	}

	@Override
	public void onSetTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId)
	{
		String responseString = "onSetTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.e(TAG, responseString);

	}

	@Override
	public void onDelTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId)
	{
		String responseString = "onDelTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.e(TAG, responseString);

	}

	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
			String requestId)
	{
		String responseString = "onListTags errorCode=" + errorCode + " tags="
				+ tags;
		Log.e(TAG, responseString);

	}

	@Override
	public void onUnbind(Context context, int errorCode, String requestId)
	{
		String responseString = "onUnbind errorCode=" + errorCode
				+ " requestId = " + requestId;
		Log.e(TAG, responseString);

		// 解绑定成功，设置未绑定flag，
		if (errorCode == 0)
		{
			PreUtils.unbind(context);
		}
	}

}
