package com.cong.shop.cart.vo;

import java.io.Serializable;

import com.cong.shop.product.vo.Product;

public class CartItem implements Serializable{
	private Product product;
	private int count;		//����ĳ����Ʒ������
	private double subtotal; //��Ʒ�۸�С��
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return count*product.getShop_price();
	}

	
} 
