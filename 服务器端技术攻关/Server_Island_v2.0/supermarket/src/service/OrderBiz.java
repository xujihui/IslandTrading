package service;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/** 
 * @author 孙丽萍
 * @version 1.0
 * 2016-8-18 
 * 订单处理业务类
 */
public class OrderBiz {
	
	/**  
	 * 查找订单
	 * 返回值：返回所有订单
	 */
	public List<Record> findAll(){
		List<Record> orders = Db.find("select * from t_order order by date desc");
		return orders;
	}
	/**  
	 * 查找订单
	 * 参数：订单号
	 * 返回值：返回订单
	 */
	public Record findByID(String oid){
		Record rec = Db.findById("t_order","oid", oid);
		return rec;
	}
	
	/**  
	 * 查找订单详情
	 * 参数：订单号
	 * 返回值：返回订单详细信息
	 */
	public List<Record> findDetailByID(String oid){
		String sql="select * from t_detail where oid='"+oid+"'";		
		List<Record> orders = Db.find(sql);
		return orders;
	}
	
	//保存订单

