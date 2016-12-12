package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class CollectInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("COLLECT_ID", "idMsg", "请输入收藏ID!");
		validateRequiredString("COLLECT_STATUS", "statusMsg", "请输入收藏状态!");
		validateRequiredString("COLLECT_TIME", "timeMsg", "请输入收藏时间!");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("COLLECT_ID");
		controller.keepPara("COLLECT_STATUS");
		controller.keepPara("COLLECT_TIME");

		String actionKey = getActionKey();
		if (actionKey.equals("/collect/update"))
			controller.render("/editCollect.jsp");
		else if (actionKey.equals("/collect/find"))
			controller.render("/findCollect.jsp");
	}

}
