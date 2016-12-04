package service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 分类处理业务类 刘鑫
 */
public class ClassifyBiz {
	/**
	 * 增加分类
	 */
	public boolean save(String pId, String pType, String pImage) {
		Record pro = new Record().set("id", pId).set("type", pType).set("image", pImage);
		boolean res = Db.save("classify_b", pro);
		return res;
	}

	/**
	 * 删除分类
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("classify_b", "id", pid);
		return res;
	}

	/**
	 * 修改分类信息
	 */
	public int update(String pId, String pType, String pImage) {
		String sql = "UPDATE classify_b SET" + " id='" + pId + "type='" + pType + "'," + "image='" + pImage
				+ " WHERE id='" + pId + "'";
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
