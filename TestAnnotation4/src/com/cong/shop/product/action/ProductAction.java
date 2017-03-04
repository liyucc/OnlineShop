package com.cong.shop.product.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.product.service.ProductService;
import com.cong.shop.product.vo.Product;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
		
	//ģ������
	private Product product = new Product();
	//ע��service
	@Resource
	private ProductService productService;
	//ע��һ�������service
	@Resource
	private CategoryService categoryService;
	//���Խ���һ�������cid
	private Integer cid;
	//���Խ��ն��������csid
	private Integer csid;
	
	//���Խ��յ�ǰ��ҳ��
	private int page;
	

	public Product getModel() {
		return product;
	}
	
	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//������Ʒid��ѯ��Ʒ
	public String findById(){
		product = productService.findByPid(product.getPid());
		return "detail";
	}
	//���ݷ����id��ѯ��Ʒ
	public String findByCid(){
		//categoryService.findAll(); ��session���Լ�������
		PageBean<Product> pageBean=productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//���ݶ��������id����ѯ��Ʒ
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
