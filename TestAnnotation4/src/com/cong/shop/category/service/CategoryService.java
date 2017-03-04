package com.cong.shop.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.category.dao.CategoryDao;
import com.cong.shop.category.vo.Category;


@Transactional
@Service("categoryService")
public class CategoryService {
	
	//注入Dao
	@Resource
	private CategoryDao categoryDao;

	//查询所有的一级分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//后台保存一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}
	//根据cid查询
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	//删除
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//修改
	public void update(Category category) {
		categoryDao.update(category);
	}

}
