package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class CollectInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proProduct_id", "product_idMsg", "请输入商品ID!");
		validateRequiredString("proUser_id", "user_idMsg", "请输入用户ID!");
		validateRequiredString("proStatus", "statusMsg", "请输入收藏状态!");
		validateRequiredString("proDate", "dateMsg", "请输入收藏日期!");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("proProduct_id");
		controller.keepPara("proUser_id");
		controller.keepPara("proStatus");
		controller.keepPara("proDate");

		String actionKey = getActionKey();
		if (actionKey.equals("/collect/update"))
			controller.render("/editCollect.jsp");
		else if (actionKey.equals("/collect/find"))
			controller.render("/findCollect.jsp");
	}

}
