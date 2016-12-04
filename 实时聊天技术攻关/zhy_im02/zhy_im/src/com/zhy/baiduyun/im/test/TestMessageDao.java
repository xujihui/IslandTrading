package com.zhy.baiduyun.im.test;

import com.zhy.baiduyun.im.bean.ChatMessage;
import com.zhy.baiduyun.im.dao.MessageDB;

import android.test.AndroidTestCase;

public class TestMessageDao extends AndroidTestCase
{

	public void testAdd()
	{
		ChatMessage chatMessage = new ChatMessage("helloworld", false,
				"101201212", 0, "ºèÑó", true, "2013-09-09 12:12:12");
		MessageDB messageDB = new MessageDB(getContext());
		messageDB.add("12121212test", chatMessage);
	}

}
