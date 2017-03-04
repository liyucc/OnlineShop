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
	//接收pid
	private Integer pid;
	//接收count
	private Integer count;
	//注入productservice
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


	//将购物项目添加到购物车
	public String addCart(){
		//封装一个CartItem
		CartItem cartItem = new CartItem();
		//设置小计
		cartItem.setCount(count);
		//根据pid查询商品信息
		Product product = productService.findByPid(pid);
		//设置商品
		cartItem.setProduct(product);
		//将购物项添加到购物车
		Cart cart = getCart(); //获得购物车
		cart.addCart(cartItem); //将购物项添加到购物车
		return "addCart";
	}
	
	public String clearCart(){
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车本身清空的方法
		cart.clearCart();
		return "clearCart";
	}
	
	public String deleteCart(){
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车本身清空的方法
		cart.removeCart(pid);
		return "deleteCart";
	}
	
	//我的购物车
	public String myCart(){
		return "myCart";
	}
	//获得购物车的方法
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
