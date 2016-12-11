/**
 * Function:ActivityBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.util.Date;
import java.util.List;

/** 
 * 活动处理业务类
 */

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ActivityBiz {
	/**
	 * 增加活动
	 */
	public boolean save(String pID, String pName, String pContent, String pOrganizer, Date pTime, String pSite) {
		Record pro = new Record().set("Activity_Id", pID).set("Activity_Name", pName).set("Activity_Content", pContent)
				.set("Activity_Organizer", pOrganizer).set("Activity_Time", pTime).set("ACTIVITY_SITE", pSite);
		boolean res = Db.save("islandtrading_activity", pro);
		return res;
	}

	/**
	 * 删除活动
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("islandtrading", "Activity_Id", pid);
		return res;
	}

	/**
	 * 修改活动
	 */
	public int update(String pID, String pName, String pContent, String pOrganizer, Date pTime, String pSite) {
		String sql = "UPDATE islandtrading_activity SET" + " Activity_Id='" + pID + "'," + "Activity_Name='" + pName
				+ "'," + "Activity_Content='" + pContent + "'," + "Activity_Organizer='" + pOrganizer + "',"
				+ "Activity_Time='" + pTime + "'," + "ACTIVITY_SITE='" + pSite + "'," + " WHERE Activity_Id='" + pID
				+ "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_activity");
		return pros;
	}

	/**
	 * 删除活动
	 */
	public Record findByID(String id) {
		Record rec = Db.findById("islandtrading_activity", "Activity_Id", id);
		return rec;
	}

}
