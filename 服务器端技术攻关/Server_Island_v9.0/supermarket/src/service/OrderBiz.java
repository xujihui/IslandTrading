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
		}
		Record order = new Record().set("ORDER_ID", oid)
				.set("ORDER_SITE", address)
				.set("ORDER_TIME", time_date)
				.set("OEDER_STATUS", b_status);
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
	 * 
	 * 2016-12-8修改
	 * 修改了参数
	 * */
	public boolean subfb(String id, String content, 
			String contact, String time, String status){
		int i_id = Integer.parseInt(id);
		boolean b_status = Boolean.valueOf(status).booleanValue();
		java.sql.Date time_sql = null;
		Date time_date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			time_date = sdf.parse(time);	//往数据库中插入这种格式正确
			time_sql = new java.sql.Date(time_date.getTime());	//往数据库中插入这种格式不正确
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record order = new Record().set("FB_ID", i_id).
				set("FB_CONTENT", content).
				set("FB_CONTACT", contact).
				set("FB_TIME", time_date).
				set("FB_STATUS", b_status);
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
	public boolean reg_user(String USER_NICKNAME, String USER_USERNAME, String USER_PASSWORD,
							String USER_TAKINGID, String USER_CONTACT	){
//		System.out.println("reg_user()中的参数 user:" + user + " pwd:" + pwd + " nick:" + nick);
		Record mRecord = new Record().set("USER_NICKNAME", USER_NICKNAME)
				.set("USER_USERNAME", USER_USERNAME)
				.set("USER_PASSWORD", USER_PASSWORD)
				.set("USER_TAKINGID", USER_TAKINGID)
				.set("USER_POWER", 0)
				.set("USER_CONTACT", USER_CONTACT);
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
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：通过act编辑活动详情
	 * 参数：活动表的各种参数
	 * 返回值：int
	 * */
	public int edit_act(String ACTIVITY_ID,
			String ACTIVITY_CONTENT,
			String ACTIVITY_ORGANIZER,
			String ACTIVITY_TIME,
			String ACTIVITY_SITE,
			String ACTIVITY_NAME){
		int res = 0;		
		System.out.println("-----参数++"+ACTIVITY_CONTENT+ACTIVITY_TIME);
//		int act_id = Integer.valueOf(ACTIVITY_ID);	//天呐，这里也不需要 
//			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ACTIVITY_TIME);	//往数据库里写不需要！浪费了好多时间...
		String sql = "update islandtrading_activity set ACTIVITY_CONTENT = '" + ACTIVITY_CONTENT + 
				"', ACTIVITY_ORGANIZER='" + ACTIVITY_ORGANIZER + 
				"', ACTIVITY_TIME='" + ACTIVITY_TIME + 
				"', ACTIVITY_SITE='" + ACTIVITY_SITE +
				"', ACTIVITY_NAME='" + ACTIVITY_NAME +
				"' where ACTIVITY_ID=" + ACTIVITY_ID;
		res = Db.update(sql);
		return res;
	}

	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：通过ACTIVITY_ID找到活动记录USER_ID,用USER_ID匹配APP端USER_ID
	 * 参数：ACTIVITY_ID
	 * */
	public String fetch_User_By_Act(String ACTIVITY_ID){
//		String sql = "select USER_ID from activity_user where ACTIVITY_ID='" + ACTIVITY_ID + "'";
		Record mRecord = Db.findById("activity_user", "ACTIVITY_ID", ACTIVITY_ID);
		return mRecord.getInt("USER_ID") + "";
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 实现：用户通过手机删除发布的活动
	 * 参数：ACTIVITY_ID
	 * */
	public boolean del_act(String USER_ID, String ACTIVITY_ID){
		boolean res = Db.deleteById("islandtrading_activity", "ACTIVITY_ID", ACTIVITY_ID);
		return res;
	}
	
}
