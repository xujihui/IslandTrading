/**
 * Function:CollectController
 * Date:2016.12.11
 * Author:LiuXin
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
		Date day=new Date();   
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pTime = df.format(day);
		String result = "更新内容不能为空";
		int res = collectService.update(pId, pStatus, pTime);
		if (res > 0) {
			result = "更新成功";
		} else {
			result = "更新失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	@Before(POST.class)
	public void findByID() {
		String id = this.getPara("Collect_Id");
		Record rec = collectService.findByID(id);
		this.setAttr("collect", rec);
		this.render("/findCollect.jsp");
	}
}
