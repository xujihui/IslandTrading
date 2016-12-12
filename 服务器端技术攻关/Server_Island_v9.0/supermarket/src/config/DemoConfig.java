package config;

import model.Detail;
import model.Order;
import model.Product;

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

import controller.AnalysisController;
import controller.ProductController;
import controller.LoginController;
import controller.OrderController;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JSP);
		me.setBaseUploadPath("D:\\upload");	
		
	}

	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		System.out.println("afterJFinalStart");
	}

	@Override
	public void beforeJFinalStop() {
		// TODO Auto-generated method stub
		System.out.println("beforeJFinalStop");
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/", LoginController.class);
		me.add("/order", OrderController.class);
		me.add("/product", ProductController.class);
		me.add("/analysis", AnalysisController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/islandtrading?useUnicode=true&characterEncoding=UTF-8","root", "");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("islandtrading_product", Product.class);
		arp.addMapping("islandtrading_order", Order.class);
//		arp.addMapping("t_detail", Detail.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new ContextPathHandler("bashPath"));

	}

}
