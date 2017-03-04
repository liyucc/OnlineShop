package com.cong.shop.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.order.vo.Order;
import com.cong.shop.order.vo.OrderItem;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;


/**
 * ����ģ��ĳ־ò�
 *
 */
@Repository("orderDao")
public class OrderDao extends BaseDao{
	
	//���涩���ĳ־ò㷽��
	public void save(Order order) {
		hibernateTemplate.save(order);
	}
	//�ҵĶ����ĸ���ͳ��
	public int findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql, uid);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//�ҵĶ����ķ�ҳ��ѯ
	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		String hql="from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list=hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return hibernateTemplate.get(Order.class, oid);
	}
	//�޸Ķ���
	public void update(Order currOrder) {
		hibernateTemplate.update(currOrder);
	}
	//��ѯ�����ܼ�¼
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//��ҳ��ѯ����
	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	//�������ѯ
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=(List<OrderItem>) hibernateTemplate.find(hql, oid);
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}
	
}
