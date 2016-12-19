/**
 * Function:FeedbackController
 * Date:2016.12.11
 * Author:LiuXin
 */
package controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

import interceptor.LoginInterceptor;
import service.FeedbackBiz;
import validate.FeedbackInfoValidator;

@Before(LoginInterceptor.class)
public class FeedbackController extends Controller {
	FeedbackBiz feedbackService = this.enhance(FeedbackBiz.class);

	@Before(FeedbackInfoValidator.class)
	public void deleteByID() {
		String id = this.getPara(0);
		boolean res = feedbackService.deleteByID(id);
		String result;
		if (res) {
			result = "·´À¡É¾³ý³É¹¦";
		} else {
			result = "·´À¡É¾³ýÊ§°Ü";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	public void list() {
		List<Record> profeedback = feedbackService.findAll();
		this.setSessionAttr("profeedback", profeedback);
		this.render("/FeedbackList.jsp");
	}

	@Before(POST.class)
	public void findByID() {
		String id = this.getPara("Fb_Id");
		Record rec = feedbackService.findByID(id);
		this.setAttr("feedback", rec);
		this.render("/findFeedback.jsp");
	}
}
