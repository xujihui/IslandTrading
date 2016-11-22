package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import service.AnalysisBiz;
import service.OrderBiz;
import service.ProductBiz;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class AnalysisController extends Controller {
	AnalysisBiz analysisService = this.enhance(AnalysisBiz.class);
	OrderBiz orderService = this.enhance(OrderBiz.class);
	ProductBiz productService = this.enhance(ProductBiz.class);

	/**
	 * 价格查询
	 */
	public void lookupprice() {

		String jsonContent = this.getPara("goodsCode"); //goodsCode 和 pid 是一个东西
		//String jsonContent = "{pid:123458}";	//告诉你参数是什么样子，下面的接口中应该也一样
		System.out.println("这是什么"+jsonContent);
		String pid = "123457";	//默认pid，应该是为了防止获取不到goodsCode设置的
		// 解析json数据
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonContent);
			pid = jsonObject.getString("pId");	//得到商品pid
			System.out.println("这是什么pid"+pid);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} //得到json串
	
		System.out.println("lookup price pId:" + pid);

		// 查找记录
		Record rec = analysisService.lookupprice(pid);

		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
		float price;
		String name;

		// 返回结果 1 存在，0 不存在
		String res = "ok";

		if (rec != null) {
			// 获取商品字段信息
			price = rec.getFloat("price");
			//price=Float.parseFloat(new java.text.DecimalFormat("#.00").format(rec.getFloat("price")));
			name = rec.getStr("name");
			res = "ok";

		} else {
			price = 0;
			name = null;
			res = "no";
		}

		// 组装Json串
		try {

			// {"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}
			System.out.println("name"+name);
			content.put("pId", pid);
			content.put("pName", name);
			content.put("pPrice", price);

			// {"res":"1","content":{"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}}
			json.put("res", res);
			json.put("content", content);
			good.put("good", json);

			System.out.println("ret:" + good.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 响应
		try {
			HttpServletResponse response = this.getResponse();
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(good.toString());
			writer.flush();
			writer.close();
			// this.renderJson(jsonText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.renderNull();
	}

	/**
	 * 订单提交
	 */
	public void palceoreder() {
		// 接收数据 订单号(oid)、记录条数(total)、商品条形码(pId)、商品数量(pNum)、订单总价(oSum)
		// 创建订单时间，存入到订单表（订单号、时间、总价）
		// 查询商品表，得到商品名、商品单价
		// 更新 商品详情表（商品名、商品单价、商品数量、订单号）
		/*
		 * { “oId”:”00001”, “total”:3,”record”:[ {“pId”:”050043”,” pNum”:”1” },
		 * {“pId”:”050044”,” pNum”:”2” }, {“pId”:”050045”,” pNum”:”1”
		 * }],”oSum”:”30.5”}
		 */
		String jsonContent = this.getPara("checkout");
//		String jsonContent = "{ oId:A20160816001, total:3,record:["
//				+ "{pId:123462,pNum:1},"
//				+ "{pId:123460,pNum:2},"
//				+ "{pId:123461,pNum:1}]," + "oSum:30.5}";
		String oid = "A20160816001";
		float osum = 0;
		int total = 0;
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat oidformat=new SimpleDateFormat("yyMMddHHmmss");
		String time=format.format(date);
		oid = oidformat.format(date);
		System.out.println("p2:"+time);
		System.out.println("oid:"+oid);
		try {
			// 解析json数据,获取订单号，订单中记录数，订单总价
			JSONObject jsonObject = new JSONObject(jsonContent);

			//oid = jsonObject.getString("oId");
			total = Integer.parseInt(jsonObject.getString("total"));
			osum = Float.parseFloat(jsonObject.getString("oSum"));

			System.out.println("oid:" + oid + "total:" + total + "osum:" + osum);

			// 保存订单记录
			orderService.save(oid, time, osum);

			JSONArray jsonrecos = jsonObject.getJSONArray("record");
			JSONObject jsonreco = null;
			List<Record> recordList = new ArrayList<Record>();
			

			// 批量添加 商品名、商品价格、数量、所属订单
			String pID;
			String pName;
			float pPrice;
			String pNum;

			// 解析订单中记录
			for (int i = 0; i < total; i++) {
				
				jsonreco = jsonrecos.getJSONObject(i);

				// 根据商品号，获得商品名称及单价
				pID = jsonreco.getString("pId");
				System.out.println("pID:"+pID);
				
				Record product = productService.findByID(pID);

				// 获取商品信息
				pName = product.getStr("name");
				pPrice = product.getFloat("price");
				pNum = jsonreco.getString("pNum");

				System.out.println("name:" + pName + "price:" + pPrice
						+ "pNum:" + pNum + "oid:" + oid);

				// 将商品信息封装成记录
				Record record = new Record().set("name", pName).set("price", pPrice)
						.set("number", pNum).set("oid", oid);
				System.out.println("name:" + i+":"+record.getStr("name"));
				recordList.add(i, record);
			}
			
			for (Record record2 : recordList) {
				System.out.println("name:" + record2.getStr("name"));
			}

			// 批量存储订单详细信息记录
			int[] res = orderService.batchsave(recordList, total);
			JSONObject resjson = new JSONObject();
			JSONObject order = new JSONObject();
			if (res.length > 0) {
				resjson.put("res", "ok");
			} else {
				resjson.put("res", "no");
			}
			order.put("order", resjson);
			// 响应
			try {
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/json;charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(order.toString());
				writer.flush();
				writer.close();
				// this.renderJson(jsonText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.renderNull();
	}
	
	/*
	 * 作者：孙铖
	 * 删除商品
	 * */
	public void deleteById(){
		String jsonContent = this.getPara("goodsCode");//形如"{pid:123458}"
		String pId = "123459";
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonContent);
			pId = jsonObject.getString("pId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean delete_result = analysisService.deleteMyGood(pId);
		if(delete_result == true){
			System.out.println("deleteById孙铖铖"+"删除成功！");
			this.renderHtml("删除成功！" + pId);
		}
		else{
			System.out.println("deleteById孙铖铖"+"删除失败！");
			this.renderHtml("删除失败！" + pId);
		}		
	}
	/*
	 * 作者：孙铖
	 * 编辑商品
	 * */
	public void editGoods(){
		
		//注意此处关键字!!!!!!!
		String jsonContent = this.getPara("goods");
		
		String pId = new String();
		String pName = new String();
		double pPrice = 0;
		JSONObject jsonObject_goods;
		
		try {
			jsonObject_goods = new JSONObject(jsonContent);
			pId = jsonObject_goods.getString("pId");
			pName = jsonObject_goods.getString("pName");
			pName = new String(pName.getBytes("iso-8859-1"),"GBK");
			pPrice = jsonObject_goods.getDouble("pPrice"); //记得转换成float格式！ 
			System.out.println("解析editGoods结果----"+pId + "," + pName + "," + pPrice);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		int edit_result = analysisService.editMyGood(pId, pName, (float)pPrice);
		if(edit_result == 1){
			System.out.println("编辑成功！"+pId + "," + pName + "," + pPrice);
			this.renderHtml("编辑成功！");
		}
		else{
			System.out.println("编辑失败！"+pId + "," + pName + "," + pPrice);
			this.renderHtml("编辑失败！");
		}
	}
	
}
