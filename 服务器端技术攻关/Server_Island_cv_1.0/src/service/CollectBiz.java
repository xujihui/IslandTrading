package service;

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
	public int update(String pProduct_id, String pUser_id, String pStatus, String pDate) {
		String sql = "UPDATE collect_b SET" + " product_id='" + pProduct_id + "'," + "user_id='" + pUser_id + "',"
				+ "status='" + pStatus + "'," + "date='" + pDate + "'," + " WHERE id='" + pProduct_id + "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from collect_b");
		return pros;
	}

	/**
	 * 查找商品
	 */
	public Record findByID(String pProduct_id) {
		Record rec = Db.findById("collect_b", "product_id", pProduct_id);
		return rec;
	}

}
