package com.cong.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart implements Serializable{
	//������ļ���:map��key������Ʒ��pid,value:������
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//�����ܽ��
	private double total;
	//Cart��������һ��cartItems����
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}
	//���ﳵ�Ĺ��ܺͷ���
	//1.��ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//�жϹ��ﳵ���Ƿ��и���Ʒ
		//���ڣ���������  �ܼ� = �ܼ�+������С��
		//�����ڣ���map����ӹ�����   �ܼ� = �ܼ�+������С��
		Integer pid= cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			//���� ԭ��������+���ڵ�����
			CartItem cartItem2 = map.get(pid); //ԭ���Ĺ�����Ŀ
			cartItem2.setCount(cartItem2.getCount()+cartItem.getCount());
		} else {
			//�����ڷ���Map��
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//�Ƴ�������Ŀ
	public void removeCart(Integer pid){
		//���������Ƴ����ﳵ
//		CartItem cartItem=map.get(pid);
//		total -= cartItem.getSubtotal();
		//�ܼ�-�Ƴ����ﳵ��С��
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//��չ��ﳵ
	public void clearCart(){
		
		//��������Ŀ���
		map.clear();
		//�ܼ�����
		total = 0;
		
	}
}
