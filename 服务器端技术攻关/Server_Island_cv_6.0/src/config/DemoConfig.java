/**
 * Function:DemoConfig
 * Date:2016.12.11
 * Author:LiuXin
 */
package config;

import model.Activity;
import model.Classify;
import model.Collect;
import model.FeedBack;
import model.Order;
import model.Product;
import model.User;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import controller.ActivityController;
import controller.AnalysisController;
import controller.ClassifyController;
import controller.CollectController;
import controller.FeedbackController;
import controller.ProductController;
import controller.UserController;
import controller.LoginController;
import controller.OrderController;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JSP);

	}

	@Override
	public void afterJFinalStart() {
		System.out.println("JFinal Is Starting...");
	}

	@Override
	public void beforeJFinalStop() {
		System.out.println("JFinal Is Stopping...");
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", LoginController.class);
		me.add("/order", OrderController.class);
		me.add("/product", ProductController.class);
		me.add("/analysis", AnalysisController.class);
		me.add("/activity", ActivityController.class);
		me.add("/classify", ClassifyController.class);
		me.add("/collect", CollectController.class);
		me.add("/feedback", FeedbackController.class);
		me.add("/user", UserController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin(
				"jdbc:mysql://localhost/islandtrading_db?useUnicode=true&characterEncoding=UTF-8", "root", "");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("islandtrading_product", Product.class);
		arp.addMapping("islandtrading_order", Order.class);
		arp.addMapping("islandtrading_activity", Activity.class);
		arp.addMapping("islandtrading_classify", Classify.class);
		arp.addMapping("islandtrading_collect", Collect.class);
		arp.addMapping("islandtrading_feedback", FeedBack.class);
		arp.addMapping("islandtrading_user", User.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("bashPath"));

	}

}
