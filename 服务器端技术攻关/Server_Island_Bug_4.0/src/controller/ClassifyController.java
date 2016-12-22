/**
 * Function:ClassifyController
 * Date:2016.12.11
 * Author:LiuXin
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

import interceptor.LoginInterceptor;
import service.ClassifyBiz;
import validate.ClassifyInfoValidator;

@Before(LoginInterceptor.class)
public class ClassifyController extends Controller {
	ClassifyBiz classifyService = this.enhance(ClassifyBiz.class);

	@Before(ClassifyInfoValidator.class)
	public void save() {
		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pName = this.getPara("CLASSIFY_NAME");
		String pImage = this.getPara("CLASSIFY_IMAGE");

		String result = "分类信息不能为空";
	    boolean res = classifyService.save(pName, pImage);
				if (res) {
					result = "分类添加成功";
				} else {
					result = "分类添加失败";
				}
			this.setAttr("result", result);
			this.render("/addClassify.jsp");
	}

	public void deleteByID() {
		String id = this.getPara(0);
		boolean res = classifyService.deleteByID(id);
		String result;
		if (res) {
			result = "分类删除成功";
		} else {
			result = "分类删除失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	public void list() {
		List<Record> proclassify = classifyService.findAll();
		this.setSessionAttr("proclassify", proclassify);
		this.render("/ClassifyList.jsp");
	}

	@Before(ClassifyInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String pID = this.getPara("CLASSIFY_ID");
		String pName = this.getPara("CLASSIFY_NAME");
		String pImage = this.getPara("CLASSIFY_IMAGE");

		String result = "分类更新信息不能为空";
		int res = classifyService.update(pID, pName, pImage);
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
		String id = this.getPara("Classify_Id");
		Record rec = classifyService.findByID(id);
		this.setAttr("classify", rec);
		this.render("/findClassify.jsp");
	}
}
