package com.zhy.baiduyun.im.bean;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.zhy.baiduyun.im.PushApplication;
import com.zhy.baiduyun.im.utils.SharePreferenceUtil;

public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Expose
	private String userId;
	@Expose
	private String channelId;
	@Expose
	private String nickname;
	@Expose
	private int headIcon;
	@Expose
	private long timeSamp;
	@Expose
	private String message;
	/**
	 * 新人第一次加入时，会广播这个字段，且值为hello
	 */
	@Expose
	private String hello;
	/**
	 * 接收到新人的hello时，自动回复此字段且值为world
	 */
	@Expose
	private String world;
	


	public Message(long time_samp, String message)
	{
		super();
		SharePreferenceUtil util = PushApplication.getInstance().getSpUtil();
		this.userId = util.getUserId();
		this.channelId = util.getChannelId();
		this.nickname = util.getNick();
		this.headIcon = util.getHeadIcon();
		this.timeSamp = time_samp;
		this.message = message;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getChannelId()
	{
		return channelId;
	}

	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public int getHeadIcon()
	{
		return headIcon;
	}

	public void setHeadIcon(int headIcon)
	{
		this.headIcon = headIcon;
	}

	public long getTimeSamp()
	{
		return timeSamp;
	}

	public void setTimeSamp(long timeSamp)
	{
		this.timeSamp = timeSamp;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getHello()
	{
		return hello;
	}

	public void setHello(String hello)
	{
		this.hello = hello;
	}

	public String getWorld()
	{
		return world;
	}

	public void setWorld(String world)
	{
		this.world = world;
	}

}
