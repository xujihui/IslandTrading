/**
 * Function:CollectBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class CollectBiz {
	/**
	 * 修改收藏
	 */
	public int update(String pId, String pStatus, String pTime) {
		String sql = "UPDATE islandtrading_collect SET" + " Collect_Id='" + pId + "'," + "Collect_Status='" + pStatus
				+ "'," + "Collect_Time='" + pTime +"'"+ " WHERE Collect_Id='" + pId + "'";
		System.out.println(sql);
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_collect");
		return pros;
	}

	/**
	 * 查找收藏
	 */
	public Record findByID(String pid) {
		Record rec = Db.findById("islandtrading_collect", "Collect_Id", pid);
		return rec;
	}

}
