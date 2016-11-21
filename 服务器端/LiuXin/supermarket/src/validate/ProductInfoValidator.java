package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProductInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入商品ID!");
		validateRequiredString("proName", "nameMsg", "请输入商品名!");
		validateRequiredString("proPrice", "priceMsg", "请输入产品价格!");
		validateInteger("proPrice", "priceMsg", "商品价格应该为数字!");
	}
	
	protected void handleError(Controller controller) {
		//controller.getModel(Blog.class);
		controller.keepPara("proID");
		controller.keepPara("proName");
		controller.keepPara("proPrice");
		
		String actionKey = getActionKey();
		if (actionKey.equals("/product/save"))
				controller.render("/addProduct.jsp");
		else if (actionKey.equals("/product/update"))
			controller.render("/editProduct.jsp");
	}

}
