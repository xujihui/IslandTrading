package com.zhy.baiduyun.im.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

import com.zhy.baiduyun.im.PushApplication;
import com.zhy.baiduyun.im.R;
import com.zhy.baiduyun.im.server.BaiduPush;

public class SendMsgAsyncTask
{
	private BaiduPush mBaiduPush;
	private String mMessage;
	private Handler mHandler;
	private MyAsyncTask mTask;
	private String mUserId;
	private OnSendScuessListener mListener;

	public interface OnSendScuessListener
	{
		void sendScuess();
	}

	public void setOnSendScuessListener(OnSendScuessListener listener)
	{
		this.mListener = listener;
	}

	Runnable reSend = new Runnable()
	{

		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			L.i("resend msg...");
			send();// 重发
		}
	};

	public SendMsgAsyncTask(String jsonMsg, String useId)
	{
		// TODO Auto-generated constructor stub
		mBaiduPush = PushApplication.getInstance().getBaiduPush();
		mMessage = jsonMsg;
		mUserId = useId;
		mHandler = new Handler();
	}

	// 发送
	public void send()
	{
		if (NetUtil.isNetConnected(PushApplication.getInstance()))
		{// 如果网络可用
			mTask = new MyAsyncTask();
			mTask.execute();
		} else
		{
			T.showLong(PushApplication.getInstance(), R.string.net_error_tip);
		}
	}

	// 停止
	public void stop()
	{
		if (mTask != null)
			mTask.cancel(true);
	}

	class MyAsyncTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... message)
		{
			String result = "";
			if (TextUtils.isEmpty(mUserId))
				result = mBaiduPush.PushMessage(mMessage);
			else
				result = mBaiduPush.PushMessage(mMessage, mUserId);
			return result;
		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			L.i("send msg result:" + result);
			if (result.contains(BaiduPush.SEND_MSG_ERROR))
			{// 如果消息发送失败，则100ms后重发
				mHandler.postDelayed(reSend, 100);
			} else
			{
				if (mListener != null)
					mListener.sendScuess();
			}
		}
	}
}
