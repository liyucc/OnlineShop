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
	//ע��dao
	@Resource
	private ProductDao productDao;
	//������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//��ѯ������Ʒ
	public List<Product> findNew() {
		return productDao.findNew();
	}
	//������Ʒid��ѯ��Ʒ��Ϣ
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//����һ���������ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit=8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//���ò�ѯ���
		int begin= (page -1) *limit;
		//���õ�ǰҳ�Ľ����
		List<Product> list = productDao.findByPageCid(cid,limit, begin);
		pageBean.setList(list);
		return pageBean;
	}
	//���ݶ����������ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit=12;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit + 1;
		}
		//���ò�ѯ���
		int begin = (page-1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, limit, begin);
		pageBean.setList(list);
		return pageBean;
	}
	//��̨��ѯ����ҳ
	public PageBean<Product> findByPage(Integer page) {
		//���õ�ǰҳ
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 5;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount %limit ==0){
			totalPage = totalCount /limit;
		}else {
			totalPage = totalCount /limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//���ò�ѯ���
		int begin= (page-1)*limit;
		//���ý����
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//������Ʒ
	public void save(Product product) {
		productDao.save(product);
	}
	//ɾ����Ʒ
	public void delete(Product product) {
		productDao.delete(product);
	}
	//�޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
	}
	

}
