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
	//首页查询热门商品
	public List<Product> findHot() {
		String hql = "from Product p where p.is_hot = ?  order by p.pdate desc";
		//使用分页查询
		List<Product> list =hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{1}, 0, 10));
		if(list != null && list.size() > 0){
			for (Product product : list) {
				System.out.println(product.getImage());
			}
			return list;
		}

		return null;
	}
	//首页最新商品
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
	//根据商品id查询商品信息
	public Product findByPid(Integer pid) {
		String hql = "from Product p where p.pid=?";
		List<Product> list = (List<Product>) hibernateTemplate.find(hql, pid);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//根据一级分类id查询所属总记录
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, cid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据一级id查询所属结果集
	public List<Product> findByPageCid(Integer cid, int limit, int begin) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//根据二级分类id查询所属总记录
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, csid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根绝二级分类id产寻所属结果集
	public List<Product> findByPageCsid(Integer csid, int limit, int begin) {
		String hql = "select p from Product p join p.categorySecond cs  where cs.csid=?";
		List<Product> list=hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//查询所有商品记录
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
	//保存商品
	public void save(Product product) {
		hibernateTemplate.save(product);
	}
	//删除商品
	public void delete(Product product) {
		hibernateTemplate.delete(product);
	}
	//修改商品
	public void update(Product product) {
		hibernateTemplate.update(product);
	}
	
}
