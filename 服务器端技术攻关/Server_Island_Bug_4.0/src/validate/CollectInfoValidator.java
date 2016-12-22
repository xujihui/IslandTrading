/**
 * Function:CollectInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class CollectInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("COLLECT_STATUS", "statusMsg", "«Î ‰»Î ’≤ÿ◊¥Ã¨");
	}

	protected void handleError(Controller controller) {
		controller.keepPara("COLLECT_STATUS");

		String actionKey = getActionKey();
		if (actionKey.equals("/collect/update"))
			controller.render("/editCollect.jsp");
		else if (actionKey.equals("/collect/find"))
			controller.render("/findCollect.jsp");
	}

}
