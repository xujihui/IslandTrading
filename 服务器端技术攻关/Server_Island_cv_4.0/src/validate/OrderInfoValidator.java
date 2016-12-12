package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class OrderInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("ORDER_ID", "idMsg", "请输入订单ID");
		validateRequiredString("ORDER_SITE", "siteMsg", "请输入订单位置");
		validateRequiredString("ORDER_TIME", "timeMsg", "请输入订单时间");
		validateRequiredString("ORDER_STATUS", "statusMsg", "请输入订单状态");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("ORDER_ID");
		controller.keepPara("ORDER_SITE");
		controller.keepPara("ORDER_TIME");
		controller.keepPara("ORDER_STATUS");

		String actionKey = getActionKey();
		if (actionKey.equals("/order/find"))
			controller.render("/findOrder.jsp");
	}
}
