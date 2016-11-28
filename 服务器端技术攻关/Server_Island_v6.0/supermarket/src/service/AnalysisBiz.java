package service;

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
