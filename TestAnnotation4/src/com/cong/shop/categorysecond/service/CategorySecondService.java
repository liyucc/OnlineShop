package com.cong.shop.categorysecond.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.categorysecond.dao.CategorySecondDao;
import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.util.PageBean;

@Service("categorySecondService")
@Transactional
public class CategorySecondService {
	//ע����������dao
	@Resource
	private CategorySecondDao categorySecondDao;
	//��ҳ��ѯ���еĶ�������
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		if(totalCount % limit==0){
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//���ò�ѯ���
		int begin = (page - 1)*limit;
		List<CategorySecond> list=categorySecondDao.findAllByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//�����������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//���ݶ�������id��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//�޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	//��ѯ���ж�������
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
}
