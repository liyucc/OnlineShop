package com.cong.shop.cart.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.cart.vo.Cart;
import com.cong.shop.cart.vo.CartItem;
import com.cong.shop.product.service.ProductService;
import com.cong.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;
@Controller("cartAction")
@Scope("prototype")
public class CartAction extends ActionSupport{
	//����pid
	private Integer pid;
	//����count
	private Integer count;
	//ע��productservice
	@Resource
	private ProductService productService;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	//��������Ŀ��ӵ����ﳵ
	public String addCart(){
		//��װһ��CartItem
		CartItem cartItem = new CartItem();
		//����С��
		cartItem.setCount(count);
		//����pid��ѯ��Ʒ��Ϣ
		Product product = productService.findByPid(pid);
		//������Ʒ
		cartItem.setProduct(product);
		//����������ӵ����ﳵ
		Cart cart = getCart(); //��ù��ﳵ
		cart.addCart(cartItem); //����������ӵ����ﳵ
		return "addCart";
	}
	
	public String clearCart(){
		//��ù��ﳵ����
		Cart cart = getCart();
		//���ù��ﳵ������յķ���
		cart.clearCart();
		return "clearCart";
	}
	
	public String deleteCart(){
		//��ù��ﳵ����
		Cart cart = getCart();
		//���ù��ﳵ������յķ���
		cart.removeCart(pid);
		return "deleteCart";
	}
	
	//�ҵĹ��ﳵ
	public String myCart(){
		return "myCart";
	}
	//��ù��ﳵ�ķ���
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
			.setAttribute("cart", cart);
		}
		return cart;
	}
	
}
