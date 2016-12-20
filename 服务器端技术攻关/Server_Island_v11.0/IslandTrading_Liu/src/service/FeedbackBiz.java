/**
 * Function:FeedbackBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.util.List;

/** 
 * 反馈处理业务类
 * 刘鑫
 */

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class FeedbackBiz {
	/**
	 * 删除反馈
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("islandtrading_feedback", "Fb_Id", pid);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_feedback");
		return pros;
	}

	/**
	 * 查找反馈
	 */
	public Record findByID(String pid) {
		Record rec = Db.findById("islandtrading_feedback", "Fb_Id", pid);
		return rec;
	}

}
