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
	//ע��service
	@Resource
	private OrderService orderService;
	//����page
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
	//���ݶ���idȥ��ѯ��������
	public String findOrderItem(){
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//�޸Ķ���״̬
	public String updateState(){
		//1.���ݶ���id��ѯ����
		Order currOrder=orderService.findByOid(order.getOid());
		//2.�޸Ķ���״̬
		currOrder.setState(3);
		orderService.update(currOrder);
		//3.ҳ����ת
		return "updateState";
	}
}
