package service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/** 
 * @author 孙丽萍
 * @version 1.0
 * 2016-8-19 
 * 客户端请求处理业务类
 */
public class AnalysisBiz {
	/**
	 * 订单提交
	 */
	public boolean palceoreder() {
//		List<Record> orderlist = analysisService.findAll();
//		this.setSessionAttr("orderlist", orderlist);
//		this.render("/orderList.jsp");
		
		//接收数据 订单号(oid)、记录条数(total)、商品条形码(pId)、商品数量(pNum)、订单总价(oSum)

		//创建订单时间，存入到订单表（订单号、时间、总价）
		
		//查询商品表，得到商品名、商品单价
		
		//更新 商品详情表（商品名、商品单价、商品数量、订单号）
		return true;
	}

	/**  
	 * 订单查询
	 * 参数：订单号
	 * 返回值：返回价格
	 * 
	 * 修改日期：2016-12-8
	 * 作者：孙铖
	 * 修改项：参数由int修改为long
	 */
	public Record lookupprice(long pid){		
		//数据查询
		Record Record = Db.findById("islandtrading_product","PRODUCT_ID", pid);
		return Record;		
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-1-27
	 * 实现：通过pName查询商品
	 * 返回值：返回商品信息
	 * */
	public Record lookup_pName(String pName){
		//数据查询
		Record Record = Db.findById("islandtrading_product","PRODUCT_NAME", pName); 
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
		String sql = "select * from islandtrading_product where PRODUCT_ID in (" +
				"select PRODUCT_ID from product_classify where CLASSIFY_ID=(" +
				"select CLASSIFY_ID from islandtrading_classify where CLASSIFY_NAME=" + "'" +
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
	 * 实现：通过user、goods查询所有活动详细信息
	 * 参数：user goods
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * 
	 * 2016-12-8修改
	 * 返回值修改为Record
	 * */
	public Record lookup_col(String user, String goods){
		System.out.println("携带有goods参数! user:"+user+"  goods:"+goods);
			
		int i_user = Integer.valueOf(user);
		int i_goods = Integer.valueOf(goods);
		String sql = "select * from islandtrading_product where PRODUCT_ID=" + "'" + goods + "'";
//		String sql = "select * from islandtrading_product where PRODUCT_ID=(" + 
//				"select PRODUCT_ID from collect_product_user where USER_ID='" + user + 
//				" ' and PRODUCT_ID=" + "'" + goods + "')";  
				
		Record mRecord = Db.findFirst(sql);
		return mRecord;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过user查询所有活动详细信息
	 * 参数：user
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * */
	public List<Record> lookup_col(String user_id){
		System.out.println("没有goods!");
		List<Record> list_Record = new ArrayList<>();
		String sql = "select * from islandtrading_product where PRODUCT_ID in (" + 
				"select PRODUCT_ID from collect_product_user where USER_ID=" + "'" +
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
		Record mRecord = Db.findById("islandtrading_user", "USER_USERNAME",user);
		return mRecord;
	}
	
	
	
	/*
	 * 作者：孙铖铖
	 * 实现：删除指定商品
	 * 返回值：删除结果
	 * */
	public boolean deleteMyGood(String pid){
		int i_pid = Integer.valueOf(pid).intValue();
		boolean res = Db.deleteById("islandtrading_product","PRODUCT_ID", i_pid);
		return res;
	}
	/* 作者：孙铖铖
	 * 实现：编辑指定商品
	 * 返回值：编辑结果
	 * 2016-12-8修改
	 * 触发方式：http://localhost:8080/supermarket/analysis/editGoods?goodsCode={PRODUCT_ID:2,PRODUCT_NAME:三星,PRODUCT_PRICE:50,PRODUCT_DESCRIBE:描述,PRODUCT_SITE:发布地点,PRODUCT_STATUS:false}
	 * */
	public int editMyGood(String PRODUCT_ID, String PRODUCT_NAME,
						String PRODUCT_PRICE,
						String PRODUCT_DESCRIBE,
						String PRODUCT_SITE, 
						String PRODUCT_STATUS){
		float price = Float.valueOf(PRODUCT_PRICE).floatValue();
		boolean b_status = Boolean.valueOf(PRODUCT_STATUS).booleanValue();
		
//		System.out.println("update("+pName+","+pID+","+ ") success!");
		String sql = "UPDATE islandtrading_product SET PRODUCT_NAME='"+PRODUCT_NAME+"',PRODUCT_PRICE="+price+ 
					", PRODUCT_DESCRIBE='" + PRODUCT_DESCRIBE  + "' ,PRODUCT_SITE='" +
					PRODUCT_SITE + "', PRODUCT_STATUS=" + b_status + " WHERE PRODUCT_ID='"+PRODUCT_ID+"'";
		int res = Db.update(sql);
		return res;
	}
	
	/*
	 * 实现：图片上传完毕时将图片的下载地址写入到数据库中
	 * */
	public boolean addimg(String downloadUrl, String pid){
		String sql = "UPDATE islandtrading_product SET img='" + downloadUrl + "' where pid='" + pid + "'";
		int res = Db.update(sql);
		if(res == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * 实现：通过pid查询pType
	 * 作者：孙铖
	 * 日期：2016-12-8
	 * 涉及表：product_classify、islandtrading_product
	 * */
	public String getClassify(long PRODUCT_ID){
		String sql = "select CLASSIFY_ID from product_classify where PRODUCT_ID=" + PRODUCT_ID;
		int CLASSIFY_ID;
		String CLASSIFY_NAME = null;
		CLASSIFY_ID = Db.queryInt(sql);
		String sql1 = "select CLASSIFY_NAME from islandtrading_classify where CLASSIFY_ID=" + CLASSIFY_ID;
		CLASSIFY_NAME = Db.queryStr(sql1);
		
		return CLASSIFY_NAME;
	}
}
