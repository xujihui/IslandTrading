package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入用户ID!");
		validateRequiredString("proNickname", "nicknameMsg", "请输入用户昵称!");
		validateRequiredString("proUsername", "usernameMsg", "请输入用户账户!");
		validateRequiredString("proPassword", "passwordMsg", "请输入用户密码!");
		validateRequiredString("proGarvatar", "garvatarMsg", "请输入用户头像!");
		validateRequiredString("proPower", "powerMsg", "请输入用户权限!");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("proID");
		controller.keepPara("proNickname");
		controller.keepPara("proUsername");
		controller.keepPara("proPassword");
		controller.keepPara("proGravatar");
		controller.keepPara("proPower");

		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("/addUser.jsp");
		else if (actionKey.equals("/user/update"))
			controller.render("/editUser.jsp");
		else if (actionKey.equals("/user/find"))
			controller.render("/findUser.jsp");
	}

}
