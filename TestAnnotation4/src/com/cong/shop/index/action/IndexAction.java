package com.cong.shop.index.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.cong.shop.product.service.ProductService;
import com.cong.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{

	//ע��һ�������service
	@Resource
	private CategoryService categoryService;
	//ע����Ʒ��service
	@Resource 
	private ProductService productService;
	
	public String execute() throws Exception {
		//��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();
		//��ֵ�����session�У���Ϊÿ��ҳ�涼�õ�
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
}
