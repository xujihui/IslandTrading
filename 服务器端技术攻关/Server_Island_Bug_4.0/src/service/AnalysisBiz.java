package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class AnalysisBiz {
	/**
	 * 订单提交
	 */
	public boolean palceoreder() {
		// List<Record> orderlist = analysisService.findAll();
		// this.setSessionAttr("orderlist", orderlist);
		// this.render("/orderList.jsp");

		// 接收数据 订单号(oid)、记录条数(total)、商品条形码(pId)、商品数量(pNum)、订单总价(oSum)

		// 创建订单时间，存入到订单表（订单号、时间、总价）

		// 查询商品表，得到商品名、商品单价

		// 更新 商品详情表（商品名、商品单价、商品数量、订单号）
		return true;
	}

	/**  
	 * 
	 * 查询价格
	 * 修改日期：2016-12-8
	 * 作者：孙铖
	 * 修改项：参数由int修改为long
	 */
	public Record lookupprice(long pid){		
		//数据查询
		Record Record = Db.findById("islandtrading_product","Product_Id", pid);
		return Record;		
	}
	

	/*
	 * 实现：删除指定商品 返回值：删除结果
	 */
	public boolean deleteMyGood(String pid) {
		boolean res = Db.deleteById("islandtrading_product", "Product_Id", pid);
		return res;
	}

	/*
	 *  实现：编辑指定商品 返回值：编辑结果
	 */
	public int editMyGood(String pID, String pName, float pPrice) {

		System.out.println("update(" + pName + "," + pID + "," + ") success!");
		String sql = "UPDATE t_product SET name='" + pName + "',price=" + pPrice + " WHERE pid='" + pID + "'";
		int res = Db.update(sql);
		return res;
	}
	
	/*
	 * 实现：通过pid查询pType
	 * 作者：孙铖
	 * 日期：2016-12-8
	 * 涉及表：product_classify、islandtrading_product
	 * */
	public String getClassify(int Product_Id){
		String sql = "select Classify_Id from re_product_classify where Product_Id=" + Product_Id;
		int Classify_Id;
		String Classify_Name = null;
		Classify_Id = Db.queryInt(sql);
		String sql1 = "select Classify_Name from islandtrading_classify where Classify_Id=" + Classify_Id;
		Classify_Name = Db.queryStr(sql1);
		
		return Classify_Name;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-1-27
	 * 实现：通过pName查询商品
	 * 返回值：返回商品信息
	 * */
	public Record lookup_pName(String pName){
		//数据查询
		Record Record = Db.findById("islandtrading_product","Product_Name", pName); 
		return Record;		
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过type查询商品，达到过滤效果
	 * 返回值：返回商品信息
	 * 
	 * 修改日期：2016-12-8
	 * 
	 * */
	public List<Record> lookup_type(String pType){
		//数据查询
		System.out.println("----pType参数:" + pType);
		List<Record> list_Record = new ArrayList<>();
		String sql = "select * from islandtrading_product where Product_Id in (" +
				"select Product_Id from re_product_classify where Classify_Id=(" +
				"select Classify_Id from islandtrading_classify where Classify_Name=" + "'" +
				pType + "'" + ")" + ")";
		list_Record = Db.find(sql);
		return list_Record;		
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过act查询所有活动详细信息
	 * 参数：不需要参数
	 * 返回值：返回所有活动详情
	 * */
	public List<Record> lookup_act(){
		List<Record> list_Record = new ArrayList<>();
		list_Record = Db.find("select * from islandtrading_activity");
		return list_Record;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过user、goods查询所有收藏详细信息
	 * 参数：user goods
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * 
	 * 2016-12-8修改
	 * 返回值修改为Record
	 * */
	public Record lookup_col(String user, String goods){	//先查goods有没有在user收藏中
		System.out.println("携带有goods参数! user:"+user+"  goods:"+goods);
		String sql = "select * from re_collect_product_user where User_Id='" + 
						user + "'";
		List<Record> list = new ArrayList<>();
		list = Db.find(sql);	//得到user收藏所有商品
		System.out.println("-----list为：" + list.toString());
		int goods_id = Integer.valueOf(goods);
		int Product_Id;
		for (Record mRecord : list){
			Product_Id = mRecord.getInt("Product_Id");
			if(goods_id == Product_Id){
				System.out.println("-----lookup_col()中查询到某user的某goods，执行if");
				Record mRecord_res = Db.findById("islandtrading_product", "Product_Id", goods_id);
				return mRecord_res;
			}
		}
		
//		Record mRecord = Db.findFirst(sql);
		return null;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过user查询所有收藏商品详细信息
	 * 参数：user
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * */
	public List<Record> lookup_col(String user_id){
		System.out.println("没有goods!");
		List<Record> list_Record = new ArrayList<>();
		String sql = "select * from islandtrading_product where Product_Id in (" + 
				"select Product_Id from re_collect_product_user where User_Id=" + "'" +
				user_id + "'" + ")";
		list_Record = Db.find(sql);
		return list_Record;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 实现：完成验证登录
	 * 参数：user,取出记录即可，在AnalysisController中完成验证;
	 * 返回值：返回具体记录 或 null
	 * */
	public Record lookup_user(String user){
		Record mRecord = Db.findById("islandtrading_user", "User_Username",user);
		return mRecord;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-15
	 * 实现：验证 User_TakingId 是否存在，存在验证通过，登陆成功
	 * */
	public Record login_TK(String User_TakingId){
		Record mRecord = Db.findById("islandtrading_user", "User_TakingId", User_TakingId);
		
		return mRecord;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-15
	 * 实现：注册 User_TakingId
	 * */
	public boolean register_TK(Record mRecord){
		String User_TakingId = mRecord.getStr("User_TakingId");	//参数tkid
		String sql = "select * from islandtrading_user where User_TakingId='" + 
				User_TakingId + "'";
		List<Record> list = new ArrayList<>();
		list = Db.find(sql);
		for(Record mRec : list){
			String tkid = mRec.getStr("User_TakingId");
			if(tkid.equals(User_TakingId)){	//tkid已存在，注册失败！
				System.out.println("----register_TK（）User_TakingId" + User_TakingId + "已存在！");
				return false;
			}
		}
		boolean res = Db.save("islandtrading_user", mRecord);
		return res;
	}
	
	/* 作者：孙铖铖
	 * 实现：编辑指定商品
	 * 返回值：编辑结果
	 * 2016-12-8修改
	 * 触发方式：http://localhost:8080/supermarket/analysis/editGoods?goodsCode={Product_Id:2,PRODUCT_NAME:三星,PRODUCT_PRICE:50,PRODUCT_DESCRIBE:描述,PRODUCT_SITE:发布地点,PRODUCT_STATUS:false}
	 * */
	public int editMyGood(String Product_Id, String PRODUCT_NAME,
						String PRODUCT_PRICE,
						String PRODUCT_DESCRIBE,
						String PRODUCT_SITE, 
						String PRODUCT_STATUS){
		float price = Float.valueOf(PRODUCT_PRICE).floatValue();
		boolean b_status = Boolean.valueOf(PRODUCT_STATUS).booleanValue();
		
//		System.out.println("update("+pName+","+pID+","+ ") success!");
		String sql = "UPDATE islandtrading_product SET PRODUCT_NAME='"+PRODUCT_NAME+"',PRODUCT_PRICE="+price+ 
					", PRODUCT_DESCRIBE='" + PRODUCT_DESCRIBE  + "' ,PRODUCT_SITE='" +
					PRODUCT_SITE + "', PRODUCT_STATUS=" + b_status + " WHERE Product_Id='"+Product_Id+"'";
		int res = Db.update(sql);
		return res;
	}
	
	
	/*
	 * 实现：添加商品,只添加商品属性
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 涉及表：islandtrading_product
	 * */
	public boolean add_Goods(Record mRecord){
		
		boolean res = Db.save("islandtrading_product", mRecord);	
		return res;
	}
	
	/*
	 * 添加商品发布者
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 涉及表：product_user
	 * 先找出pid，再找出userid
	 * */
	public boolean add_Goods_User(String pName, String userName){
		Record mRecord = Db.findById("islandtrading_product", "Product_Name", pName);
		int Product_Id = mRecord.getInt("Product_Id");	//找到 Product_Id
		Record mRecord_user = Db.findById("islandtrading_user", "User_Username", userName);
		if(mRecord_user == null){
			System.out.println("-----表islandtrading_user不存在此user：" + userName);
			return false;
		}
		int USER_ID = mRecord_user.getInt("User_Id"); 
		Record myRecord = new Record().set("User_Id", USER_ID)
									.set("Product_Id", Product_Id);
		boolean res = Db.save("re_product_user", myRecord);
		return res;
	}
	
	/*
	 * 添加图片下载地址
	 * 作者：孙铖铖
	 * 日期：2016-12-15
	 * 参数：存入本地服务器的文件名称
	 * */
	public boolean add_img_url(int Product_Id, String img_Name){
		String sql = "update islandtrading_product set Product_Image='" + 
				img_Name  + "' where Product_Id=" + Product_Id;
		Record mRecord = new Record()
				.set("Product_Id", Product_Id)
				.set("Product_Image_Url", "http://192.168.194.2:8080/IslandTrading/analysis/downloadImg?Product_Id=" + Product_Id);
		Db.update("islandtrading_product", "Product_Id",mRecord);
		
		int i = Db.update(sql);
		if(i == 1){
			return true;
		}
		return false;
	}
	
	/*
	 * 通过Product_Id得到商品图片名称
	 * 作者：孙铖铖
	 * 日期：2016-12-15
	 * */
	public String getImg(int pid){
		Record mRecord = Db.findById("islandtrading_product", "Product_Id", pid);
		String Product_Image = mRecord.getStr("Product_Image");
		System.out.println("-----getImg()找到的图片名：" + Product_Image);
		
		return Product_Image;
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


	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-20
	 * 获取推荐商品
	 * */
	public List<Record> getTop(){
		List<Record> list = new ArrayList<>();
		String sql = "select * from islandtrading_product where Product_Top=" + 1;
		list = Db.find(sql);
		System.out.println("----推荐商品：" + list.size() +"  " + list.toString());
		return list;
	}
	

	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-20
	 * 商品发布时，设置 re_product_classify 关系
	 * */
	public boolean setType(String pType, String Product_Name){
		String sql = "select * from islandtrading_product where Product_Name='" + 
				Product_Name + "'";
		List<Record> list = new ArrayList<>();
		list = Db.find(sql);
		for(Record mRecord : list){
			if(mRecord.get("Product_Type") == null){
//				int Classify_Id = Db.find("select Classify_Id from islandtrading_classify where Classify_Name='" + 
//						pType + "'");
				Record record = Db.findById("islandtrading_classify", "Classify_Name", pType);
				int Classify_Id = record.getInt("Classify_Id");		//得到商品类型id
				int pid = mRecord.getInt("Product_Id");		//得到商品id
				System.out.println("-----没有type参数的pid：" + pid);
				Db.update("update islandtrading_product set Product_Type='" + pType + "' where Product_Id=" + pid);
				Record type_record = new Record().set("Product_Id", pid)
									.set("Classify_Id", Classify_Id);
				Db.save("re_product_classify", type_record);
			}
		}
		
		return false;
	}
	
}
