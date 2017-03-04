package com.cong.shop.categorysecond.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;

@Repository("categorySecondDao")
public class CategorySecondDao extends BaseDao{

	//��ѯ����������ܼ�¼��
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list=(List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//��ҳ��ѯ��������
	public List<CategorySecond> findAllByPage(int begin, int limit) {
		String hql= "from CategorySecond order by csid desc";
		List<CategorySecond> list=hibernateTemplate.execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	//�����������
	public void save(CategorySecond categorySecond) {
		hibernateTemplate.save(categorySecond);
	}
	//����csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return hibernateTemplate.get(CategorySecond.class, csid);
	}
	public void delete(CategorySecond categorySecond) {
		hibernateTemplate.delete(categorySecond);
	}
	//�޸Ķ�������
	public void update(CategorySecond categorySecond) {
		hibernateTemplate.update(categorySecond);
	}
	//��ѯ���ж�������
	public List<CategorySecond> findAll() {
		String hql="from CategorySecond";
		List<CategorySecond> list=(List<CategorySecond>) hibernateTemplate.find(hql);
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	
}
