package com.cong.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart implements Serializable{
	//购物项的集合:map的key就是商品的pid,value:购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//购物总金额
	private double total;
	//Cart对象中有一个cartItems属性
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}
	//购物车的功能和方法
	//1.添加到购物车
	public void addCart(CartItem cartItem){
		//判断购物车中是否有该商品
		//存在：数量增加  总计 = 总计+购物项小计
		//不存在：向map中添加购物项   总计 = 总计+购物项小计
		Integer pid= cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			//存在 原来的数量+现在的数量
			CartItem cartItem2 = map.get(pid); //原来的购物项目
			cartItem2.setCount(cartItem2.getCount()+cartItem.getCount());
		} else {
			//不存在放入Map中
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//移除购物项目
	public void removeCart(Integer pid){
		//将购物项移除购物车
//		CartItem cartItem=map.get(pid);
//		total -= cartItem.getSubtotal();
		//总计-移除购物车的小计
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//清空购物车
	public void clearCart(){
		
		//将购物项目清空
		map.clear();
		//总计清零
		total = 0;
		
	}
}
