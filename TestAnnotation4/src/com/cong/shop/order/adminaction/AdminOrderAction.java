package com.cong.shop.order.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.order.service.OrderService;
import com.cong.shop.order.vo.Order;
import com.cong.shop.order.vo.OrderItem;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("adminOrderAction")
@Scope("prototype")
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	//注入service
	@Resource
	private OrderService orderService;
	//接收page
	private Integer page;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String findAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//根据订单id去查询订单详情
	public String findOrderItem(){
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//修改订单状态
	public String updateState(){
		//1.根据订单id查询订单
		Order currOrder=orderService.findByOid(order.getOid());
		//2.修改订单状态
		currOrder.setState(3);
		orderService.update(currOrder);
		//3.页面跳转
		return "updateState";
	}
}
