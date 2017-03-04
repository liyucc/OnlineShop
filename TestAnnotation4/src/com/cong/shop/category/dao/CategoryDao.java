package com.cong.shop.category.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.category.vo.Category;
import com.cong.shop.util.BaseDao;

@Repository("categoryDao")
public class CategoryDao extends BaseDao{
	
	//查询所有的一级分类
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = (List<Category>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//后台保存分类
	public void save(Category category) {
		hibernateTemplate.save(category);
	}
	//根据cid查询
	public Category findByCid(Integer cid) {
		return hibernateTemplate.get(Category.class, cid);
	}
	//删除
	public void delete(Category category) {
		hibernateTemplate.delete(category);
	}
	//修改
	public void update(Category category) {
		hibernateTemplate.update(category);
	}

}
