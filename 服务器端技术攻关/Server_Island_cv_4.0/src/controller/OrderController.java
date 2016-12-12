package controller;

import java.util.List;

import interceptor.LoginInterceptor;
import service.OrderBiz;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

@Before(LoginInterceptor.class)
public class OrderController extends Controller {
	OrderBiz orderService = this.enhance(OrderBiz.class);

	public void detail() {
		String oid = this.getPara(0);
		List<Record> detailList = orderService.findDetailByID(oid);
		this.setSessionAttr("detailList", detailList);
		this.render("/orderDetail.jsp");
	}

	public void list() {
		List<Record> orderlist = orderService.findAll();
		this.setSessionAttr("orderlist", orderlist);
		this.render("/orderList.jsp");
	}

	public void findByID() {
		String oid = this.getPara("id");
		Record order = orderService.findByID(oid);
		this.setSessionAttr("order", order);
		this.render("/findOrder.jsp");
	}
}
