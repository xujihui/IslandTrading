package controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
		List<Record> procollect = collectService.findAll();
		this.setSessionAttr("procollect", procollect);
		this.render("/CollectList.jsp");
	}

	@Before(CollectInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String pId = this.getPara("COLLECT_ID");
		String pStatus = this.getPara("COLLECT_STATUS");
		Date pTime = this.getParaToDate("COLLECT_TIME");

		String result;
		int res = collectService.update(pId, pStatus,pTime);
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
		String id = this.getPara("COLLECT_ID");
		Record rec = collectService.findByID(id);
		this.setAttr("collect", rec);
		this.render("/findCollect.jsp");
	}
}
