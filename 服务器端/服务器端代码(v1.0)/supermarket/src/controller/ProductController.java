package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import interceptor.LoginInterceptor;
import service.ProductBiz;
import validate.ProductInfoValidator;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

@Before(LoginInterceptor.class)
public class ProductController extends Controller {
	ProductBiz productService = this.enhance(ProductBiz.class);

	//
	//增加用户请求
	// public void add() {
	// this.render("/addUser.jsp");
	// }

	// 保存用户信息
	@Before(ProductInfoValidator.class)
	public void save() {
		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pID = this.getPara("proID");
		String pName = this.getPara("proName");
		String price = this.getPara("proPrice");
		float pPrice = 0;
		String result = "商品信息不能为空";

		if (price != null && price != "") {
			pPrice = Float.parseFloat(price);
		}

		if ((pID != null && pID.trim() != "")
				&& (pName != null && pName.trim() != "")) {
			// String result =
			// "Hi,"+this.getSession().getAttribute("userName")+"! you have save user ("+name+","+sex+","+age+","+pwd+","+") success!";
			
			Record rec = productService.findByID(pID);
			if(rec==null){
				boolean res = productService.save(pID, pName, pPrice);
				// this.setAttr("result", result);

				if (res) {
					result = "商品添加成功,继续添加";
				} else {
					result = "商品添加失败，重新添加";
				}
			}
			else{
				result = "该商品ID已经存在,重新添加";
			}
			
			this.setAttr("result", result);
			this.render("/addProduct.jsp");
		} else {
			this.render("/addProduct.jsp");
		}
	}

	public void deleteByID() {
		String id = this.getPara(0);
		boolean res = productService.deleteByID(id);
		String result;
		if (res) {
			result = "商品删除成功";
		} else {
			result = "商品删除失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	public void list() {
		List<Record> prolist = productService.findAll();
		this.setSessionAttr("prolist", prolist);
		this.render("/productList.jsp");
	}

	@Before(ProductInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pID = this.getPara("proID");
		String pName = this.getPara("proName");
		float pPrice = Float.parseFloat(this.getPara("proPrice"));
		String result = "商品更新信息不能为空";
		int res = productService.update(pID, pName, pPrice);
		if (res > 0) {
			result = "商品更新成功";
		} else {
			result = "商品更新失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
		// this.render("/product/list");
	}

	@Before(POST.class)
	public void findByID() {
		String id = this.getPara("pid");
		System.out.println(" find by id:" + id);
		Record rec = productService.findByID(id);
		this.setAttr("product", rec);
		this.render("/findProduct.jsp");
	}

}
