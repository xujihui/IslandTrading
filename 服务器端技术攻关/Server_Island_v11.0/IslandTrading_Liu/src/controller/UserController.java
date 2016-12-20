/**
 * Function:UserController
 * Date:2016.12.11
 * Author:LiuXin
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import interceptor.LoginInterceptor;
import service.UserBiz;
import validate.UserInfoValidator;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Record;

@Before(LoginInterceptor.class)
public class UserController extends Controller {
	UserBiz userService = this.enhance(UserBiz.class);

	@Before(UserInfoValidator.class)
	public void save() {
		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pID = this.getPara("USER_ID");
		String pNickname = this.getPara("USER_NICKNAME");
		String pUsername = this.getPara("USER_USERNAME");
		String pPassword = this.getPara("USER_PASSWORD");
		String pImage = this.getPara("USER_IMAGE");
		String pPower = this.getPara("USER_POWER");
		String pTakingid = this.getPara("USER_TAKINGID");
		String pTel = this.getPara("USER_TEL");
		String pHx_username = this.getPara("HX_USERNAME");
		String pHx_password = this.getPara("HX_PASSWORD");

		String result = "用户信息不为空";

		if ((pID != null && pID.trim() != "")) {
			Record rec = userService.findByID(pID);
			if (rec == null) {
				boolean res = userService.save(pID, pNickname, pUsername, pPassword, pImage, pPower, pTakingid, pTel,
						pHx_username, pHx_password);
				if (res) {
					result = "用户添加成功";
				} else {
					result = "用户添加失败";
				}
			} else {
				result = "该用户ID已经存在,重新添加";
			}

			this.setAttr("result", result);
			this.render("/addUser.jsp");
		} else {
			this.render("/addUser.jsp");
		}
	}

	public void deleteByID() {
		String id = this.getPara(0);
		boolean res = userService.deleteByID(id);
		String result;
		if (res) {
			result = "用户删除成功";
		} else {
			result = "用户删除失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	public void list() {
		List<Record> prouser = userService.findAll();
		this.setSessionAttr("prouser", prouser);
		this.render("/userList.jsp");
	}

	@Before(UserInfoValidator.class)
	public void update() {

		try {
			this.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String pID = this.getPara("USER_ID");
		String pNickname = this.getPara("USER_NICKNAME");
		String pUsername = this.getPara("USER_USERNAME");
		String pPassword = this.getPara("USER_PASSWORD");
		String pImage = this.getPara("USER_IMAGE");
		String pPower = this.getPara("USER_POWER");
		String pTakingid = this.getPara("USER_TAKINGID");
		String pTel = this.getPara("USER_TEL");
		String pHx_username = this.getPara("HX_USERNAME");
		String pHx_password = this.getPara("HX_PASSWORD");

		String result;
		int res = userService.update(pID, pNickname, pUsername, pPassword, pImage, pPower, pTakingid, pTel,
				pHx_username, pHx_password);
		if (res > 0) {
			result = "用户更新成功";
		} else {
			result = "用户更新失败";
		}
		this.setAttr("result", result);
		this.render("/result.jsp");
	}

	@Before(POST.class)
	public void findByID() {
		String id = this.getPara("User_Id");
		Record rec = userService.findByID(id);
		this.setAttr("user", rec);
		this.render("/findUser.jsp");
	}

}
