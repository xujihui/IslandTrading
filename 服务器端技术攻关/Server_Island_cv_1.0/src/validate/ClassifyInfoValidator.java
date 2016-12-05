package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ClassifyInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入ID!");
		validateRequiredString("proType", "typeMsg", "请输入商品分类!");
		validateRequiredString("proImage", "imageMsg", "请输入商品图片!");
	}

	protected void handleError(Controller controller) {
		// controller.getModel(Blog.class);
		controller.keepPara("proID");
		controller.keepPara("proType");
		controller.keepPara("proImage");
		String actionKey = getActionKey();
		if (actionKey.equals("/classify/save"))
			controller.render("/addClassify.jsp");
		else if (actionKey.equals("/classify/update"))
			controller.render("/editClassify.jsp");
		else if (actionKey.equals("/classify/find"))
			controller.render("/findClassify.jsp");
	}

}
