package com.zhy.baiduyun.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.android.pushservice.PushManager;
import com.jauker.widget.BadgeView;
import com.zhy.baiduyun.im.bean.ChatMessage;
import com.zhy.baiduyun.im.bean.Message;
import com.zhy.baiduyun.im.bean.User;
import com.zhy.baiduyun.im.client.PushMessageReceiver;
import com.zhy.baiduyun.im.client.PushMessageReceiver.onNewFriendListener;
import com.zhy.baiduyun.im.client.PushMessageReceiver.onNewMessageListener;
import com.zhy.baiduyun.im.utils.SharePreferenceUtil;
import com.zhy.baiduyun.im.utils.T;
import com.zhy.baiduyun.im.utils.TimeUtil;

/**
 * 朋友列表界面
 * 
 * @author zhy
 * 
 */
public class MainTabFriends extends Fragment implements onNewFriendListener,
		onNewMessageListener
{

	public static final String TAG = MainTabFriends.class.getSimpleName();

	/**
	 * 提供未读消息更新的回调，比如来了一个新消息或者用户点击查看某个用户的消息
	 * 
	 * @author zhy
	 * 
	 */
	public interface OnUnReadMessageUpdateListener
	{
		void unReadMessageUpdate(int count);
	}

	/**
	 * 存储userId-新来消息的个数
	 */
	public Map<String, Integer> mUserMessages = new HashMap<String, Integer>();
	/**
	 * 未读消息总数
	 */
	private int mUnReadedMsgs;

	private ListView mFrineds;
	private View mEmptyView;
	/**
	 * 所有的用户
	 */
	private List<User> mDatas;
	/**
	 * 适配器
	 */
	private FriendsListAdapter mAdapter;

	private PushApplication mApplication;

	private LayoutInflater mInflater;
	private SharePreferenceUtil mSpUtils;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");


		mInflater = LayoutInflater.from(getActivity());
		mApplication = (PushApplication) this.getActivity().getApplication();
		mAdapter = new FriendsListAdapter();
		mDatas = mApplication.getUserDB().getUser();
		mSpUtils = PushApplication.getInstance().getSpUtil();

		// 获取数据库中所有的用户以及未读消息个数
		mUserMessages = mApplication.getMessageDB().getUserUnReadMsgs(
				mApplication.getUserDB().getUserIds());
		for (Integer val : mUserMessages.values())
		{
			mUnReadedMsgs += val;
		}

	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.main_tab_weixin, container, false);
		mFrineds = (ListView) view.findViewById(R.id.id_listview_friends);
		mEmptyView = inflater.inflate(R.layout.no_zuo_no_die, container, false);
		mFrineds.setEmptyView(mEmptyView);
		mFrineds.setAdapter(mAdapter);

		notifyUnReadedMsg();

		mFrineds.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				String userId = mDatas.get(position).getUserId();
				if (userId.equals(mSpUtils.getUserId()))
				{
					T.showShort(getActivity(), "不能和自己聊天哈！");
					return;
				}

				if (mUserMessages.containsKey(userId))
				{
					Integer val = mUserMessages.get(userId);
					mUnReadedMsgs -= val;
					mUserMessages.remove(userId);
					mAdapter.notifyDataSetChanged();
					notifyUnReadedMsg();

				}

				Intent intent = new Intent(getActivity(),
						ChattingActivity.class);
				intent.putExtra("userId", mDatas.get(position).getUserId());
				startActivity(intent);
			}

		});
		return view;
	}

	/**
	 * 回调未读消息个数
	 */
	private void notifyUnReadedMsg()
	{
		if (getActivity() instanceof OnUnReadMessageUpdateListener)
		{
			OnUnReadMessageUpdateListener listener = (OnUnReadMessageUpdateListener) getActivity();
			listener.unReadMessageUpdate(mUnReadedMsgs);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		Log.e(TAG, "onResume");
		// 回调未读消息个数的更新
		notifyUnReadedMsg();
		// 设置新朋友的监听
		PushMessageReceiver.friendListeners.add(this);
		// 设置新消息的监听
		PushMessageReceiver.msgListeners.add(this);

		if (!PushManager.isPushEnabled(getActivity()))
			PushManager.resumeWork(getActivity());
		// 更新用户列表
		mDatas = mApplication.getUserDB().getUser();
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		Log.e(TAG, "onPause");
		/**
		 * 当onPause时，取消监听
		 */
		PushMessageReceiver.friendListeners.remove(this);
		PushMessageReceiver.msgListeners.remove(this);
	}

	/**
	 * 收到新消息时
	 */
	@Override
	public void onNewMessage(Message message)
	{
		// 如果是自己发送的，则直接返回
		if (message.getUserId() == mSpUtils.getUserId())
			return;
		// 如果该用户已经有未读消息，更新未读消息的个数，并通知更新未读消息接口，最后notifyDataSetChanged
		String userId = message.getUserId();
		if (mUserMessages.containsKey(userId))
		{
			mUserMessages.put(userId, mUserMessages.get(userId) + 1);
		} else
		{
			mUserMessages.put(userId, 1);
		}
		mUnReadedMsgs++;
		notifyUnReadedMsg();
		// 将新来的消息进行存储
		ChatMessage chatMessage = new ChatMessage(message.getMessage(), true,
				userId, message.getHeadIcon(), message.getNickname(), false,
				TimeUtil.getTime(message.getTimeSamp()));
		mApplication.getMessageDB().add(userId, chatMessage);
		// 通知listview数据改变
		mAdapter.notifyDataSetChanged();
	}

	/**
	 * 监听新朋友到来的通知
	 */
	@Override
	public void onNewFriend(User u)
	{
		Log.e(TAG, "get a new friend :" + u.getUserId() + " , " + u.getNick());
		mDatas.add(u);
		mAdapter.notifyDataSetChanged();
	}

	private class FriendsListAdapter extends BaseAdapter
	{
		@Override
		public int getCount()
		{
			return mDatas.size();
		}

		@Override
		public Object getItem(int position)
		{
			return mDatas.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			User user = mDatas.get(position);
			String userId = user.getUserId();

			ViewHolder holder = null;
			if (convertView == null)
			{
				convertView = mInflater.inflate(
						R.layout.main_tab_weixin_info_item, parent, false);
				holder = new ViewHolder();
				holder.mNickname = (TextView) convertView
						.findViewById(R.id.id_nickname);
				holder.mUserId = (TextView) convertView
						.findViewById(R.id.id_userId);
				holder.mWapper = (RelativeLayout) convertView
						.findViewById(R.id.id_item_ly);
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			// 如果存在新的消息，则设置BadgeView
			if (mUserMessages.containsKey(userId))
			{
				if (holder.mBadgeView == null)
					holder.mBadgeView = new BadgeView(mApplication);
				holder.mBadgeView.setTargetView(holder.mWapper);
				holder.mBadgeView.setBadgeGravity(Gravity.CENTER_VERTICAL
						| Gravity.RIGHT);
				holder.mBadgeView.setBadgeMargin(0, 0, 8, 0);
				holder.mBadgeView.setBadgeCount(mUserMessages.get(userId));
			} else
			{
				if (holder.mBadgeView != null)
					holder.mBadgeView.setVisibility(View.GONE);
			}

			holder.mNickname.setText(mDatas.get(position).getNick());
			holder.mUserId.setText(userId);

			return convertView;
		}

		private final class ViewHolder
		{
			TextView mNickname;
			TextView mUserId;
			RelativeLayout mWapper;
			BadgeView mBadgeView;
		}

	}

}
