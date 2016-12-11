/**
 * Function:UserInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("USER_ID", "idMsg", "请输入用户ID!");
		validateRequiredString("USER_NICKNAME", "nicknameMsg", "请输入用户昵称!");
		validateRequiredString("USER_USERNAME", "usernameMsg", "请输入用户账户!");
		validateRequiredString("USER_PASSWORD", "passwordMsg", "请输入用户密码!");
		validateRequiredString("UsSER_IMAGE", "imageMsg", "请输入用户头像!");
		validateRequiredString("USER_POWER", "powerMsg", "请输入用户权限!");
		validateRequiredString("USER_TAKINGID", "takingidMsg", "请输入用户环信ID!");
		validateRequiredString("USER_TEL", "telMsg", "请输入用户电话!");
		validateRequiredString("HX_USERNAME", "hx_usernameMsg", "请输入环信用户账户!");
		validateRequiredString("HX_PASSWORD", "hx_passwordMsg", "请输入环信用户密码!");

	}

	protected void handleError(Controller controller) {
		controller.keepPara("USER_ID");
		controller.keepPara("USER_NICKNAME");
		controller.keepPara("USER_USERNAME");
		controller.keepPara("USER_PASSWORD");
		controller.keepPara("USER_IMAGE");
		controller.keepPara("USER_POWER");
		controller.keepPara("USER_TAKINGID");
		controller.keepPara("USER_TEL");
		controller.keepPara("HX_USERNAME");
		controller.keepPara("HX_PASSWORD");

		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("/addUser.jsp");
		else if (actionKey.equals("/user/update"))
			controller.render("/editUser.jsp");
		else if (actionKey.equals("/user/find"))
			controller.render("/findUser.jsp");
	}

}
