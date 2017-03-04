package com.cong.shop.categorysecond.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.cong.shop.categorysecond.service.CategorySecondService;
import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨���������Action
 *
 */
@Controller("adminCategorySecondAction")
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	
	//ģ���������ղ���
	private CategorySecond categorySecond = new CategorySecond();
	//ע����������service
	@Resource
	private CategorySecondService categorySecondService;
	//ע��һ������service
	@Resource
	private CategoryService categoryService;
	//����ǰ̨��������ҳ��
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}
	
	//��ѯ���еĶ�������
	public String findAll(){
		//����ҳ��ѯ���еĶ�������
		PageBean<CategorySecond> pageBean = categorySecondService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//�������������ת
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	//�����������
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//ɾ����������
	public String delete(){
		//�������ɾ��������cascade
	 categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
	 categorySecondService.delete(categorySecond);
	 return "deleteSuccess";
	}
	
	//�޸Ķ�������ҳ����ת
	public String edit(){
		//�������ɾ��������cascade
	 categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
	 //��ѯ����һ������
	 List<Category> cList = categoryService.findAll();
	 ActionContext.getContext().getValueStack().set("cList", cList);
	 return "editPage";
	}
	//�޸Ķ�������
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
