package com.zhy.baiduyun.im;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.zhy.baiduyun.im.utils.SharePreferenceUtil;

public class AppStart extends Activity
{

	private PushApplication mApplication;
	private SharePreferenceUtil mPreferenceUtil;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_start);

		mApplication = (PushApplication) getApplication();
		mPreferenceUtil = mApplication.getSpUtil();

		mHandler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{

				String userId = mPreferenceUtil.getUserId();
				if (TextUtils.isEmpty(userId))//ÊÇ·ñµÇÂ½¹ý
				{
					Intent intent = new Intent(AppStart.this,
							LoginActivity.class);
					startActivity(intent);

				} else
				{
					Intent intent = new Intent(AppStart.this,
							MainActivity.class);
					startActivity(intent);
				}
				AppStart.this.finish();
			}
		},2000);

	}

}
