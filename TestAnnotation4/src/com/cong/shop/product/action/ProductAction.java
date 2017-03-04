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
		
	//模型驱动
	private Product product = new Product();
	//注入service
	@Resource
	private ProductService productService;
	//注入一级分类的service
	@Resource
	private CategoryService categoryService;
	//属性接收一级分类的cid
	private Integer cid;
	//属性接收二级分类的csid
	private Integer csid;
	
	//属性接收当前的页数
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

	//根据商品id查询商品
	public String findById(){
		product = productService.findByPid(product.getPid());
		return "detail";
	}
	//根据分类的id查询商品
	public String findByCid(){
		//categoryService.findAll(); 在session中以及保存有
		PageBean<Product> pageBean=productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//根据二级分类的id来查询商品
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
