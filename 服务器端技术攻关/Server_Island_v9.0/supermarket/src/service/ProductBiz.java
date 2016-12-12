package service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/** 
 * @author 孙丽萍
 * @version 1.0
 * 2016-8-17
 * 商品处理业务类
 */
public class ProductBiz {
	/**  
	 * 增加商品
	 * 参数：商品ID、商品名、商品价格
	 * 返回值：true或者false
	 */
	public boolean save(String pID,String pName,float pPrice){
		Record pro = new Record().set("PRODUCT_ID", pID).set("PRODUCT_NAME", pName).set("PRODUCT_PRICE", pPrice);
		boolean res = Db.save("islandtrading_product", pro);
		return res;
	}
	/**  
	 * 删除商品
	 * 参数：商品ID
	 * 返回值：true或者false
	 */
	public boolean deleteByID(String pid){
		boolean res = Db.deleteById("islandtrading_product","PRODUCT_ID", pid);
		return res;
	}
	/**  
	 * 修改商品信息
	 * 参数：商品ID、商品名、商品价格
	 * 返回值：修改成功的记录数
	 */
	public int update(String pID,String pName,float pPrice){
		System.out.println("update("+pName+","+pID+","+ ") success!");
		String sql = "UPDATE islandtrading_product SET PRODUCT_NAME='"+pName+"',PRODUCT_PRICE="+pPrice+" WHERE PRODUCT_ID='"+pID+"'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll(){
		List<Record> pros = Db.find("select * from islandtrading_product");
		return pros;
	}
	/**  
	 * 查找商品
	 * 参数：商品ID
	 * 返回值：返回记录
	 */
	public Record findByID(String pid){
		Record rec = Db.findById("islandtrading_product","PRODUCT_ID", pid);
		return rec;
	}
}
