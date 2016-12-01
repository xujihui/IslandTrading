package service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
	 */
	public Record lookupprice(String pid){		
		//数据查询
		Record Record = Db.findById("t_product","pid", pid);
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
		Record Record = Db.findById("t_product","name", pName); 
		return Record;		
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过type查询商品，达到过滤效果
	 * 返回值：返回商品信息
	 * */
	public List<Record> lookup_type(String pType){
		//数据查询
		System.out.println("----pType参数:" + pType);
		List<Record> list_Record = new ArrayList<>();
		list_Record = Db.find("select * from " + "t_product" + " where type = '" + pType+ "'");	//单引号，好长时间才解决
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
		list_Record = Db.find("select * from " + "activities");
		return list_Record;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过user、goods查询所有活动详细信息
	 * 参数：user goods
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * */
	public List<Record> lookup_col(String user, String goods){
		System.out.println("携带有goods参数! user:"+user+"  goods:"+goods);
		List<Record> list_Record = new ArrayList<>();
		int i_user = Integer.valueOf(user);
		int i_goods = Integer.valueOf(goods);
		list_Record = Db.find("select * from " + "collection " + "where id_goods="
		+ i_goods + " and " + "id_user=" + i_user);
		System.out.println("sql语句:----"+"select * from " + "collection " + "where id_goods="
		+ i_goods + " and " + "id_user=" + i_user);
		return list_Record;
	}
	
	/*
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 实现：通过user查询所有活动详细信息
	 * 参数：user
	 * 返回值：返回具体某人收藏的id_goods为**的一件商品，因为有具体商品id
	 * */
	public List<Record> lookup_col(String user){
		System.out.println("没有goods!");
		List<Record> list_Record = new ArrayList<>();
		list_Record = Db.find("select * from " + "collection " + "where id_user=" + "'" + user + "'");
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
		Record mRecord = Db.findById("user", "username",user);
		return mRecord;
	}
	
	
	
	/*
	 * 作者：孙铖铖
	 * 实现：删除指定商品
	 * 返回值：删除结果
	 * */
	public boolean deleteMyGood(String pid){
		boolean res = Db.deleteById("t_product","pid", pid);
		return res;
	}
	/* 作者：孙铖铖
	 * 实现：编辑指定商品
	 * 返回值：编辑结果
	 * */
	public int editMyGood(String pID,String pName,float pPrice){
		
		System.out.println("update("+pName+","+pID+","+ ") success!");
		String sql = "UPDATE t_product SET name='"+pName+"',price="+pPrice+" WHERE pid='"+pID+"'";
		int res = Db.update(sql);
		return res;
	}
}
