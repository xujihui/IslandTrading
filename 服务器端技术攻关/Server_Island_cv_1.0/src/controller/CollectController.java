package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

import interceptor.LoginInterceptor;
import service.CollectBiz;
import validate.CollectInfoValidator;

@Before(LoginInterceptor.class)
public class CollectController extends Controller {
	CollectBiz collectService = this.enhance(CollectBiz.class);

	@Before(CollectInfoValidator.class)

	public void list() {
		List<Record> prolist = collectService.findAll();
		this.setSessionAttr("prolist", prolist);
		this.render("/CollectList.jsp");
	}

	@Before(CollectInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String pProduct_id = this.getPara("proProduct_id");
		String pUser_id = this.getPara("proUser_id");
		String pStatus = this.getPara("proStatus");
		String pDate = this.getPara("proDate");

		String result = "收藏信息不能为空";
		int res = collectService.update(pProduct_id, pUser_id, pStatus, pDate);
		if (res > 0) {
			result = "收藏更新成功";
		} else {
			result = "收藏更新失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	@Before(POST.class)
	public void findByID() {
		String id = this.getPara("product_id");
		Record rec = collectService.findByID(id);
		this.setAttr("collect", rec);
		this.render("/findCollect.jsp");
	}
}
