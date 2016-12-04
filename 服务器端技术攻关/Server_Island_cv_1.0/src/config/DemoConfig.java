package config;

import model.Activity;
import model.Classify;
import model.Collect;
import model.FeedBack;
import model.Order;
import model.Product;
import model.Slideshow;
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
import controller.SlideshowController;
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
		System.out.println("更新结束");
	}

	@Override
	public void beforeJFinalStop() {
		System.out.println("更新开始");
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
		me.add("/slideshow", SlideshowController.class);
		me.add("/user", UserController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/islandTrading?useUnicode=true&characterEncoding=UTF-8",
				"root", "");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("product_b", Product.class);
		arp.addMapping("order_b", Order.class);
		arp.addMapping("activity_b", Activity.class);
		arp.addMapping("classify_b", Classify.class);
		arp.addMapping("collect_b", Collect.class);
		arp.addMapping("feedback_b", FeedBack.class);
		arp.addMapping("slideshow_b", Slideshow.class);
		arp.addMapping("user_b", User.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("bashPath"));

	}

}
