/**
 * Function:FeedbackInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class FeedbackInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("FEEDBACK_ID", "idMsg", "请输入反馈ID");
		validateRequiredString("FEEDBACK_CONTENT", "contentMsg", "请输入反馈");
		validateRequiredString("FEEDBACK_CONTACT", "contactMsg", "请输入反馈联系方式");
		validateRequiredString("FEEDBACK_TIME", "timeMsg", "请输入反馈时间");
		validateRequiredString("FEEDBACK_STATUS", "statusMsg", "请输入反馈状态");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("FEEDBACK_ID");
		controller.keepPara("FEEDBACK_CONTENT");
		controller.keepPara("FEEDBACK_CONTACT");
		controller.keepPara("FEEDBACK_TIME");
		controller.keepPara("FEEDBACK_STATUS");

		String actionKey = getActionKey();
		if (actionKey.equals("/feedback/find"))
			controller.render("/addFeedback.jsp");
	}

}
