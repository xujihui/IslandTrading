package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
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
	 * 
	 * 2016-11-27  商品查询
	 * 输入pId或pName，获得商品的详细信息
	 * 触发方式：http://localhost:8080/supermarket/analysis/lookupprice?pName={pName:华为}
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
	 * @throws UnsupportedEncodingException 
	 */
	public void lookupprice() throws UnsupportedEncodingException {

//		String jsonContent = this.getPara("goodsCode"); //goodsCode 和 pid 是一个东西		
		String jsonContent_pId = this.getPara("pId");
		String jsonContent_pName = this.getPara("pName");
		//String jsonContent = "{pid:123458}";	//告诉你参数是什么样子，下面的接口中应该也一样
		String jsonContent = jsonContent_pId == null?jsonContent_pName:jsonContent_pId;

		String pid = "123456";	//默认pid，应该是为了防止获取不到pId设置的
		String pName = "Default";	//默认pName，为了防止获取不到pName设置的
		String res = "ok";	//标记，查询成功；解析时可以以此key为查询结果，ok为存在相应商品，no为不存在
		float price;
//		String name;
		
		//用于存储查询到的其他项
		String str_des;
		String str_type;
		String str_loc;
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
				pid = jsonObject.getString("pId");	//得到商品pid
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("lookup price pId:" + pid + "  lookup pname pName:" + pName);
			Record rec_pId = analysisService.lookupprice(pid);
			
			//如果没有指定商品，停止执行
			if(rec_pId == null){
				res = "no";
				System.out.println("-----没有指定商品 lookupprice（） pId:" + pid);
				writer.write("没有指定商品" + pid);
				this.renderNull();	
				return;
			}
			
			
			// 获取商品字段信息
			price = rec_pId.getFloat("price");
			//price=Float.parseFloat(new java.text.DecimalFormat("#.00").format(rec.getFloat("price")));
			pName = rec_pId.getStr("name");
			str_des = rec_pId.getStr("description");
			str_type = rec_pId.getStr("type");
			str_loc = rec_pId.getStr("location");
			date_time = rec_pId.getDate("time");
			str_time = (new SimpleDateFormat("yyyy-MM-dd")).format(date_time);
			
		}
		else{
			System.out.println("携带的pId："+jsonContent_pId + "  pName：" + jsonContent_pName);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent);
				String pName_temp = jsonObject.getString("pName");	//得到商品pName
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
				writer.write("没有指定商品" + pName);
				this.renderNull();	
				return;
			}
			// 获取商品字段信息
			price = rec_pName.getFloat("price");
			//price=Float.parseFloat(new java.text.DecimalFormat("#.00").format(rec.getFloat("price")));
			String pid_temp = String.valueOf(rec_pName.getInt("pid"));
			str_des = rec_pName.getStr("description");
			str_type = rec_pName.getStr("type");
			str_loc = rec_pName.getStr("location");
			date_time = rec_pName.getDate("time");
			str_time = (new SimpleDateFormat("yyyy-MM-dd")).format(date_time);
		}
		// 组装Json串
		try {

			// {"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}
			System.out.println("name"+pName);
			content.put("pId", pid);
			content.put("pName", pName);
			content.put("pPrice", price);
			content.put("pDes", str_des);
			content.put("pType", str_type);
			content.put("pLoc", str_loc);
			content.put("pTime", str_time);
			

			// {"res":"1","content":{"pPrice":3.200000047683716,"pName":"牛奶","pID":"123458"}}
			json.put("res", res);
			json.put("content", content);
			good.put("good", json);

			System.out.println("----ret:" + good.toString());
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
	 * */
	public void type_collection(){
		String jsonContent = this.getPara("pType");	//得到携带的参数pType
		String type = "default";	//设置默认类型
		String res = "ok";	//默认查询结果
		
		//用于存储获得某商品的详细信息
		String str_pid = null;
		String str_name = null;
		float price = 0;
		String str_des = null;
		String str_loc = null;
		Date date_time;
		String str_time = null;
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
		System.out.println("----jsonContent:"+jsonContent.toString());
		if(jsonContent != null){
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent);
				String str_temp = jsonObject.getString("pType");
				type = new String(str_temp.getBytes("iso-8859-1"),"UTF-8");
				System.out.println("----中文否？type:"+type);	//成功得到中文
				List<Record> list_Record_type = new ArrayList<>();
				list_Record_type = analysisService.lookup_type(type);
				
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();
				
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				if(list_Record_type.size() == 0){
					res = "no";
					System.out.println("type_collection()------此类别为空！"+type);
					writer.write(type + "此类别没有商品！");
					this.renderNull();
				}
				//循环查找表
				for(Record rec_pType: list_Record_type){
					//获取商品字段信息
					str_pid = String.valueOf(rec_pType.getInt("pid"));
					str_name = rec_pType.getStr("name");
					price = rec_pType.getFloat("price");
					str_des = rec_pType.getStr("desciption");
					str_loc = rec_pType.getStr("location");
					date_time = rec_pType.getDate("time");
					str_time = (new SimpleDateFormat("yyyy-MM-dd")).format(date_time);
					//组装json串
					try {
						System.out.println("name"+str_name);
						content.put("pId", str_pid);
						content.put("name", str_name);
						content.put("pPrice", price);
						content.put("pDes", str_des);
						content.put("pType", type);
						content.put("pLoc", str_loc);
						content.put("pTime", str_time);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);

						System.out.println("----good串:" + good.toString());
						
						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//for
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
	 * */
	public void request_acts(){
		String res = "ok";	//默认查询结果
		
		//用于存储获得的活动详细信息
		String str_id;
		String str_name;
		String str_dec;
		String str_organizer;
		Date date_temp;
		String str_time;
		String str_site;
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
			try {
				List<Record> list_Record_act = new ArrayList<>();
				list_Record_act = analysisService.lookup_act();
				
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();
				
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				if(list_Record_act.size() == 0){
					System.out.println("request_acts()------暂时没有活动！");
					writer.write("暂时没有活动！");
					this.renderNull();
				}
				//循环查找表
				for(Record rec_pType: list_Record_act){
					//获取商品字段信息
					str_id = String.valueOf(rec_pType.getInt("id"));
					str_name = rec_pType.getStr("name");
					str_dec = rec_pType.getStr("desciption");
					str_organizer = rec_pType.getStr("organizer");
					date_temp = rec_pType.getDate("time");
					str_time = (new SimpleDateFormat("yyyy-MM-dd")).format(date_temp);
					str_site = rec_pType.getStr("site");
					
					//组装json串
					try {
						System.out.println("name"+str_name);
						content.put("pId", str_id);
						content.put("name", str_name);
						content.put("pDes", str_dec);
						content.put("organizer", str_organizer);
						content.put("pLoc", str_site);
						content.put("pTime", str_time);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);

						System.out.println("----good串:" + good.toString());
						
						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//for
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
	 * */
	public void request_col(){
		String jsonContent_goods = this.getPara("goods");	
		String jsonContent_user = this.getPara("user");
		String goods = "default";
		String user = "default";
		String res = "ok";
		String str_goods_id = null;	//可能不使用
		boolean b_status;	//sql中bit类型，需要转换！
		Date time_temp;
		String str_time;
		JSONObject json = new JSONObject();
		JSONObject content = new JSONObject();
		JSONObject good = new JSONObject();
//		System.out.println("----jsonContent_user:"+jsonContent_user.toString() + "  " 
//		+ "----jsonContent_user:" +jsonContent_goods.toString());
		if(jsonContent_goods != null){
		try {
			JSONObject jsonObject_temp = new JSONObject(jsonContent_goods);
			goods = jsonObject_temp.getString("goods");
		} catch (JSONException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		}
		//如果没有携带goods_id
		if(goods.equals("default")){	//如果没有携带goods_id，即商品id，只处理user参数
			res = "no";
			System.out.println("----执行if：");
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(jsonContent_user);
				user = jsonObject.getString("user");
				System.out.println("----中文否？type:"+user);	//成功得到中文
				List<Record> list_Record_type = new ArrayList<>();
				list_Record_type = analysisService.lookup_col(user);
								
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();
				
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				//如果用户名非法，提示 没有收藏任何商品
				if(list_Record_type.size() == 0){
					System.out.println("-----没有匹配数据 request_col if分支.");
					writer.write("用户："+user+" 没有收藏任何商品!");
					this.renderNull();
				}
				//循环查找表
				for(Record rec_pType: list_Record_type){
					//获取商品字段信息
					str_goods_id = String.valueOf(rec_pType.getInt("id_goods"));					
					time_temp = rec_pType.getDate("date");
					str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(time_temp);
					b_status = rec_pType.getBoolean("status");
					//组装json串
					try {
						content.put("pId", str_goods_id);
						content.put("user", user);
						content.put("pTime", str_time);
						content.put("status", b_status);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);

						System.out.println("----good串:" + good.toString());
						
						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("-----捕获到异常   e1 !!!");
						e.printStackTrace();
					}
				}//for
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
		}
		else{	//携带了goods_id，即商品id，处理两个参数
			System.out.println("----执行else：");
			JSONObject jsonObject_goods;
			JSONObject jsonObject_user;
			try {
				System.out.println("----else中得到的参数：jsonContent_user" + 
						jsonContent_user.toString() + "  jsonObject_goods:" + 
						jsonContent_goods.toString());
				jsonObject_user = new JSONObject(jsonContent_user);
				jsonObject_goods = new JSONObject(jsonContent_goods);
				user = jsonObject_user.getString("user");
				goods = jsonObject_goods.getString("goods");
				System.out.println("-----user:"+user+"  goods:"+goods);
				List<Record> list_Record_type = new ArrayList<>();
				list_Record_type = analysisService.lookup_col(user, goods);
				System.out.println(list_Record_type.toString()+"-----");	//list里没东西
				PrintWriter writer;
				HttpServletResponse response = this.getResponse();
				
				response.setContentType("application/json;charset=utf-8");
				writer = response.getWriter();
				
				//如果用户名非法，提示 没有收藏任何商品
				if(list_Record_type.size() == 0){
					res = "no";
					System.out.println("-----没有匹配数据 request_col if分支.");
					writer.write("商品已不存在或者请求商品id非法！");
					this.renderNull();
				}
				//循环查找表
				for(Record rec_pType: list_Record_type){
					//获取商品字段信息
					str_goods_id = String.valueOf(rec_pType.getInt("id_goods"));					
					time_temp = rec_pType.getDate("date");
					str_time = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(time_temp);
					b_status = rec_pType.getBoolean("status");
					//组装json串
					try {
						content.put("pId", str_goods_id);
						content.put("user", user);
						content.put("pTime", str_time);
						content.put("status", b_status);
						
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);

						System.out.println("----good串:" + good.toString());
						
						writer.append(good.toString());
						
						// this.renderJson(jsonText);
						this.renderNull();		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("-----捕获到异常   e1 !!!");
						e.printStackTrace();
					}
				}//for
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
	 * 触发方式：http://localhost:8080/supermarket/analysis/submit_fb?checkout={"id":"1","user_id":"2","content":"你好","time":"2016-11-25 11:13:01"}
	 * 运行注释：携带参数 checkout 
	 * */
	public void submit_fb(){
		String jsonContent = this.getPara("checkout");
		String str_id;	//反馈信息id，这个参数必须有
		String str_user_id;	//用户id，这个参数，暂定必带
		String str_content;	//反馈内容，必带
//		Date time_date;	//时间，必带！
		String str_time;	
		boolean b_status;	//问题是否已经处理了，客户端不需要上传，为了便于填充表，此处给一个默认值 false
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			str_id = jsonObject.getString("id");
			str_user_id = jsonObject.getString("user_id");
			String str_content_temp = jsonObject.getString("content");
			str_content = new String(str_content_temp.getBytes("iso-8859-1"),"UTF-8");
			str_time = jsonObject.getString("time");
			System.out.println("----str_time:"+str_time);
			b_status = false;	//什么时候转换成 bit，要不存储的时候转换一下，但是提交成网址之后，会变成什么……
			boolean b = orderService.subfb(str_id, str_user_id, str_content, str_time, b_status);
			System.out.println("-----submit_fb()获得的参数jsonContent:" + jsonContent+"\n转换后的效果 content:" + str_content);
			
			if(b == true){
				System.out.println("反馈提交成功：id" + str_id + "  user_id:" + str_user_id);
				this.renderHtml("反馈提交成功！");
				return;
			}
			else{
				System.out.println("反馈提交失败：id" + str_id + "  user_id:" + str_user_id);
				this.renderHtml("反馈提交失败！");
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
	 * 触发方式：http://localhost:8080/supermarket/analysis/reg_log_user?mode=register&user=%E5%91%A8%E6%9D%B0%E4%BC%A6&pwd=123abcdefg
	 * App端注释：	App端携带mode参数，验证登陆check  还是 注册register
	 * 			注册时，App端能获取到要注册账号、密码并提交
	 * 			登录时，App端提交用户输入的用户名、密码提交，在数据库端完成验证并给App端反馈
	 * 运行注释：
	 * */
	public void reg_log_user(){
		String str_mode = this.getPara("mode");	//区分验证方式，登陆还是注册
		
		//1.后台验证
		if(str_mode.equals("check")){
			String user_temp = this.getPara("user");	//直接得到参数就行了，不用json了
			String user = null;
			try {
				user = new String(user_temp.getBytes("iso-8859-1"),"UTF-8");
				System.out.println("----reg_log_user()得到的参数--user:"+user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pwd = this.getPara("pwd");
			
			String str_nickname;
			int power;
			String res = "";	//结果状态标记  ok , no
			
			//JSONObject用于最终组装得到的参数、结果状态标记等信息；
			JSONObject json = new JSONObject();
			JSONObject content = new JSONObject();
			JSONObject good = new JSONObject();
			
			//向网页中渲染信息……不太清楚作用
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
				writer.write("用户名错误！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{	//用户名正确
				System.out.println("-----reg_log_user()得到的记录:"+mRecord.toString());
				
				//用户名验证通过，且拿了数据库中的信息，下面验证密码是否正确
				String str_pwd = mRecord.getStr("password");
				if(str_pwd.equals(pwd)){	//用户输入密码正确
					System.out.println("用户输入密码正确！！！");
					str_nickname = mRecord.getStr("nickname");
					power = mRecord.getInt("power");
					res = "ok";
					try {
						content.put("username", user);
						content.put("password", pwd);
						content.put("nickname", str_nickname);
						content.put("power", power);
						json.put("res", res);
						json.put("content", content);
						good.put("good", json);
						System.out.println("----reg_log_user（）中 good:"+good.toString());
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					writer.write(good.toString());
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
		//2.添加账户 http://localhost:8080/supermarket/analysis/reg_log_user?mode=check&user=%E7%94%A8%E6%88%B7%E5%90%8D&pwd=123a
		else if(str_mode.equals("register")){	
			String str_pwd = this.getPara("pwd");
			String str_nick_temp = this.getPara("nick");
			String str_user_temp = this.getPara("user");		//携带了一堆参数
			String str_user = null;
			String str_nick = "default nick";
			try {
				str_user = new String(str_user_temp.getBytes("iso-8859-1"),"UTF-8");
				if(!str_nick.equals("default nick")){
					str_nick = new String(str_nick_temp.getBytes("iso-8859-1"),"UTF-8");
				}
				boolean b_res = orderService.reg_user(str_user, str_pwd, str_nick);
				System.out.println("----注册reg_log_user（）  - 获得账号" + str_user);
				//用于向网页中渲染信息
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/json;charset=utf-8");
				PrintWriter writer = response.getWriter();
				if(b_res == true){			//犯了个很低级的错误，用了 = ！！！
					writer.write("注册成功！ user:" + str_user);
					writer.flush();
					writer.close();
					this.renderNull();
				}
				else{
					System.out.println("str_user::::" + str_user);
					writer.write("注册失败！ user:" + str_user);
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
	 * 参数形如：/order={osm:30.0,address:"软件学院",telphone:1234566666,user_id:700,pid:7878788}
	 * localhost:8080/supermarket/analysis/oreder?order={osm:30.0,address:"软件学院",telphone:1234566666,user_id:700,pid:7878788}
	 */
	public void oreder() {
		
		String jsonContent = this.getPara("order");	//得到一堆参数
//		String jsonContent = "{ oId:A20160816001, total:3,record:["
//				+ "{pId:123462,pNum:1},"
//				+ "{pId:123460,pNum:2},"
//				+ "{pId:123461,pNum:1}]," + "oSum:30.5}";
		String oid = "";	//订单编号
		float osum = 0;		//订单总价
		String address = "";	//交易地址
		String telphone = "";	//买家电话
		String user_id = "";	//买家 user_id
		String pid = "";
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat oidformat=new SimpleDateFormat("yyMMddHHmmss");
		String time=format.format(date);	//将现在时间转成String时间
		oid = oidformat.format(date);	//将现在时间转成订单号oid
		System.out.println("----time:"+time);
		System.out.println("----oid:"+oid);
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			osum = Float.parseFloat(jsonObject.getString("osm"));
			String str_address = jsonObject.getString("address");
			String str_telphone = jsonObject.getString("telphone");
			String str_user_id = jsonObject.getString("user_id");
			pid = jsonObject.getString("pid");
			try {
				address = new String(str_address.getBytes("iso-8859-1"),"UTF-8");
				telphone = new String(str_telphone.getBytes("iso-8859-1"),"UTF-8");
				user_id = new String(str_user_id.getBytes("iso-8859-1"),"UTF-8");
//				pid = new String(str_pid.getBytes("iso-8856-1"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			System.out.println("得到否  ---address:" + address + "telphone:" 
					+ telphone + "user_id:" + user_id + "pid:" + pid);

			// 保存订单记录
			boolean b_res = orderService.save(oid, osum, address, telphone,user_id,pid);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			if(b_res == true){	//订单保存成功			
				writer.write("订单提交成功！" + oid);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{	//订单保存失败
				writer.write("订单提交失败！！！" + oid);
				writer.flush();
				writer.close();
				this.renderNull();
			}
			
			
			
//			JSONArray jsonrecos = jsonObject.getJSONArray("record");	//记录各商品信息
//			JSONObject jsonreco = null;	//临时变量
//			List<Record> recordList = new ArrayList<Record>();	//解析完的各商品信息
			

//			// 批量添加 商品名、商品价格、数量、所属订单
//			String pID;
//			String pName;
//			float pPrice;
//			String pNum;
//
//			// 解析订单中记录
//			for (int i = 0; i < total; i++) {
//				
//				jsonreco = jsonrecos.getJSONObject(i);
//
//				// 根据商品号，获得商品名称及单价
//				pID = jsonreco.getString("pid");
//				System.out.println("pid:"+pID);
//				
//				Record product = productService.findByID(pID);
//
//				// 获取商品信息
//				pName = product.getStr("name");
//				pPrice = product.getFloat("price");
//				pNum = jsonreco.getString("num");
//
//				System.out.println("name:" + pName + "price:" + pPrice
//						+ "pNum:" + pNum + "oid:" + oid);
//
//				// 将商品信息封装成记录
//				Record record = new Record().set("name", pName).set("price", pPrice)
//						.set("number", pNum).set("oid", oid);
//				System.out.println("name:" + i+":"+record.getStr("name"));
//				recordList.add(i, record);
//			}
//			
//			for (Record record2 : recordList) {
//				System.out.println("name:" + record2.getStr("name"));
//			}
//
//			// 批量存储订单详细信息记录
//			int[] res = orderService.batchsave(recordList, total);
//			JSONObject resjson = new JSONObject();
//			JSONObject order = new JSONObject();
//			if (res.length > 0) {
//				resjson.put("res", "ok");
//			} else {
//				resjson.put("res", "no");
//			}
//			order.put("order", resjson);
//			// 响应
//			try {
//				HttpServletResponse response = this.getResponse();
//				response.setContentType("application/json;charset=utf-8");
//				PrintWriter writer = response.getWriter();
//				writer.write(order.toString());
//				writer.flush();
//				writer.close();
//				// this.renderJson(jsonText);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (JSONException e) {
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
	 * */
	public void deleteById(){
		String jsonContent = this.getPara("goodsCode");//形如"{pid:123458}"
		String pId = "123459";	//默认pId
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
			pPrice = jsonObject_goods.getDouble("pPrice");  
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
