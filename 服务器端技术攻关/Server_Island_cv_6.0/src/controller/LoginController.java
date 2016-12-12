/**
 * Function:LoginController
 * Date:2016.12.11
 * Author:LiuXin
 */
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
		String userPwd = this.getPara("userPwd");

		this.setAttr("userName", userName);
		this.setAttr("userPwd", userPwd);
		this.setSessionAttr("userName", userName);
		if (userName.equals("admin") && userPwd.equals("admin")) {
			this.redirect("/product/list");
		} else {
			this.render("login.jsp");
		}

	}

}
