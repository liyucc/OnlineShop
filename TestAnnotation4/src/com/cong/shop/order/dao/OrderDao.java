package com.cong.shop.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.order.vo.Order;
import com.cong.shop.order.vo.OrderItem;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;


/**
 * 订单模块的持久层
 *
 */
@Repository("orderDao")
public class OrderDao extends BaseDao{
	
	//保存订单的持久层方法
	public void save(Order order) {
		hibernateTemplate.save(order);
	}
	//我的订单的个数统计
	public int findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql, uid);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//我的订单的分页查询
	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		String hql="from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list=hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		return hibernateTemplate.get(Order.class, oid);
	}
	//修改订单
	public void update(Order currOrder) {
		hibernateTemplate.update(currOrder);
	}
	//查询订单总记录
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询订单
	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	//订单项查询
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=(List<OrderItem>) hibernateTemplate.find(hql, oid);
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	
}
