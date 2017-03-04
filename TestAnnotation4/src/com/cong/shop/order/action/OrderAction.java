package com.cong.shop.order.action;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.cart.vo.Cart;
import com.cong.shop.cart.vo.CartItem;
import com.cong.shop.order.service.OrderService;
import com.cong.shop.order.vo.Order;
import com.cong.shop.order.vo.OrderItem;
import com.cong.shop.user.vo.User;
import com.cong.shop.util.PageBean;
import com.cong.shop.util.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



/**
 * 订单管理的Action
 *
 */
@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//模型驱动接收参数
	private Order order = new Order();
	//注入service
	@Resource
	private OrderService orderService;
	//接收分页参数page
	private Integer page;
	//接收支付通道编码
	private String pd_FrpId;
	//接收付款成功后的响应
	private String r6_Order;
	private String r3_Amt;
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getR6_Order() {
		return r6_Order;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public String getR3_Amt() {
		return r3_Amt;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public Order getModel() {
		return order;
	}
	
	//生成订单的方法
	public String add(){
		//1.保存数据保存到数据库
		//订单数据的补全
		order.setOrdertime(new Date());
		order.setState(1); //"1" 表示未付款  "2" 表示已付款，未发货  "3"表示已发货， 未收货   "4"表示交易成功
		//获得购物车的信息补全订单信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("未购物，请先购物！！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//订单所属的用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null){
			this.addActionError("还未登陆，无法生成订单，请您先登录！！");
			return "login";
		}
		order.setUser(user);
		//保存设置好的order
		orderService.save(order);
		//2.将订单数据显示到页面
		//通过值栈显示数据到页面：因为显示的是模型驱动对象，可以直接显示
		//清空购物车
		cart.clearCart();
		return "addsuccess";
	}
	
	//我的订单的查询
	public String findByUid(){
		//根据用户的id查询订单
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//调用service查询
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(), page);
		//将分页的数据显示到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//根据订单id查询订单的方法
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//为订单付款的方法
	public String payOrder() throws IOException{
		//修改订单
		Order currorder = orderService.findByOid(order.getOid());
		currorder.setAddr(order.getAddr());
		currorder.setName(order.getName());
		currorder.setPhone(order.getPhone());
		orderService.update(currorder);
		//为订单付款
		String p0_Cmd = "Buy"; //业务类型
		String p1_MerId="10001126856"; //商户的编号
		String p2_Order=order.getOid().toString(); // 订单号
		String p3_Amt= "0.02";   //金额
		String p4_Cur="CNY";   //交易币种
		String p5_Pid="";   //商品名称
		String p6_Pcat="";  //商品的种类
		String p7_Pdesc=""; //商品的描述
		String p8_Url="http://127.0.0.1:8080/TestAnnotation4/order_callBack.action";    // 商户接收支付成功数据的地址
		String p9_SAF="";    //送货地址
		String pa_MP="";  //商户扩展信息
		String pd_FrpId=this.pd_FrpId; //支付通道编码
		String pr_NeedResponse="1"; //应答机制
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, 
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // 签名数据
		//向易宝触发
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("keyValue=").append(keyValue).append("&");
		stringBuffer.append("hmac=").append(hmac);
		
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		return NONE;
	}
	//付款成功后的返回的转向
	public String callBack(){
		//修改订单的状态为已经
		Order cOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		cOrder.setState(2);
		orderService.update(cOrder);
		//在页面上显示付款成功的信息
		this.addActionMessage("订单付款成功：订单的编号："+r6_Order+"付款的金额："+r3_Amt);
		return "msg";
	}
	//前台修改订单状态
	public String updateState(){
		//1.根据订单id查询订单
		Order currOrder=orderService.findByOid(order.getOid());
		//2.修改订单状态
		currOrder.setState(4);
		orderService.update(currOrder);
		//3.页面跳转
		return "updateState";
	}
}
