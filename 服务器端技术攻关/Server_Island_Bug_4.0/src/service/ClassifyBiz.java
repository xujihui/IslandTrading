/**
 * Function:ClassifyBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ClassifyBiz {
	/**
	 * 增加分类
	 */
	public boolean save(String pName, String pImage) {
		Record pro = new Record().set("Classify_Name", pName).set("Classify_Image", pImage);
		boolean res = Db.save("islandtrading_classify", pro);
		return res;
	}

	/**
	 * 删除分类
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("islandtrading_classify", "Classify_Id", pid);
		return res;
	}

	/**
	 * 修改分类
	 */
	public int update(String pId, String pName, String pImage) {
		String sql = "UPDATE islandtrading_classify SET" 
	             + " Classify_Id='" + pId+ "'," 
	             + "Classify_Name='" + pName + "',"
				 + "Classify_Image='" + pImage +"'"+ 
				 " WHERE Classify_Id='" + pId + "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_classify");
		return pros;
	}

	/**
	 * 查找分类
	 */
	public Record findByID(String pid) {
		Record rec = Db.findById("islandtrading_classify", "Classify_Id", pid);
		return rec;
	}
}
