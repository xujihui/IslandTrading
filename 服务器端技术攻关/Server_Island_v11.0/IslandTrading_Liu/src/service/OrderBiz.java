/**
 * Function:OrderBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class OrderBiz {
	/**
	 * 查找订单
	 */
	public List<Record> findAll() {
		List<Record> orders = Db.find("select * from islandtrading_order order by Order_Id desc");
		return orders;
	}

	/**
	 * 查找订单
	 */
	public Record findByID(String oid) {
		Record rec = Db.findById("islandtrading_order", "Order_Id", oid);
		return rec;
	}

	/**
	 * 查找订单详情
	 */
	public List<Record> findDetailByID(String oid) {
		String sql = "select * from islandtrading_order where Order_Id='" + oid + "'";
		List<Record> orders = Db.find(sql);
		return orders;
	}

	// 保存订单
	public boolean save(String oID, Date time, String status, String site) {
		Record order = new Record().set("Order_Id", oID).set("Order_Time", time).set("Order_Status", status)
				.set("Order_Site", site);
		boolean res = Db.save("islandtrading_order", order);
		return res;
	}

	// 批量保存订单详情记录
	public int[] batchsave(List<Record> recordList, int batchSize) {
		int[] res = Db.batchSave("islandtrading_order", recordList, batchSize);
		return res;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：通过act编辑活动详情
	 * 参数：活动表的各种参数
	 * 返回值：int
	 * */
	public int edit_act(String Activity_Id,
			String ACTIVITY_CONTENT,
			String ACTIVITY_ORGANIZER,
			String ACTIVITY_TIME,
			String ACTIVITY_SITE,
			String ACTIVITY_NAME){
		int res = 0;		
		System.out.println("-----参数++"+ACTIVITY_CONTENT+ACTIVITY_TIME);
//		int act_id = Integer.valueOf(Activity_Id);	//天呐，这里也不需要 
//			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ACTIVITY_TIME);	//往数据库里写不需要！浪费了好多时间...
		String sql = "update islandtrading_activity set ACTIVITY_CONTENT = '" + ACTIVITY_CONTENT + 
				"', ACTIVITY_ORGANIZER='" + ACTIVITY_ORGANIZER + 
				"', ACTIVITY_TIME='" + ACTIVITY_TIME + 
				"', ACTIVITY_SITE='" + ACTIVITY_SITE +
				"', ACTIVITY_NAME='" + ACTIVITY_NAME +
				"' where Activity_Id=" + Activity_Id;
		res = Db.update(sql);
		return res;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：通过Activity_Id找到活动记录USER_ID,用USER_ID匹配APP端USER_ID
	 * 参数：Activity_Id
	 * */
	public String fetch_User_By_Act(String Activity_Id){
//		String sql = "select USER_ID from activity_user where Activity_Id='" + Activity_Id + "'";
		int activity_id = Integer.valueOf(Activity_Id).intValue();
		Record mRecord = Db.findById("re_activity_user", "Activity_Id", activity_id);
		System.out.println("----fetch_User_By_Act()得到的 USER_ID：" + mRecord.getInt("User_Id"));
		return mRecord.getInt("User_Id") + "";
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：用户通过手机删除发布的活动
	 * 参数：Activity_Id
	 * */
	public boolean del_act(String USER_ID, String Activity_Id){
		boolean res = Db.deleteById("islandtrading_activity", "Activity_Id", Activity_Id);
		return res;
	}
	
	/*
	 * 保存用户的反馈信息
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 参数：
	 * 返回值：boolean 反馈提交结果
	 * 运行注释：获取中文注意转码！！！
	 * 
	 * 2016-12-8修改
	 * 修改了参数
	 * */
	public boolean subfb( String content, 
			String contact, String time){
//		int i_id = Integer.parseInt(id);
//		boolean b_status = Boolean.valueOf(status).booleanValue();
		java.sql.Date time_sql = null;
		Date time_date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			time_date = sdf.parse(time);	//往数据库中插入这种格式正确
			time_sql = new java.sql.Date(time_date.getTime());	//往数据库中插入这种格式不正确
		} catch (ParseException | java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record order = new Record().
				set("Fb_Content", content).
				set("Fb_Contact", contact).
				set("Fb_Time", time_date).
				set("Fb_Status", false);
		System.out.println("组成的order------"+order.toString());
		boolean res = false;
		try {
			res = Db.save("islandtrading_feedback", order);
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
	 * 
	 * 2016-12-8修改
	 * 用户级别暂定 不需要携带参数，默认为最低级别0，管理员通过后台修改
	 * 触发方式：
	 * 
	 * 
	 * */
	public boolean reg_user(String User_Nickname, String User_Username, String User_Password,
							String User_TakingId, String User_Tel, String Hx_Username,String Hx_Password){
//		System.out.println("reg_user()中的参数 user:" + user + " pwd:" + pwd + " nick:" + nick);
		
		//注册的时候用户名重复的时候提示已存在
		Record mRecord_user = Db.findById("islandtrading_user", "User_Username", User_Username);
		if(mRecord_user != null){
			System.out.println("----注册失败，用户名已存在！");
			return false;
		}
		
		Record mRecord = new Record().set("User_Nickname", User_Nickname)
				.set("User_Username", User_Username)
				.set("User_Password", User_Password)
				.set("User_TakingId", User_TakingId)
				.set("User_Power", 0)
				.set("User_Tel", User_Tel)
				.set("Hx_Username", Hx_Username)
				.set("Hx_Password", Hx_Password);
		boolean res = false;
		try {
			res = Db.save("islandtrading_user", mRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("注册失败喽！");
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * 保存订单信息
	 * 作者：孙铖铖	原save函数已覆盖
	 * 日期：2016-11-29
	 * 参数：一堆
	 * */
	public boolean save(String oid, String address,
					String time, String status){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time_date = null;
		boolean b_status = Boolean.valueOf(status).booleanValue();
		try {
			time_date = sdf.parse(time);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record order = new Record().set("Order_Id", oid)
				.set("Order_Site", address)
				.set("Order_Time", time_date)
				.set("Order_Status", b_status);
		boolean res = false;
		try {
			res = Db.save("islandtrading_order", order);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("订单保存失败！！！");
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-17
	 * 实现：通过User_Id得到其发布的商品 ，返回该User_Id所有发布的商品
	 * */
	public List<Record> myRel(String User_Id){
		String sql = "select * from re_product_user where User_Id=" + 
				User_Id + "";
		List<Record> list_temp = new ArrayList<>();
		List<Record> list = new ArrayList<>();
		list_temp = Db.find(sql);
		for(Record mRecord : list_temp){
			int Product_Id = mRecord.getInt("Product_Id");
			Record myRecord = Db.findById("islandtrading_product", "Product_Id", Product_Id);
			list.add(myRecord);
		}
		System.out.println("-----我的发布list:" + list.toString());
		return list;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-17
	 * 实现：通过User_Id得到卖出的商品，通过Product_Status得到卖出的商品
	 * */
	public List<Record> mySell(int User_Id){
		
		String sql = "select * from islandtrading_product where Product_Id in(" + 
					"select Product_Id from re_product_user where User_Id =" + 
					User_Id + ")";
		List<Record> list = new ArrayList<>();
		List<Record> list_sell = new ArrayList<>();
		list = Db.find(sql);
		for(Record mRecord : list){
			if(mRecord.getBoolean("Product_Status")){
				System.out.println("----卖出的mRecord：" + mRecord.toString());
				list_sell.add(mRecord);
			}
		}
		System.out.println("----卖出的list_sell：" + list_sell.toString());
		return list_sell;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-17
	 * 实现：通过User_Id得到订单信息，从订单中获取买到的商品
	 * */
	public List<Record> myBuy(int User_Id){
		String sql = "select * from islandtrading_product where Product_Id in(" + 
					"select Product_Id from re_user_order_product where User_Id=" + 
					User_Id + ")";
		List<Record> list = new ArrayList<>();
		list = Db.find(sql);
		return list;
		
		
	}
}
