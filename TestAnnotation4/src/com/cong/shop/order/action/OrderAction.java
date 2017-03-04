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
 * ���������Action
 *
 */
@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//ģ���������ղ���
	private Order order = new Order();
	//ע��service
	@Resource
	private OrderService orderService;
	//���շ�ҳ����page
	private Integer page;
	//����֧��ͨ������
	private String pd_FrpId;
	//���ո���ɹ������Ӧ
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
	
	//���ɶ����ķ���
	public String add(){
		//1.�������ݱ��浽���ݿ�
		//�������ݵĲ�ȫ
		order.setOrdertime(new Date());
		order.setState(1); //"1" ��ʾδ����  "2" ��ʾ�Ѹ��δ����  "3"��ʾ�ѷ����� δ�ջ�   "4"��ʾ���׳ɹ�
		//��ù��ﳵ����Ϣ��ȫ������Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("δ������ȹ����");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//�����������û�
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null){
			this.addActionError("��δ��½���޷����ɶ����������ȵ�¼����");
			return "login";
		}
		order.setUser(user);
		//�������úõ�order
		orderService.save(order);
		//2.������������ʾ��ҳ��
		//ͨ��ֵջ��ʾ���ݵ�ҳ�棺��Ϊ��ʾ����ģ���������󣬿���ֱ����ʾ
		//��չ��ﳵ
		cart.clearCart();
		return "addsuccess";
	}
	
	//�ҵĶ����Ĳ�ѯ
	public String findByUid(){
		//�����û���id��ѯ����
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//����service��ѯ
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(), page);
		//����ҳ��������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//���ݶ���id��ѯ�����ķ���
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//Ϊ��������ķ���
	public String payOrder() throws IOException{
		//�޸Ķ���
		Order currorder = orderService.findByOid(order.getOid());
		currorder.setAddr(order.getAddr());
		currorder.setName(order.getName());
		currorder.setPhone(order.getPhone());
		orderService.update(currorder);
		//Ϊ��������
		String p0_Cmd = "Buy"; //ҵ������
		String p1_MerId="10001126856"; //�̻��ı��
		String p2_Order=order.getOid().toString(); // ������
		String p3_Amt= "0.02";   //���
		String p4_Cur="CNY";   //���ױ���
		String p5_Pid="";   //��Ʒ����
		String p6_Pcat="";  //��Ʒ������
		String p7_Pdesc=""; //��Ʒ������
		String p8_Url="http://127.0.0.1:8080/TestAnnotation4/order_callBack.action";    // �̻�����֧���ɹ����ݵĵ�ַ
		String p9_SAF="";    //�ͻ���ַ
		String pa_MP="";  //�̻���չ��Ϣ
		String pd_FrpId=this.pd_FrpId; //֧��ͨ������
		String pr_NeedResponse="1"; //Ӧ�����
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, 
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // ǩ������
		//���ױ�����
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
		
		//�ض����ױ�
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		return NONE;
	}
	//����ɹ���ķ��ص�ת��
	public String callBack(){
		//�޸Ķ�����״̬Ϊ�Ѿ�
		Order cOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		cOrder.setState(2);
		orderService.update(cOrder);
		//��ҳ������ʾ����ɹ�����Ϣ
		this.addActionMessage("��������ɹ��������ı�ţ�"+r6_Order+"����Ľ�"+r3_Amt);
		return "msg";
	}
	//ǰ̨�޸Ķ���״̬
	public String updateState(){
		//1.���ݶ���id��ѯ����
		Order currOrder=orderService.findByOid(order.getOid());
		//2.�޸Ķ���״̬
		currOrder.setState(4);
		orderService.update(currOrder);
		//3.ҳ����ת
		return "updateState";
	}
}
