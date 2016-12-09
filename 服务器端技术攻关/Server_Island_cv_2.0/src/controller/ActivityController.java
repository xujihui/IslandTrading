package controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

import interceptor.LoginInterceptor;
import service.ActivityBiz;
import validate.ActivityInfoValidator;

@Before(LoginInterceptor.class)
public class ActivityController extends Controller {
	ActivityBiz activityService = this.enhance(ActivityBiz.class);

	@Before(ActivityInfoValidator.class)
	public void save() {
		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pID = this.getPara("ACTIVITY_ID");
		String pName = this.getPara("ACTIVITY_NAME");
		String pContent = this.getPara("ACTIVITY_CONTENT");
		String pOrganizer = this.getPara("ACTIVITY_ORGANIZER");
		Date pTime = this.getParaToDate("ACTIVITY_TIME");
		String pSite = this.getPara("ACTIVITY_SITE");
		String result;
        
		if ((pID != null && pName.trim()!="") && (pName != null && pName.trim() != "")) {
			Record rec = activityService.findByID(pID);
			if (rec == null) {
				boolean res = activityService.save(pID, pName, pContent, pOrganizer, pTime, pSite);

				if (res) {
					result = "添加成功";
				} else {
					result = "添加失败";
				}
			} else {
				result = "ID已经存在,请重新添加";
			}

			this.setAttr("result", result);
			this.render("/addActivity.jsp");
		} else {
			this.render("/addActivity.jsp");
		}
	}

	public void deleteByID() {
		String id = this.getPara(0);
		boolean res = activityService.deleteByID(id);
		String result;
		if (res) {
			result = "删除成功";
		} else {
			result = "删除失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	public void list() {
		List<Record> prolist = activityService.findAll();
		this.setSessionAttr("prolist", prolist);
		this.render("/ActivityList.jsp");
	}

	@Before(ActivityInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String pID = this.getPara("ACTIVITY_ID");
		String pName = this.getPara("AVTIVITY_NAME");
		String pContent = this.getPara("ACTIVITY_CONTENT");
		String pOrganizer = this.getPara("ACTIVITY_ORGANIZER");
		String PTime = this.getPara("ACTIVITY_TIME");
		String pSite = this.getPara("ACTIVITY_SITE");

		String result = "更新信息不能为空";
		int res = activityService.update(pID, pName, pContent, pOrganizer, PTime, pSite);
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
		String id = this.getPara("ACTIVITY_ID");
		Record rec = activityService.findByID(id);
		this.setAttr("rec", rec);
		this.render("/findActivity.jsp");
	}
}
