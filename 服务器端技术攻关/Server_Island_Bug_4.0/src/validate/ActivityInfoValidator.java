/**
 * Function:ActivityInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ActivityInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("ACTIVITY_CONTENT", "contentMsg", "请输入活动内容!");
		validateRequiredString("ACTIVITY_ORGANIZER", "organizerMsg", "请输入活动组织单位!");
		validateRequiredString("ACTIVITY_SITE", "siteMsg", "请输入活动发布位置!");
		validateRequiredString("ACTIVITY_NAME", "nameMsg", "请输入活动名称!");
		validateRequiredString("ACTIVITY_IMG", "imgMsg", "请输入活动详情图!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepPara("ACTIVITY_CONTENT");
		controller.keepPara("ACTIVITY_ORGANIZER");
		controller.keepPara("ACTIVITY_SITE");
		controller.keepPara("ACTIVITY_NAME");
		controller.keepPara("ACTIVITY_IMG");

		String actionKey = getActionKey();
		if (actionKey.equals("/activity/save"))
			controller.render("/addActivity.jsp");
		else if (actionKey.equals("/activity/update"))
			controller.render("/editActivity.jsp");
		else if (actionKey.equals("/activity/find"))
			controller.render("/findActivity.jsp");
	}
}
