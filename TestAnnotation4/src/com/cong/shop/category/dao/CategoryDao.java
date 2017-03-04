package com.cong.shop.category.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.category.vo.Category;
import com.cong.shop.util.BaseDao;

@Repository("categoryDao")
public class CategoryDao extends BaseDao{
	
	//��ѯ���е�һ������
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = (List<Category>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//��̨�������
	public void save(Category category) {
		hibernateTemplate.save(category);
	}
	//����cid��ѯ
	public Category findByCid(Integer cid) {
		return hibernateTemplate.get(Category.class, cid);
	}
	//ɾ��
	public void delete(Category category) {
		hibernateTemplate.delete(category);
	}
	//�޸�
	public void update(Category category) {
		hibernateTemplate.update(category);
	}

}
