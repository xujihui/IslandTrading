package service;

import java.util.List;

/** 
 * 活动处理业务类
 * 刘鑫
 */

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ActivityBiz {
	/**
	 * 增加活动
	 */
	public boolean save(String pID, String pName, String pContent, String pOrganizer, String pTime, String pSite) {
		Record pro = new Record().set("id", pID).set("name", pName).set("content", pContent)
				.set("organizer", pOrganizer).set("time", pTime).set("site", pSite);
		boolean res = Db.save("activity_b", pro);
		return res;
	}

	/**
	 * 删除活动
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("activity_b", "id", pid);
		return res;
	}

	/**
	 * 修改活动信息
	 */
	public int update(String pID, String pName, String pContent, String pOrganizer, String pTime, String pSite) {
		String sql = "UPDATE activity_b SET" + " id='" + pID + "'," + "name='" + pName + "'," + "content='" + pContent
				+ "'," + "organizer='" + pOrganizer + "'," + "time='" + pTime + "'," + "site='" + pSite + "',"
				+ " WHERE id='" + pID + "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from activity_b");
		return pros;
	}

	/**
	 * 查找商品
	 */
	public Record findByID(String pid) {
		Record rec = Db.findById("activity_b", "id", pid);
		return rec;
	}

}
