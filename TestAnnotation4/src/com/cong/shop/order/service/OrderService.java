package com.cong.shop.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.order.dao.OrderDao;
import com.cong.shop.order.vo.Order;
import com.cong.shop.order.vo.OrderItem;
import com.cong.shop.util.PageBean;

@Service("orderService")
@Transactional
public class OrderService {
	
	//注入dao
	@Resource
	private OrderDao orderDao;
	//保存订单业务
	public void save(Order order) {
		orderDao.save(order);
	}
	//分页查询我的订单
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		int limit = 5;
		pageBean.setLimit(limit);
		int totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount%limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
	//后台分页查询
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		int limit = 5;
		pageBean.setLimit(limit);
		int totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount%limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
}
