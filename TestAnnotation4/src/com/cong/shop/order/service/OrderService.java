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
	
	//ע��dao
	@Resource
	private OrderDao orderDao;
	//���涩��ҵ��
	public void save(Order order) {
		orderDao.save(order);
	}
	//��ҳ��ѯ�ҵĶ���
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
	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//�޸Ķ���
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
	//��̨��ҳ��ѯ
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
	//���ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
}
