package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import controller.ProductController;
import controller.OrderController;

public class LoginInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller con = inv.getController();
		String akey=inv.getActionKey();
		String method=inv.getMethodName();
		
		String userName = con.getSessionAttr("userName");
		/*System.out.println("(ActionKey,Controller,Method):("
		+con+","+akey+","+method+")");*/
		
		//用户已经登录，且是管理员
		if (userName!=null&&!userName.isEmpty()){
			inv.invoke();
		}
		else{
			con.setSessionAttr("mess", "请先登录");
			con.render("/login.jsp");
		}
	}
}
