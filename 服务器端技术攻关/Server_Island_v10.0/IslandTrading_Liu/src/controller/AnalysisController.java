/**
 * Function:AnalysisController
 * Date:2016.12.11
 * Author:SunCheng
 */
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
import com.jfinal.upload.UploadFile;

public class AnalysisController extends Controller {
	AnalysisBiz analysisService = this.enhance(AnalysisBiz.class);
	OrderBiz orderService = this.enhance(OrderBiz.class);
	ProductBiz productService = this.enhance(ProductBiz.class);

		/**
	 * 
	 * 2016-11-27  商品查询
	 * 输入pId或pName，获得商品的详细信息
	 * 触发方式：http://localhost:8080/supermarket/analysis/lookupprice?pName={pName:华为}
	 * 
	 * App端注释：   App端主要进行json请求、解析
	 * 			App发送请求中携带json串参数，key为 "pId"或者"pName"；
	 * 			App以pId或pName为key值进行搜索
	 * 			pId默认为123456，此时视为App没有携带pId参数，另外，后台设计要避开此pId；
	 * 			pName默认为”Default“,此时视为App没有携带pName参数；
	 * 
	 * 运行注释：	App发送请求中携带json串参数，key为 "pId"或者"pName"；
	 * 			pId默认为123456，此时视为App没有携带pId参数，另外，后台设计要避开此pId；
	 * 			pName默认为”Default“,此时视为App没有携带pName参数；
	 * 			jsonContent_pId、jsonContent_pName是通过参数得到的商品详细信息；(二者只取其一)
	 * 			通过jsonContent_pId 或 jsonContent_pName新建JSONObject，其中有一个在数据库中有对应即可查询出结果；
	 * 			通过 res 关键字检索查询结果，"ok"为查询到商品，"no"为没有查询到指定商品
	 * 			一定注意 中文 问题！！！只有涉及到网址中的中文!!!
	 * 
	 * 2016-12-8修改
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/lookupprice?pId={Product_Id:3}
	 * 		http://localhost:8080/IslandTrading/analysis/lookupprice?pName={Product_Name:Apple}
	 * @throws UnsupportedEncodingException 
	 */
	public void lookupprice() throws UnsupportedEncodingException {
		
		String jsonContent_pId = this.getPara("pId");	//拿到参数
		String jsonContent_pName = this.getPara("pName");	//拿到参数
		String jsonContent = jsonContent_pId == null?jsonContent_pName:jsonContent_pId;	//优先查询pid
		System.out.println("-----00得到的参数：" + jsonContent_pId + "  " + jsonContent_pName);
		long pid = 0;	//默认pid，应该是为了防止获取不到pId设置的
		String pName = "Default";	//默认pName，为了防止获取不到pName设置的
		String res = "ok";	//标记，查询成功；解析时可以以此key为查询结果，ok为存在相应商品，no为不存在
		float price;
		String str_des;
		String str_type;  //需要中间表查询
		
		//新增属性
		String str_site;
		int hit = 0;
		int favour = 0;
		boolean status = false;
		boolean top = false;
		double Product_Lagitude;
		double Product_Longgitude;
		
		Date date_time;
		String str_time;
		
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
		
		HttpServletResponse response = this.getResponse();	
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		if(jsonContent_pId != null){
			System.out.println("携带的pId："+jsonContent_pId + "  pName：" + jsonContent_pName);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent);
				pid = jsonObject.getInt("Product_Id");	//得到商品pid
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("lookup price Product_Id:" + pid + "  lookup pname Product_Name:" + pName);
			Record rec_pId = analysisService.lookupprice(pid);
			
			//如果没有指定商品，停止执行
			if(rec_pId == null){
				res = "no";
				System.out.println("-----没有指定商品 lookupprice（） Product_Id:" + pid);
				try {
					content.put("res", res);
					content.put("tip", "没有指定商品   Product_Id:" + pid);
					good.put("PRODUCT", content);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				writer.write("没有指定商品   Product_Id:" + pid);
				writer.write(good.toString());
				this.renderNull();	
				return;
			}
			
			
			// 获取商品字段信息
			price = rec_pId.getFloat("Product_Price");
			//price=Float.parseFloat(new java.text.DecimalFormat("#.00").format(rec.getFloat("price")));
			pName = rec_pId.getStr("Product_Name");
			str_des = rec_pId.getStr("Product_Describe");
			str_type = analysisService.getClassify(pid);	
			str_site = rec_pId.getStr("Product_Site");
			date_time = rec_pId.getDate("Product_Time");
			str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date_time);
			hit = rec_pId.getInt("Product_View");	
			favour = rec_pId.getInt("Product_Positive");	
			status = rec_pId.getBoolean("Product_Status");
			top = rec_pId.getBoolean("Product_Top");
			Product_Lagitude = rec_pId.getDouble("Product_Lagitude");
			Product_Longgitude = rec_pId.getDouble("Product_Longgitude");
		}
		else{	//只携带了pName
			System.out.println("携带的pId："+jsonContent_pId + "  pName：" + jsonContent_pName);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent);
				String pName_temp = jsonObject.getString("Product_Name");	//得到商品pName
				pName = new String(pName_temp.getBytes("iso-8859-1"),"UTF-8");
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("lookup pId:" + pid + "  lookup pName:" + pName);
			Record rec_pName = analysisService.lookup_pName(pName);
			
			//如果没有指定商品，停止执行
			if(rec_pName == null){
				res = "no";
				System.out.println("-----没有指定商品 lookupprice（） pName:" + pName);
				writer.write("没有指定商品 PRODUCT_NEME:" + pName);
				this.renderNull();	
				return;
			}
			// 获取商品字段信息
			price = rec_pName.getFloat("Product_Price");
			pid = rec_pName.getInt("Product_Id");
			str_des = rec_pName.getStr("Product_Describe");
			str_type = analysisService.getClassify(pid);
			str_site = rec_pName.getStr("Product_Site");
			date_time = rec_pName.getDate("Product_Time");
			str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date_time);
			
			str_site = rec_pName.getStr("Product_Site");
			hit = rec_pName.getInt("Product_View");
			favour = rec_pName.getInt("Product_Positive");
			status = rec_pName.getBoolean("Product_Status");
			top = rec_pName.getBoolean("Product_Top");
			Product_Lagitude = rec_pName.getDouble("Product_Lagitude");
			Product_Longgitude = rec_pName.getDouble("Product_Longgitude");
		}
		// 组装Json串
		try {

			// {"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}
			System.out.println("name"+pName);
			
			content.put("Product_Id", pid);
			content.put("Product_Name", pName);
			content.put("Product_Price", price);
			content.put("Product_Describe", str_des);
			content.put("Classify_Name", str_type);
			content.put("Product_Site", str_site);
			content.put("Product_Time", str_time);
			content.put("Product_Lagitude", Product_Lagitude);
			content.put("Product_Longgitude", Product_Longgitude);
			
			//新增加属性
			content.put("Product_Positive", favour);
			content.put("Product_View", hit);
			content.put("Product_Status", status);
			content.put("Product_Top", top);
			content.put("PRODUCT_TYPE", str_type);
			

			// {"res":"1","content":{"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}}
			json.put("res", res);
			json.put("content", content);
			good.put("PRODUCT", json);

			System.out.println("----拼接的PRODUCT:" + good.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		writer.write(good.toString());
		writer.flush();
		writer.close();
		// this.renderJson(jsonText);
		this.renderNull();
	}

	/*
	 * 分类筛选
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 触发方式：http://localhost:8080/supermarket/analysis/type_collection?pType={pType:电脑}
	 * App端注释：点击分类图片，获取分类关键字，查询出相同类型的所有商品；
	 * 运行注释：注意，网址涉及到中文！！！只处理网址中文即可；
	 * 		 注意修改AnalysisBiz里调用的方法；
	 * 		 目前只能得到一条商品信息，通过循环表达到过滤类别功能！
	 * 
	 * 2016-12-8修改,
	 * bug发现：分类筛选将会产生多个json，需要用json数组来承载  []
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/type_collection?pType={pType:电脑PC}
	 * 返回值：JSONArray
	 * */
	public void type_collection(){
		String jsonContent = this.getPara("pType");	//得到携带的参数pType
		String str_type = "default";	//设置默认类型
		String res = "ok";	//默认查询结果
		
		//用于存储获得某商品的详细信息
		long pid = 0;
		String pName = null;
		float price = 0;
		String str_des = null;
		String str_site = null;
		Date date_time;
		String str_time = null;
		
		//新增属性
		int hit = 0;
		int favour = 0;
		boolean status = false;
		boolean top = false;
		double Product_Lagitude;
		double Product_Longgitude;
		
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
		JSONArray goods = new JSONArray();
		System.out.println("----得到的jsonContent:"+jsonContent.toString());
		if(jsonContent != null){
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent);
				String str_temp = jsonObject.getString("pType");
				str_type = new String(str_temp.getBytes("iso-8859-1"),"UTF-8");
				System.out.println("----中文否 type:"+str_type);	//成功得到中文
				List<Record> list_Record_type = new ArrayList<>();
				list_Record_type = analysisService.lookup_type(str_type);
				
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				if(list_Record_type.size() == 0){
					res = "no";
					System.out.println("type_collection()------此类别下商品为空！"+str_type);
					writer.write(str_type + "此类别没有商品！");
					this.renderNull();
				}
//				int num = 0;
				//循环查找表
				for(Record rec_pType: list_Record_type){
					content = null;
					json = null;
					good = null;
					content = new JSONObject();
					json = new JSONObject();
					good = new JSONObject();
					//获取商品字段信息
					pid = rec_pType.getInt("Product_Id");	
					pName = rec_pType.getStr("Product_Name");
					price = rec_pType.getFloat("Product_Price");
					str_des = rec_pType.getStr("Product_Describe");
					str_site = rec_pType.getStr("Product_Site");
					date_time = rec_pType.getDate("Product_Time");
					str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date_time);
					
					hit = rec_pType.getInt("Product_View");
					favour = rec_pType.getInt("Product_Positive");
					status = rec_pType.getBoolean("Product_Status");
					top = rec_pType.getBoolean("Product_Top");
					Product_Lagitude = rec_pType.getDouble("Product_Lagitude");
					Product_Longgitude = rec_pType.getDouble("Product_Longgitude");
					//组装json串
					try {
						System.out.println("name"+pName);
						content.put("Product_Id", pid);
						content.put("Product_Name", pName);
						content.put("Product_Price", price);
						content.put("Product_Describe", str_des);
						content.put("Classify_Name", str_type);
						content.put("Product_Site", str_site);
						content.put("Product_Time", str_time);
						
						content.put("Product_View", hit);
						content.put("Product_Positive", favour);
						content.put("Product_Status", status);
						content.put("Product_Top", top);
						content.put("Product_Lagitude", Product_Lagitude);
						content.put("Product_Lagitude", Product_Lagitude);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);
						goods.put(good);
						
						System.out.println("----good串:" + good.toString());
						
//						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//for
				writer.write(goods.toString());
				writer.flush();
				writer.close();
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
			
				
		}//if(jsonContent != null)
	}
	
	/*
	 * 请求活动列表
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 触发方式：localhost:8080/supermarket/analysis/request_acts
	 * 参数：不需要参数
	 * 实现：App请求获取所有活动
	 * 
	 * 2016-12-8修改
	 * 触发方式：localhost:8080/IslandTrading/analysis/request_acts
	 * 
	 * */
	public void request_acts(){
		String res = "ok";	//默认查询结果
		
		//用于存储获得的活动详细信息
		long id;
		String str_name;
		String str_content;
		String str_organizer;
		Date date_temp;
		String str_time;
		String str_site;
		
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
		JSONArray goods = new JSONArray();
			try {
				List<Record> list_Record_act = new ArrayList<>();
				list_Record_act = analysisService.lookup_act();
				
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();			
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				if(list_Record_act.size() == 0){
					res = "no";
					System.out.println("request_acts()------暂时没有活动！");
					writer.write("暂时没有活动！");
					this.renderNull();
				}
				int num = 0;
				//循环查找表
				for(Record rec_pType: list_Record_act){
					content = null;
					json = null;
					good = null;
					content = new JSONObject();
					json = new JSONObject();
					good = new JSONObject();
					System.out.println("-----范围for："+rec_pType.toString());
					//获取商品字段信息
					id = rec_pType.getInt("Activity_Id");
					str_name = rec_pType.getStr("Activity_Name");
					str_content = rec_pType.getStr("Activity_Content");
					str_organizer = rec_pType.getStr("Activity_Organizer");
					date_temp = rec_pType.getDate("Activity_Time");
					str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date_temp);
					str_site = rec_pType.getStr("Activity_Site");
					
					//组装json串
					try {
						System.out.println("name"+str_name);
						content.put("Activity_Id", id);
						content.put("Activity_Name", str_name);
						content.put("Activity_Content", str_content);
						content.put("Activity_Organizer", str_organizer);
						content.put("Activity_Site", str_site);
						content.put("Activity_Time", str_time);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);
						goods.put(num++,good);
						System.out.println("----good串:" + good.toString());
						
//						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//for
				writer.write(goods.toString());
				writer.flush();
				writer.close();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		
		
	}
	
	/*
	 * 编辑活动
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 参数：act 
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/editActs?act={Activity_Id:1,Activity_Content:活动内容修改,Activity_Organizer:活动组织修改,Activity_Time:'2016-12-12 12:12:12',Activity_Site:活动地点修改,Activity_Name:活动名修改}
	 * 参数：
	 * */
	public void editActs(){
		String jsonContent = this.getPara("act");
		System.out.println("----act获得:"+jsonContent);
		
//		//存储要更改的数据
		String Activity_Id;
		String Activity_Content;
		String Activity_Organizer;
		String Activity_Time;
		String Activity_Site;
		String Activity_Name;
		
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			Activity_Id = jsonObject.getString("Activity_Id");
			Activity_Content = new String((jsonObject.getString("Activity_Content")).getBytes("iso-8859-1"),"UTF-8");
			Activity_Organizer = new String((jsonObject.getString("Activity_Organizer")).getBytes("iso-8859-1"),"UTF-8");
			Activity_Time = jsonObject.getString("Activity_Time");
			Activity_Site = new String((jsonObject.getString("Activity_Site")).getBytes("iso-8859-1"),"UTF-8");
			Activity_Name = new String((jsonObject.getString("Activity_Name")).getBytes("iso-8859-1"),"UTF-8");
			
//			System.out.println("获得的参数中文么：-----"+Activity_Content + "  " + 
//					Activity_Organizer + " " + Activity_Time + " " + 
//					Activity_Site);
			
			int res = orderService.edit_act(Activity_Id, Activity_Content, Activity_Organizer, 
					Activity_Time, Activity_Site, Activity_Name);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			if(res != 0){	//编辑活动成功
				writer.write("活动编辑成功！Activity_Id：" + Activity_Id);
				writer.flush();
				writer.close();
				this.renderNull();
				
			}
			else{
				writer.write("活动编辑失败！Activity_Id：" + Activity_Id);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * 删除活动（用户操作）
	 * 作者：孙铖铖
	 * 日期：2016-12-11
	 * 注释：APP端拼接请求接口，APP端获取活动id，肯定可以；
	 * 		判断活动是不是此用户发布的
	 * 参数：
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/deleteAct?Activity_Id=1&User_Id=1
	 * */
	public void deleteAct(){
		String Activity_Id = this.getPara("Activity_Id");
		String User_Id = this.getPara("User_Id");
		System.out.println("-----deleteAct参数：Activity_Id:" + Activity_Id + "  User_Id:" + User_Id);
		String User_Id_fetch = orderService.fetch_User_By_Act(Activity_Id);	//找到此Activity_Id的发布者
		System.out.println("-----User_Id_fetch:" + User_Id_fetch);
		
		HttpServletResponse response = this.getResponse();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(User_Id.equals(User_Id_fetch) ){	//此activity发布者和APP登陆者userid相同
			boolean res = orderService.del_act(User_Id, Activity_Id);
			if(res == true){
				writer.write("删除成功! Activity_Id：" + Activity_Id);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{
				writer.write("删除失败! Activity_Id：" + Activity_Id);
				writer.flush();
				writer.close();
				this.renderNull();
			}
		}//if(User_Id == User_Id)
		else{	//APP登陆者不能删除他人发布的活动，好像也走不到这个分支……
			writer.write("不能删除别人的活动! Activity_Id：" + Activity_Id);
			writer.flush();
			writer.close();
			this.renderNull();
		}	
	}
	
	/*
	 * 请求我的收藏
	 * 作者：孙铖铖
	 * 日期：2016-11-28
	 * 触发方式：http://localhost:8080/supermarket/analysis/request_col?user={user:20161130}
	 * App端注释：携带id_user和id_goods两个id，方能获取准确商品
	 * 			或者只携带id_user，达到获取某人收藏的效果
	 * 运行注释：两个参数确定准确商品；user参数过滤商品；
	 * 		 需要考虑携带的参数数量，如何表示？？？user必带，所以，先判断goods_id有无
	 * 		在网页上显示提示信息，通过 writer.write("  ");
	 * 		this.renderNull();通知jfinal跳转到哪个页面，这句话应该是不跳转；
	 * 		Sql中通过bit存储java中的boolean值，可直接  getboolean(coloum)
	 * 
	 * 2016-12-8修改
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/request_col?User_Id={User_Id:20161130}
	 * 		http://localhost:8080/IslandTrading/analysis/request_col?User_Id={User_Id:20161130}&goods_id={goods_id:2}
	 * 		
	 * */
	public void request_col(){
		String jsonContent_goods = this.getPara("goods_id");	
		String jsonContent_user = this.getPara("User_Id");
		String goods_id = "default";	//得到goods_id
		String User_Id = null;
		
		//存储商品信息；
		long Product_Id = 0;
		String Product_Name = null;
		float Product_Price = 0;
		String Product_Describe = null;
		Date time_temp;
		String Product_Time = null;
		String Product_Site = null;
		int Product_View = 0;
		int Product_Positive = 0;
		boolean Product_Status = false;
		boolean Product_Top = false;
		double Product_Lagitude = 0;
		double Product_Longgitude = 0;
		
		JSONObject json = null;
		JSONObject content = null;
		JSONObject good = null;
		JSONArray goods = new JSONArray();
		if(jsonContent_goods != null){
		try {
			JSONObject jsonObject_temp = new JSONObject(jsonContent_goods);
			goods_id = jsonObject_temp.getString("goods_id");
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		}
		//如果没有携带goods_id
		if(goods_id.equals("default")){	//没有携带goods_id，只处理user参数
//			res = "ok";
			System.out.println("----执行if：");
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent_user);
				User_Id = jsonObject.getString("User_Id");
				List<Record> list_Record_Col = new ArrayList<>();
				list_Record_Col = analysisService.lookup_col(User_Id);
								
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();			
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				//如果用户名非法，提示 没有收藏任何商品
				if(list_Record_Col.size() == 0){
					writer.write("用户："+User_Id+" 没有收藏任何商品!");
					this.renderNull();
					return;
				}
				//循环查找表
				for(Record rec_pType: list_Record_Col){
					json = new JSONObject();
					content = new JSONObject();
					good = new JSONObject();
					//获取商品字段信息
					Product_Id = rec_pType.getInt("Product_Id");
					Product_Name = rec_pType.getStr("Product_Name");
					Product_Price = rec_pType.getFloat("Product_Price");
					Product_Describe = rec_pType.getStr("Product_Describe");
					Product_Time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(rec_pType.getDate("Product_Time"));
					Product_Site = rec_pType.getStr("Product_Site");
					Product_View = rec_pType.getInt("Product_View");
					Product_Positive = rec_pType.getInt("Product_Positive");
					Product_Status = rec_pType.getBoolean("Product_Status");
					Product_Top = rec_pType.getBoolean("Product_Top");
					Product_Lagitude = rec_pType.getDouble("Product_Lagitude");
					Product_Longgitude = rec_pType.getDouble("Product_Longgitude");
				
					//组装json串
					try {
						content.put("Product_Id", Product_Id);
						content.put("Product_Name", Product_Name);	
						content.put("Product_Price", Product_Price);
						content.put("Product_Describe", Product_Describe);
						content.put("Product_Time", Product_Time);
						content.put("Product_Site", Product_Site);
						content.put("Product_View", Product_View);
						content.put("Product_Positive", Product_Positive);
						content.put("Product_Status", Product_Status);
						content.put("Product_Top", Product_Top);
						content.put("Product_Lagitude", Product_Lagitude);
						content.put("Product_Longgitude", Product_Longgitude);
						
						json.put("content", content);
						good.put("good", json);
						goods.put(good);
						
						System.out.println("----good串:" + good.toString());
						
//						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("-----捕获到异常   e1 !!!");
						e.printStackTrace();
					}
				}//for
				writer.write(goods.toString());
				writer.flush();
				writer.close();
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e2 !!!");
				e2.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e3 !!!");
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e4 !!!");
				e1.printStackTrace();
			} 
		}	//结束if 只携带一个user参数
		else{	//携带了goods_id，即商品id，处理两个参数
			content = new JSONObject();
			json = new JSONObject();
			good = new JSONObject();
			
			System.out.println("----执行else：");
			JSONObject jsonObject_goods;
			JSONObject jsonObject_user;
			try {
				System.out.println("----else中得到的参数：jsonContent_user" + 
						jsonContent_user.toString() + "  jsonObject_goods:" + 
						jsonContent_goods.toString());
				jsonObject_user = new JSONObject(jsonContent_user);
				jsonObject_goods = new JSONObject(jsonContent_goods);
				User_Id = jsonObject_user.getString("User_Id");
				goods_id = jsonObject_goods.getString("goods_id");
				System.out.println("-----user:"+User_Id+"  goods:"+goods_id);
				Record rec_pType = analysisService.lookup_col(User_Id, goods_id);
//				System.out.println(rec_pType.toString()+"-----");	//list里没东西
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();				
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				//如果用户名非法，提示 没有收藏任何商品
				if(rec_pType == null){
					System.out.println("-----没有匹配数据 request_col if分支.");
					writer.write("商品已不存在或者请求商品id非法！");
					this.renderNull();
					return;
				}
				else{
				//循环查找表
//				for(Record rec_pType: list_Record_type){
					//获取商品字段信息
					Product_Id = rec_pType.getInt("Product_Id");
					Product_Name = rec_pType.getStr("Product_Name");
					Product_Price = rec_pType.getFloat("Product_Price");
					Product_Describe = rec_pType.getStr("Product_Describe");
					Product_Time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(rec_pType.getDate("Product_Time"));
					Product_Site = rec_pType.getStr("Product_Site");
					Product_View = rec_pType.getInt("Product_View");
					Product_Positive = rec_pType.getInt("Product_Positive");
					Product_Status = rec_pType.getBoolean("Product_Status");
					Product_Top = rec_pType.getBoolean("Product_Top");
					Product_Lagitude = rec_pType.getDouble("Product_Lagitude");
					Product_Longgitude = rec_pType.getDouble("Product_Longgitude");
				}	
					
					//组装json串
					try {
						content.put("Product_Id", Product_Id);
						content.put("Product_Name", Product_Name);	
						content.put("Product_Price", Product_Price);
						content.put("Product_Describe", Product_Describe);
						content.put("Product_Time", Product_Time);
						content.put("Product_Site", Product_Site);
						content.put("Product_View", Product_View);
						content.put("Product_Positive", Product_Positive);
						content.put("Product_Status", Product_Status);
						content.put("Product_Top", Product_Top);
						content.put("Product_Lagitude", Product_Lagitude);
						content.put("Product_Longgitude", Product_Longgitude);
						
						json.put("content", content);
						good.put("good", json);
						goods.put(good);
						System.out.println("----good串:" + good.toString());
						
						writer.write(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("-----捕获到异常   e1 !!!");
						e.printStackTrace();
					}
//				}//for
//				writer.write(goods.toString());
				writer.flush();
				writer.close();
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e2 !!!");
				e2.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e3 !!!");
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("-----捕获到异常   e4 !!!");
				e1.printStackTrace();
			} 
		}//else
		

	}
	
	/*
	 * 提交反馈表
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * App端注释：获取用户填写的信息；
	 * 		         需要规范上传的日期格式；
	 * 触发方式：http://localhost:8080/supermarket/analysis/submit_fb?checkout={"id":"1","User_Id":"2","content":"你好","time":"2016-11-25 11:13:01"}
	 * 运行注释：携带参数 checkout 
	 * 
	 * 2016-12-8修改
	 * 修改：关键字改为  fb;status为false或true，数据库对应存储为0或1
	 * 注意：提交参数  时间  必须有 “” 双引号
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/submit_fb?fb={"FB_CONTENT":"中文可以","FB_CONTACT":"1523015666","FB_TIME":"2016-12-9 20:20:39"}
	 * 
	 * */
	public void submit_fb(){
		String jsonContent = this.getPara("fb");	
		
		String FB_ID;
		String FB_CONTENT;
		String FB_CONTACT;
		String FB_TIME;
//		String FB_STATUS;	//提交的反馈肯定都是未解决状态，所以不携带状态了
		
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
//			FB_ID = jsonObject.getString("FB_ID");
			FB_CONTENT = new String((jsonObject.getString("FB_CONTENT")).getBytes("iso-8859-1"),"UTF-8");
			FB_CONTACT = jsonObject.getString("FB_CONTACT");
			FB_TIME = jsonObject.getString("FB_TIME");	
//			FB_STATUS = jsonObject.getString("FB_STATUS");
			
			boolean b = orderService.subfb( FB_CONTENT, FB_CONTACT, FB_TIME);
			System.out.println("-----submit_fb()获得的参数jsonContent:" + jsonContent+"\n转换后的效果 content:" + jsonContent);
			
			if(b == true){
//				System.out.println("反馈提交成功：id" + FB_ID);
				this.renderHtml("反馈提交成功！");
				return;
			}
			else{
//				System.out.println("反馈提交失败：id" + FB_ID);
//				this.renderHtml("反馈提交失败！id：" + FB_ID);
				return;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("-----submit_fb() 捕获到异常e1");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * 完成用户注册、登陆等请求
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 触发方式：http://localhost:8080/supermarket/analysis/reg_log_user?mode=check&User_Username=孙铖铖&User_Password=123abc
	 * App端注释：	App端携带mode参数，验证登陆check  还是 注册register
	 * 			注册时，App端能获取到要注册账号、密码并提交
	 * 			登录时，App端提交用户输入的用户名、密码提交，在数据库端完成验证并给App端反馈
	 * 运行注释：
	 * 
	 * 2016-12-8修改
	 * 注册只是往数据库写；登陆拿数据库的数据验证是否正确
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/reg_log_user?mode=check&User_Username=孙铖铖&User_Password=123abc
	 * 		http://localhost:8080/IslandTrading/analysis/reg_log_user?mode=register&User_Nickname=韩寒&User_Username=韩寒的用户名&User_Password=1234&User_TakingId=15686565&User_Tel=15230153136&Hx_Username=a12345&Hx_Password=12345
	 * */
	public void reg_log_user(){
		String str_mode = this.getPara("mode");	//区分验证方式，登陆还是注册
		
		//1.后台验证
		if(str_mode.equals("check")){
			String user_temp = this.getPara("User_Username");	//直接得到参数就行了，不用json了
			
			String pwd = this.getPara("User_Password");
			String user = null;
			try {
				user = new String(user_temp.getBytes("iso-8859-1"),"UTF-8");
				System.out.println("----reg_log_user()得到的参数--user:"+user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String str_nickname;
			
			//JSONObject用于最终组装得到的参数、结果状态标记等信息；
			JSONObject json = new JSONObject();
			JSONObject content = new JSONObject();
			JSONObject good = new JSONObject();
			
			//向网页中渲染信息
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			Record mRecord = analysisService.lookup_user(user);
			if(mRecord == null){	//用户名错误
				System.out.println("-----reg_log_user()--现在只能说明输入的用户名错误：" + user);
				writer.write("用户名不存在！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{	//用户名正确
				System.out.println("-----reg_log_user()得到的记录:"+mRecord.toString());
				
				//用户名验证通过，且拿了数据库中的信息，下面验证密码是否正确
				String str_pwd = mRecord.getStr("User_Password");
				if(str_pwd.equals(pwd)){	//用户输入密码正确
					System.out.println("用户输入密码正确！！！");
					str_nickname = mRecord.getStr("User_Nickname");
					writer.write("用户登录成功！");
					writer.flush();
					writer.close();
					this.renderNull();
				}
				else{	//用户输入密码错误
					writer.write("密码错误！");
					this.renderNull();
				}
			}
				
		}
		else if(str_mode.equals("register")){	
			try {
				String User_Nickname = new String((this.getPara("User_Nickname")).getBytes("iso-8859-1"),"UTF-8");
				String User_Username = new String((this.getPara("User_Username")).getBytes("iso-8859-1"),"UTF-8");
				String User_Password = this.getPara("User_Password");
				String User_TakingId = this.getPara("User_TakingId");
				String User_Tel = this.getPara("User_Tel");
				String Hx_Username = this.getPara("Hx_Username");
				String Hx_Password = this.getPara("Hx_Password");
				boolean res = orderService.reg_user(User_Nickname,User_Username,User_Password,
						User_TakingId,User_Tel,Hx_Username,Hx_Password);
				
				//用于向网页中渲染信息
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/json;charset=utf-8");
				PrintWriter writer = response.getWriter();
				
				if(res == true){			
					writer.write("注册成功！ User_Username:" + User_Username);
					writer.flush();
					writer.close();
					this.renderNull();
				}
				else{
					System.out.println("str_user::::" + User_Username);
					writer.write("注册失败！ User_Username:" + User_Username + "已存在！");
					writer.flush();
					writer.close();
					this.renderNull();
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			

		}//else if
	}	

	/**
	 * 用户订单提交
	 * 作者：孙铖铖
	 * 日期：2016-11-29
	 * 参数：total商品数量，record商品信息，oSum订单总价，address交易地址，telphone买家电话
	 * 		好像不能有商品id，万一一下买了好多呢；岛买岛卖这个机制就不能多买！！！
	 * 		
	 * 参数形如：/order={osm:30.0,address:"软件学院",telphone:1234566666,User_Id:700,pid:7878788}
	 * localhost:8080/supermarket/analysis/oreder?order={osm:30.0,address:"软件学院",telphone:1234566666,User_Id:700,pid:7878788}
	 * 
	 * 
	 * 2016-12-8修改
	 * localhost:8080/IslandTrading/analysis/oreder?order={ORDER_ID:1001,ORDER_SITE:河北师大,ORDER_TIME:'2016-12-8 20:46:55',OEDER_STATUS:true}
	 * 
	 */
	public void oreder() {
		
		String jsonContent = this.getPara("order");	//得到一堆参数
//		String jsonContent = "{ oId:A20160816001, total:3,record:["
//				+ "{pId:123462,pNum:1},"
//				+ "{pId:123460,pNum:2},"
//				+ "{pId:123461,pNum:1}]," + "oSum:30.5}";
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonContent);
			//提交数据，数据暂为String
			String ORDER_ID = jsonObject.getString("ORDER_ID");
			String ORDER_SITE = new String((jsonObject.getString("ORDER_SITE")).getBytes("iso-8859-1"),"UTF-8");
			String ORDER_TIME = jsonObject.getString("ORDER_TIME");
			String OEDER_STATUS = jsonObject.getString("OEDER_STATUS");
			boolean res = orderService.save(ORDER_ID, ORDER_SITE, ORDER_TIME, OEDER_STATUS);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			if(res == true){
				writer.write("订单提交成功！" + ORDER_ID);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{
				writer.write("订单提交失败！！！" + ORDER_ID);
				writer.flush();
				writer.close();
				this.renderNull();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.renderNull();
	}
	
	/**
	 * 订单提交
	 *  
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
//			orderService.save(oid, time, osum);

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
	 * 
	 * 2016-12-8修改
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/deleteById?goodsCode={Product_Id:123456}	
	 * */
	public void deleteById(){
		String jsonContent = this.getPara("goodsCode");//形如"{pid:123458}"
		String pId = "0";	//默认pId
		JSONObject jsonObject;	
		try {
			jsonObject = new JSONObject(jsonContent);
			pId = jsonObject.getString("Product_Id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean delete_result = analysisService.deleteMyGood(pId);
		if(delete_result == true){
			System.out.println("deleteById"+"删除成功！");
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
	 * 
	 * 2016-12-8修改
	 * 注释：
	 * 		发布时间始终为编辑时间，不可设置，系自动设置；
	 * 		经纬度不可以修改；
	 * 		不可以编辑点赞量、浏览量、置顶；
	 * 		boolean直接在url中获取  getBoolean 就行了
	 * 		携带多少参数……应该设置默认参数
	 * 		通过APP端获取Product_Id修改商品信息，Product_Name虽然也设为了主键，但可以重复，所以不可以拿来确定商品
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/editGoods?goodsCode={Product_Id:4,Product_Name:手机,Product_Price:50,Product_Describe:奢华手机,Product_Site:发布地点,Product_Status:true}	
	 * */
	public void editGoods(){
		
		String jsonContent = this.getPara("goodsCode");
		
		String Product_Id;
		String Product_Name;
		String Product_Price;
		String Product_Describe;
		String Product_Site;
		String Product_Status;
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			Product_Id = jsonObject.getString("Product_Id");
			Product_Name = new String((jsonObject.getString("Product_Name")).getBytes("iso-8859-1"),"UTF-8");
			Product_Price = jsonObject.getString("Product_Price");
			Product_Describe = new String((jsonObject.getString("Product_Describe")).getBytes("iso-8859-1"),"UTF-8");
			Product_Site = new String((jsonObject.getString("Product_Site")).getBytes("iso-8859-1"),"UTF-8");
			Product_Status = jsonObject.getString("Product_Status");
			
			int i = analysisService.editMyGood(Product_Id, Product_Name, Product_Price, Product_Describe, Product_Site, Product_Status);
			
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			if(i == 1){
				writer.write("商品编辑成功！Product_Id：" + Product_Id);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{
				writer.write("商品编辑失败！Product_Id：" + Product_Id);
				writer.flush();
				writer.close();
				this.renderNull();
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 实现：商品发布
	 * 参数：一堆
	 * 触发方式：http://localhost:8080/IslandTrading/analysis/addGoods?goods={User_Username:孙铖铖,Product_Name:三星S5 G9008V,Product_Price:2000,Product_Describe:中文商品描述,Product_Time:"2016-12-15 10:46:10",Product_Site:软件学院,Product_View:50,Product_Positive:40,Product_Status:TRUE,Product_Top:false,Product_Longgitude:340.0,Product_Lagitude:350.0,Product_Type=手机Phone}
	 * */
	public void addGoods(){
		String jsonContent = this.getPara("goods");
		
		//获取商品属性
//		String Product_Id;
		String Product_Name;
		String str_Product_Price;
		float Product_Price;
		String Product_Describe;
//		String PRODUCT_IMAGE	
		String Product_Time;
		String Product_Site;
		String Product_View;
		String Product_Positive;
		boolean Product_Status;
		boolean Product_Top;
		double Product_Longgitude; //经度
		double Product_Lagitude;	//纬度
		String Product_Type;
		
		
		//获取用户名
		String User_Username;
		
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			
			Product_Name = new String((jsonObject.getString("Product_Name")).getBytes("iso-8859-1"),"UTF-8");
			str_Product_Price = jsonObject.getString("Product_Price");
			Product_Price = Float.valueOf(str_Product_Price).floatValue();
			Product_Describe = new String((jsonObject.getString("Product_Describe")).getBytes("iso-8859-1"),"UTF-8");
			Product_Time = jsonObject.getString("Product_Time");
			Product_Site = new String((jsonObject.getString("Product_Site")).getBytes("iso-8859-1"),"UTF-8");
			Product_View = jsonObject.getString("Product_View");
			Product_Positive = jsonObject.getString("Product_Positive");
			Product_Status = jsonObject.getBoolean("Product_Status");
			Product_Top = jsonObject.getBoolean("Product_Top");
			Product_Longgitude = jsonObject.getDouble("Product_Longgitude");
			Product_Lagitude = jsonObject.getDouble("Product_Lagitude");
			Product_Type = new String((jsonObject.getString("Product_Type")).getBytes("iso-8859-1"),"UTF-8");
			System.out.println("----接口里Product_Status： " + Product_Status+"  Product_Top:" + Product_Top);
			
			//获取USER_NAME
			User_Username = new String((jsonObject.getString("User_Username")).getBytes("iso-8859-1"),"UTF-8");
//			System.out.println("------User_Username:" + User_Username);
			Record mRecord = new Record().set("Product_Name", Product_Name)
					.set("Product_Price", Product_Price)
					.set("Product_Describe", Product_Describe)
					.set("Product_Time", Product_Time)
					.set("Product_Site", Product_Site)
					.set("Product_View", Product_View)
					.set("Product_Positive", Product_Positive)
					.set("Product_Status", Product_Status)
					.set("Product_Top", Product_Top)
					.set("Product_Longgitude", Product_Longgitude)
					.set("Product_Lagitude", Product_Lagitude)
					.set("Product_Type", Product_Type);
			boolean res_goods = analysisService.add_Goods(mRecord);
			boolean res_goods_user = analysisService.add_Goods_User(Product_Name,User_Username);
			System.out.println("----发布状态监视：res_goods:" + res_goods + "  res_goods_user:" + res_goods_user);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			if(res_goods && res_goods_user){
				writer.write("商品发布成功！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{	//记得处理异常
				writer.write("商品发布失败！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 实现：上传图片
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 注释：如果不能得到图片下载的url
	 * 触发方式：http://192.168.100.2:8080/IslandTrading/analysis/uploadImg
	 * */
	public void uploadImg(){
		System.out.println("-----调用uploadImg()--");
		UploadFile uploadFile = getFile("profile_picture");	
		String img_Name = uploadFile.getFileName();
		System.out.println("------" + img_Name);	//得到的是存入服务器的名字，这也是返回的名字
		analysisService.add_img_url(img_Name);
		renderText("ok");	
	}
	
	/*
	 * 实现：下载图片
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 触发方式：http://192.168.100.2:8080/IslandTrading/analysis/downloadImg?Product_Id=1
	 * */
	public void downloadImg(){
//		String Product_Id = this.getPara("Product_Id");
		int pid = this.getParaToInt("Product_Id");	//也可以正确得到参数
//		System.out.println("-----downloadImg（）得到的参数：Product_Id：" + Product_Id + "  pid:" + pid);
		String str_img_name = analysisService.getImg(pid);
		
		String str = "scc.jpg";
		renderFile(str_img_name);
		System.out.println("-----调用downloadImg()--str_img_name:" +str_img_name);
	}
	

}
