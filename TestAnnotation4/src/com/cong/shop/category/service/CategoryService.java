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
	
	//ע��Dao
	@Resource
	private CategoryDao categoryDao;

	//��ѯ���е�һ������
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//��̨����һ������
	public void save(Category category) {
		categoryDao.save(category);
	}
	//����cid��ѯ
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	//ɾ��
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//�޸�
	public void update(Category category) {
		categoryDao.update(category);
	}

}
