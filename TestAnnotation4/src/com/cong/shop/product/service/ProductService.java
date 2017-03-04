package com.cong.shop.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.product.dao.ProductDao;
import com.cong.shop.product.vo.Product;
import com.cong.shop.util.PageBean;

@Transactional
@Service("productService")
public class ProductService {
	//注入dao
	@Resource
	private ProductDao productDao;
	//热门商品的查询
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//查询最新商品
	public List<Product> findNew() {
		return productDao.findNew();
	}
	//根据商品id查询商品信息
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//根据一级分类带分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置查询起点
		int begin= (page -1) *limit;
		//设置当前页的结果集
		List<Product> list = productDao.findByPageCid(cid,limit, begin);
		pageBean.setList(list);
		return pageBean;
	}
	//根据二级分类带分页查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit=12;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit + 1;
		}
		//设置查询起点
		int begin = (page-1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, limit, begin);
		pageBean.setList(list);
		return pageBean;
	}
	//后台查询带分页
	public PageBean<Product> findByPage(Integer page) {
		//设置当前页
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount %limit ==0){
			totalPage = totalCount /limit;
		}else {
			totalPage = totalCount /limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置查询起点
		int begin= (page-1)*limit;
		//设置结果集
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//保存商品
	public void save(Product product) {
		productDao.save(product);
	}
	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}
	//修改商品
	public void update(Product product) {
		productDao.update(product);
	}
	

}
