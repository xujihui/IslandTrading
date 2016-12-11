package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ActivityInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("ACTIVITY_ID", "idMsg", "请输入活动ID");
		validateRequiredString("ACTIVITY_CONTENT", "contentMsg", "请输入活动内容");
		validateRequiredString("ACTIVITY_ORGANIZER", "organizerMsg", "请输入活动组织单位");
		validateRequiredString("ACTIVITY_TIME", "timeMsg", "请输入活动开展时间");
		validateRequiredString("ACTIVITY_SITE", "siteMsg", "请输入活动发布位置");
		validateRequiredString("ACTIVITY_NAME", "nameMsg", "请输入活动名称");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("ACTIVITY_ID");
		controller.keepPara("ACTIVITY_CONTENT");
		controller.keepPara("ACTIVITY_ORGANIZER");
		controller.keepPara("ACTIVITY_TIME");
		controller.keepPara("ACTIVITY_SITE");
		controller.keepPara("ACTIVITY_NAME");

		String actionKey = getActionKey();
		if (actionKey.equals("/activity/save"))
			controller.render("/addActivity.jsp");
		else if (actionKey.equals("/activity/update"))
			controller.render("/editActivity.jsp");
		else if (actionKey.equals("/activity/find"))
			controller.render("/findActivity.jsp");
	}
}
