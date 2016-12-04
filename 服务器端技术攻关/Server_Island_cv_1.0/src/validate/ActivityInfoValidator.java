package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ActivityInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入活动ID!");
		validateRequiredString("proName", "nameMsg", "请输入活动名称!");
		validateRequiredString("proContent", "contentMsg", "请输入活动内容!");
		validateRequiredString("proOrganizer", "origanizerMsg", "请输入活动组织单位!");
		validateRequiredString("proTime", "timeMsg", "请输入活动发布时间!");
		validateRequiredString("proSite", "siteMsg", "请输入活动发布位置!");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("proID");
		controller.keepPara("proName");
		controller.keepPara("proContent");
		controller.keepPara("proOrganizer");
		controller.keepPara("proTime");
		controller.keepPara("proSite");

		String actionKey = getActionKey();
		if (actionKey.equals("/activity/save"))
			controller.render("/addActivity.jsp");
		else if (actionKey.equals("/activity/update"))
			controller.render("/editActivity.jsp");
		else if (actionKey.equals("/activity/find"))
			controller.render("/findActivity.jsp");
	}

}
