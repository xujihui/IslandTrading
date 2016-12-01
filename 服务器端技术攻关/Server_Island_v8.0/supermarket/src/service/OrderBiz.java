package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	
	/*
	 * 保存订单信息
	 * 作者：孙铖铖	原save函数被覆盖了，用的话重新复制一个
	 * 日期：2016-11-29
	 * 参数：一堆
	 * */
	public boolean save(String oid, double osum, String address,
			String telphone, String user_id, String pid){
		Record order = new Record().set("oid", oid)
				.set("osum", osum)
				.set("address", address)
				.set("telphone", telphone)
				.set("user_id",user_id)
				.set("pid", pid);
		boolean res = false;
		try {
			res = Db.save("order", order);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("订单保存失败！！！");
			e.printStackTrace();
		}
		return res;
	}
	
	//批量保存订单详情记录
	public int[] batchsave(List<Record> recordList,int batchSize){
		int[] res = Db.batchSave("t_detail", recordList, batchSize);
		return res;
	}
	
	/*
	 * 保存用户的反馈信息
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 参数：
	 * 返回值：boolean 反馈提交结果
	 * 运行注释：获取中文注意转码！！！
	 * */
	public boolean subfb(String str_id, String str_user_id, 
			String str_content, String str_time, boolean b_status){	
		java.sql.Date time_sql = null;
		Date time_date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			time_date = sdf.parse(str_time);
			time_sql = new java.sql.Date(time_date.getTime());
//			System.out.println("subfb():"+ str_time +"\n"+time_date.toString() + "\n  " + time_sql.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record order = new Record().set("id", str_id).
				set("user_id", str_user_id).
				set("content", str_content).
				set("time", time_date).
				set("status", b_status);
		System.out.println("组成的order------"+order.toString());
		boolean res = false;
		try {
			res = Db.save("feedback", order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("----OrderBiz.java中我遇到了异常！！！");
		}
		return res;
	}

	/*
	 * 注册用户信息
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 参数：user、pwd
	 * 返回值：Boolean 注册结果
	 * App端注释：power默认为0，最低权限；
	 * 			参数需要较多，好像和验证账户互不干扰，网址后边加个 参数 nick就好了
	 * 运行注释：注意中文转码
	 * */
	public boolean reg_user(String user, String pwd, String nick){
		System.out.println("reg_user()中的参数 user:" + user + " pwd:" + pwd + " nick:" + nick);
		Record mRecord = new Record().set("username", user)
				.set("password", pwd)
				.set("nickname", nick)
				.set("power", 0);
		boolean res = false;
		try {
			res = Db.save("user", mRecord);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("注册失败喽！");
			e.printStackTrace();
		}
		return res;
	}

}
