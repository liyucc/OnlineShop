package com.cong.shop.category.adminaction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.category.service.CategoryService;
import com.cong.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨һ������
 *
 */
@Controller("adminCategoryAction")
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	
	//ģ��������������
	private Category category=new Category();
	//ע��һ������service
	@Resource
	private CategoryService categoryService;

	@Override
	public Category getModel() {
		return category;
	}
	
	//��̨��ѯ����һ������
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		//������������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//��̨���һ������
	public String save(){
		//����service����service
		categoryService.save(category);
		return "saveSuccess";
	}
	//��̨ɾ��һ������
	public String delete(){
		//����serviceɾ��һ������
		category =categoryService.findByCid(category.getCid());
		//ɾ��
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//��̨�޸�һ������ҳ����ת
	public String edit(){
		//����cid��ѯ���ݲ���ʾ�ı༭ҳ��
		category=categoryService.findByCid(category.getCid());
		return "editPage";
	}
	//��̨�޸�һ������
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
