package controller;

import service.ProductBiz;

import com.jfinal.core.Controller;

public class LoginController extends Controller {
	ProductBiz userService = this.enhance(ProductBiz.class);
	public void index() {
		this.render("/login.jsp");
	}

	public void login() {

		String userName = this.getPara("userName");
		String userPwd   = this.getPara("userPwd");

		this.setAttr("userName", userName);
		this.setAttr("userPwd",userPwd);
		//用户已经登录，UserName 存在session中
		this.setSessionAttr("userName", userName);
		
		//当用户名为admin且密码为admin时，转到管理界面
		if(userName.equals("admin") && userPwd.equals("admin"))
		{
			this.redirect("/product/list");
		}
		else
		{
			this.render("login.jsp");
		}

	}

}
