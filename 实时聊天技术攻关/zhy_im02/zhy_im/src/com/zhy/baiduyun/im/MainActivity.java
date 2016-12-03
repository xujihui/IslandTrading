package com.zhy.baiduyun.im;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.jauker.widget.BadgeView;
import com.zhy.baiduyun.im.MainTabFriends.OnUnReadMessageUpdateListener;
import com.zhy.baiduyun.im.client.PushMessageReceiver;
import com.zhy.baiduyun.im.server.BaiduPush;

public class MainActivity extends FragmentActivity implements
		OnUnReadMessageUpdateListener
{
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	/**
	 * 顶部三个LinearLayout
	 */
	private LinearLayout mTabLiaotian;

	/**
	 * 顶部的三个TextView
	 */
	private TextView mLiaotian;
	private TextView mFaxian;
	private TextView mTongxunlu;

	/**
	 * 分别为每个TabIndicator创建一个BadgeView
	 */
	private BadgeView mBadgeViewforLiaotian;

	/**
	 * Fragment
	 */
	private MainTabFriends mFrined;

	/**
	 * Tab的那个引导线
	 */
	private ImageView mTabLine;
	/**
	 * ViewPager的当前选中页
	 */
	private int currentIndex;
	/**
	 * 屏幕的宽度
	 */
	private int screenWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initTabLine();
		/**
		 * 初始化Adapter
		 */
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);

		/**
		 * 设置监听
		 */
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				// 重置所有TextView的字体颜色
				resetTextView();
				switch (position)
				{
				case 0:

					mLiaotian.setTextColor(getResources().getColor(
							R.color.green));
					break;
				case 1:
					mFaxian.setTextColor(getResources().getColor(R.color.green));
					break;
				case 2:
					mTongxunlu.setTextColor(getResources().getColor(
							R.color.green));
					break;
				}

				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
						.getLayoutParams();
				/**
				 * 利用position和currentIndex判断用户的操作是哪一页往哪一页滑动
				 * 然后改变根据positionOffset动态改变TabLine的leftMargin
				 */
				if (currentIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));

				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));

				} else if (currentIndex == 1 && position == 1) // 1->2
				{

					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 3) + currentIndex
							* (screenWidth / 3));
				}
				mTabLine.setLayoutParams(lp);

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		});

		mViewPager.setCurrentItem(0);

		

	}

	/**
	 * 根据屏幕的宽度，初始化引导线的宽度
	 */
	private void initTabLine()
	{
		mTabLine = (ImageView) findViewById(R.id.id_tab_line);
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
				.getLayoutParams();
		lp.width = screenWidth / 3;
		mTabLine.setLayoutParams(lp);
	}

	/**
	 * 重置颜色
	 */
	protected void resetTextView()
	{
		mLiaotian.setTextColor(getResources().getColor(R.color.black));
		mFaxian.setTextColor(getResources().getColor(R.color.black));
		mTongxunlu.setTextColor(getResources().getColor(R.color.black));
	}

	/**
	 * 初始化控件，初始化Fragment
	 */
	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		mTabLiaotian = (LinearLayout) findViewById(R.id.id_tab_liaotian_ly);

		mLiaotian = (TextView) findViewById(R.id.id_liaotian);
		mFaxian = (TextView) findViewById(R.id.id_faxian);
		mTongxunlu = (TextView) findViewById(R.id.id_tongxunlu);

		mFrined = new MainTabFriends();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		mFragments.add(mFrined);
		mFragments.add(tab02);
		mFragments.add(tab03);

		mBadgeViewforLiaotian = new BadgeView(this);
		mTabLiaotian.addView(mBadgeViewforLiaotian);
		mBadgeViewforLiaotian.setVisibility(View.GONE);
	}

	/**
	 * update modified
	 */
	@Override
	protected void onResume()
	{
		super.onResume();

		// 点击通知来到主界面
		if (PushMessageReceiver.mNewNum > 0)
		{
			// 清除通知
			PushApplication.getInstance().getNotificationManager()
					.cancel(PushMessageReceiver.NOTIFY_ID);
			PushMessageReceiver.mNewNum = 0;
		}
	}

	/**
	 * update modified
	 */
	@Override
	public void unReadMessageUpdate(int count)
	{
		if (count == 0 && mBadgeViewforLiaotian.getBadgeCount() == 0)
			mBadgeViewforLiaotian.setVisibility(View.GONE);
		mBadgeViewforLiaotian.setVisibility(View.VISIBLE);
		mBadgeViewforLiaotian.setBadgeCount(count);

	}

}
