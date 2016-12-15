package service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

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
	public String getClassify(long Product_Id){
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
//		System.out.println("----传参   pName:" + pName + "  userName:" + userName);
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
	public boolean add_img_url(String img_Name){
		
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
	
}
