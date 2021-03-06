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
	//注入二级分类的dao
	@Resource
	private CategorySecondDao categorySecondDao;
	//分页查询所有的二级分类
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount % limit==0){
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置查询起点
		int begin = (page - 1)*limit;
		List<CategorySecond> list=categorySecondDao.findAllByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//保存二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//根据二级分类id查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	//查询所有二级分类
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
}
