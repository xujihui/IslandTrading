package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class FeedbackInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入反馈ID!");
		validateRequiredString("proUser_id", "user_idMsg", "请输入用户ID!");
		validateRequiredString("proContent", "contentMsg", "请输入反馈内容!");
		validateRequiredString("proContact", "contactMsg", "请输入反馈者联系方式!");
		validateRequiredString("proTime", "timeMsg", "请输入反馈时间!");
		validateRequiredString("proStatus", "statusMsg", "请输入反馈状态!");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("proID");
		controller.keepPara("proUser_id");
		controller.keepPara("proContent");
		controller.keepPara("proContact");
		controller.keepPara("proTime");
		controller.keepPara("proStatus");

		String actionKey = getActionKey();
		if (actionKey.equals("/feedback/find"))
			controller.render("/addFeedback.jsp");
	}

}
