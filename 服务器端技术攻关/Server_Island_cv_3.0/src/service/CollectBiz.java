package service;

import java.util.Date;
import java.util.List;

/** 
 * 活动处理业务类
 * 刘鑫
 */

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CollectBiz {

	/**
	 * 修改活动信息
	 */
	public int update(String pId, String pStatus, Date pTime) {
		String sql = "UPDATE islandtrading_collect SET" + " Collect_Id='" + pId + "'," + "Collect_Status='" + pStatus + "',"
				+ "Collect_Time='" + pTime + " WHERE Collect_Id='" + pTime + "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_collect");
		return pros;
	}

	/**
	 * 查找商品
	 */
	public Record findByID(String pid) {
		Record rec = Db.findById("islandtrading_collect", "Collect_Id", pid);
		return rec;
	}

}
