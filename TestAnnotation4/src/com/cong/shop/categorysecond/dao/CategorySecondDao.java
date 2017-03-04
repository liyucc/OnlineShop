package com.cong.shop.categorysecond.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;

@Repository("categorySecondDao")
public class CategorySecondDao extends BaseDao{

	//查询二级分类的总记录数
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询二级分类
	public List<CategorySecond> findAllByPage(int begin, int limit) {
		String hql= "from CategorySecond order by csid desc";
		List<CategorySecond> list=hibernateTemplate.execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//保存二级分类
	public void save(CategorySecond categorySecond) {
		hibernateTemplate.save(categorySecond);
	}
	//根据csid查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return hibernateTemplate.get(CategorySecond.class, csid);
	}
	public void delete(CategorySecond categorySecond) {
		hibernateTemplate.delete(categorySecond);
	}
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		hibernateTemplate.update(categorySecond);
	}
	//查询所有二级分类
	public List<CategorySecond> findAll() {
		String hql="from CategorySecond";
		List<CategorySecond> list=(List<CategorySecond>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	
}
