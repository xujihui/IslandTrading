package service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/** 
 * 商品处理业务类
 */
public class ProductBiz {
	/**  
	 * 增加商品
	 */
	public boolean save(String pId,String pName,float pPrice,String pDescribe
			,String pImage,String pClassify_Id,String pSite,String pTime ){
		Record pro = new Record().set("id", pId).set("name", pName).set("price", pPrice).
				set("t_describe",pDescribe).set("image",pImage).set("classify_id",pClassify_Id)
				.set("site", pSite).set("time",pTime);
		boolean res = Db.save("product_b", pro);
		return res;
	}
	/**  
	 * 删除商品
	 */
	public boolean deleteByID(String pid){
		boolean res = Db.deleteById("product_b","id", pid);
		return res;
	}
	/**  
	 * 修改商品信息
	 */
	public int update(String pId,String pName,String pPrice,String pDescribe
			,String pImage,String pClassify_Id,String pSite,String pTime){
		String sql = "UPDATE product_b SET"+ 
		" name='"+pName+"'," + 
		"price='"+pPrice+"',"+
		"t_describe='"+pDescribe+"',"+
		"image='"+pImage+"',"+
	    "classify_id='"+pClassify_Id+"',"+
		"site='"+pSite+"',"+
		"time='"+pTime +"'"+
		" WHERE id='"+pId+"'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll(){
		List<Record> pros = Db.find("select * from product_b");
		return pros;
	}
	/**  
	 * 查找商品
	 */
	public Record findByID(String pid){
		Record rec = Db.findById("product_b","id", pid);
		return rec;
	}
}
