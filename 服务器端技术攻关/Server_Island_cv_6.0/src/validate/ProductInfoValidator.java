/**
 * Function:ProductInfoValidator
 * Date:2016.12.11
 * Author:LiuXin
 */
package validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProductInfoValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("PRODUCT_ID", "IDMsg", "请输入商品ID!");
		validateRequiredString("PRODUCT_NAME", "nameMsg", "请输入商品名称!");
		validateRequiredString("PRODUCT_PRICE", "priceMsg", "请输入商品价格!");
		validateRequiredString("PRODUCT_DESCRIBE", "describeMsg", "请输入商品描述!");
		validateRequiredString("PRODUCT_IMAGE", "iamgeMsg", "请上传商品图片!");
		validateRequiredString("PRODUCT_TIME", "timeMsg", "请输入商品发布时间!");
		validateRequiredString("PRODUCT_SITE", "siteMsg", "请输入商品发布位置!");
		validateRequiredString("PRODUCT_VIEW", "viewMsg", "请输入商品浏览量!");
		validateRequiredString("PRODUCT_POSITIVE", "positiveMsg", "请输入商品点赞量!");
		validateRequiredString("PRODUCT_STATUS", "statusMsg", "请输入商品状态!");
		validateRequiredString("PRODUCT_MESSAGE", "messageMsg", "请输入商品留言!");
	}
	
	protected void handleError(Controller controller) {
		//controller.getModel(Blog.class);
		controller.keepPara("PRODUCT_ID");
		controller.keepPara("PRODUCT_NAME");
		controller.keepPara("PRODUCT_PRICE");
		controller.keepPara("PRODUCT_DESCRIBE");
		controller.keepPara("PRODUCT_IMAGE");
		controller.keepPara("PRODUCT_TIME");
		controller.keepPara("PRODUCT_SITE");
		controller.keepPara("PRODUCT_VIEW");
		controller.keepPara("PRODUCT_POSITIVE");
		controller.keepPara("PRODUCT_STATUS");
		controller.keepPara("PRODUCT_MESSAGE");
		
		String actionKey = getActionKey();
		if (actionKey.equals("/product/save"))
				controller.render("/addProduct.jsp");
		else if (actionKey.equals("/product/update"))
			controller.render("/editProduct.jsp");
		else if (actionKey.equals("/product/find"))
		controller.render("/findProduct.jsp");
	}

}
