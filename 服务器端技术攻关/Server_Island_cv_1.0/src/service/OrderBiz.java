package service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/** 
 * 订单处理业务类
 */
public class OrderBiz {
	
	/**  
	 * 查找订单
	 * 返回值：返回所有订单
	 */
	public List<Record> findAll(){
		//List<Record> orders = Db.find("select * from order_b order by id desc");
		List<Record> orders = Db.find("select * from order_b order by id desc");
		return orders;
	}
	/**  
	 * 查找订单
	 * 参数：订单号
	 * 返回值：返回订单
	 */
	public Record findByID(String oid){
		Record rec = Db.findById("order_b","id", oid);
		return rec;
	}
	
	/**  
	 * 查找订单详情
	 * 参数：订单号
	 * 返回值：返回订单详细信息
	 */
	public List<Record> findDetailByID(String oid){
		String sql="select * from order_b where id='"+oid+"'";		
		List<Record> orders = Db.find(sql);
		return orders;
	}
	
	//保存订单
	public boolean save(String oID,String time,float total){
		Record order = new Record().set("id", oID).set("date", time).set("total", total);
		boolean res = Db.save("order_b", order);
		return res;
	}
	
	//批量保存订单详情记录
	public int[] batchsave(List<Record> recordList,int batchSize){
		int[] res = Db.batchSave("order_b", recordList, batchSize);
		return res;
	}
}
