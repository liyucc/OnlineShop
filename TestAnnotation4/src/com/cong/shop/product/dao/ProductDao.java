package com.cong.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cong.shop.product.vo.Product;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;

@Repository("productDao")
public class ProductDao extends BaseDao{
	//��ҳ��ѯ������Ʒ
	public List<Product> findHot() {
		String hql = "from Product p where p.is_hot = ?  order by p.pdate desc";
		//ʹ�÷�ҳ��ѯ
		List<Product> list =hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{1}, 0, 10));
		if(list != null && list.size() > 0){
			for (Product product : list) {
				System.out.println(product.getImage());
			}
			return list;
		}

		return null;
	}
	//��ҳ������Ʒ
	public List<Product> findNew() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(criteria, 0, 10);
		if(list != null && list.size() > 0 ){
			for (Product product : list) {
				System.out.println(product.getImage()+"..");
			}
			return list;
		}
		return null;
	}
	//������Ʒid��ѯ��Ʒ��Ϣ
	public Product findByPid(Integer pid) {
		String hql = "from Product p where p.pid=?";
		List<Product> list = (List<Product>) hibernateTemplate.find(hql, pid);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//����һ������id��ѯ�����ܼ�¼
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, cid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//����һ��id��ѯ���������
	public List<Product> findByPageCid(Integer cid, int limit, int begin) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//���ݶ�������id��ѯ�����ܼ�¼
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, csid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//������������id��Ѱ���������
	public List<Product> findByPageCsid(Integer csid, int limit, int begin) {
		String hql = "select p from Product p join p.categorySecond cs  where cs.csid=?";
		List<Product> list=hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//��ѯ������Ʒ��¼
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	public List<Product> findByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list = hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//������Ʒ
	public void save(Product product) {
		hibernateTemplate.save(product);
	}
	//ɾ����Ʒ
	public void delete(Product product) {
		hibernateTemplate.delete(product);
	}
	//�޸���Ʒ
	public void update(Product product) {
		hibernateTemplate.update(product);
	}
	
}
