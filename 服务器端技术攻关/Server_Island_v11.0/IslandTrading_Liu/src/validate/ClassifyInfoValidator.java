/**
 * Function:ClassifyInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ClassifyInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("CLASSIFY_ID", "idMsg", "请输入分类ID");
		validateRequiredString("CLASSIFY_NAME", "nameMsg", "请输入分类名称");
		validateRequiredString("CLASSIFY_IMAGE", "imageMsg", "请输入分类图片");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("CLASSIFY_ID");
		controller.keepPara("CLASSIFY_NAME");
		controller.keepPara("CLASSIFY_IMAGE");
		String actionKey = getActionKey();
		if (actionKey.equals("/classify/save"))
			controller.render("/addClassify.jsp");
		else if (actionKey.equals("/classify/update"))
			controller.render("/editClassify.jsp");
		else if (actionKey.equals("/classify/find"))
			controller.render("/findClassify.jsp");
	}

}
