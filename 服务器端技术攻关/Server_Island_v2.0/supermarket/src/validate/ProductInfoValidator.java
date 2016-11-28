package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProductInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("proID", "idMsg", "请输入ID!");
		validateRequiredString("proName", "nameMsg", "请输入名称!");
		validateRequiredString("proPrice", "priceMsg", "请输入价格!");
		validateInteger("proPrice", "priceMsg", "价格应该为数字!");
		validateRequiredString("proImage", "imageMsg", "请输入图片url!");
		validateRequiredString("proDescribe", "describeMsg", "请输入商品描述!");
		validateRequiredString("proClassify", "classifyMsg", "请输入商品分类!");
		validateRequiredString("proSite", "siteMsg", "请输入商品发布地点!");
		validateRequiredString("proTime", "timeMsg", "请输入商品发布时间!");
	}
	
	protected void handleError(Controller controller) {
		//controller.getModel(Blog.class);
		controller.keepPara("proID");
		controller.keepPara("proName");
		controller.keepPara("proPrice");
		controller.keepPara("proImage");
		controller.keepPara("proDescribe");
		controller.keepPara("proClassify");
		controller.keepPara("proSite");
		controller.keepPara("proTime");
		
		String actionKey = getActionKey();
		if (actionKey.equals("/product/save"))
				controller.render("/addProduct.jsp");
		else if (actionKey.equals("/product/update"))
			controller.render("/editProduct.jsp");
	}

}
