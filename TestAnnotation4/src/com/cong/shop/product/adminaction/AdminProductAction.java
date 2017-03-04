package com.cong.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cong.shop.categorysecond.service.CategorySecondService;
import com.cong.shop.categorysecond.vo.CategorySecond;
import com.cong.shop.product.service.ProductService;
import com.cong.shop.product.vo.Product;
import com.cong.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("adminProductAction")
@Scope("prototype")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	//ģ������������Ʒ����
	private Product product = new Product();
	//ע��service
	@Resource
	private ProductService productService;
	//����page����
	private Integer page;
	//ע���������service
	@Resource
	private CategorySecondService categorySecondService;
	//�ļ��ϴ�����
	private File upload; //�ϴ����ļ�
	private String uploadFileName; //�����ļ��ϴ����ļ���
	private String uploadContextType; //�����ļ��ϴ����ļ�����
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContextType() {
		return uploadContextType;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Product getModel() {
		return product;
	}
	//��ѯ������Ʒ�ķ���
	public String findAll(){
		//����service��ɲ�ѯ����
		PageBean<Product> pageBean=productService.findByPage(page);
		//�����ݴ��ݵ�ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//�����Ʒҳ����ת
	public String addPage(){
		//��ѯ���еĶ�������
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	//������Ʒ
	public String save() throws IOException{
		//����service������Ʒ
		product.setPdate(new Date());
		if(upload != null){
			//��ô��̾���·��
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile= new File(realPath+"//"+uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	//ɾ����Ʒ
	public String delete(){
		//��ѯ��Ʒ
		product=productService.findByPid(product.getPid());
		//ɾ����Ʒ�ϴ���ͼƬ
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext
					.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
		}
		//ɾ����Ʒ
		productService.delete(product);
		return "deleteSuccess";
	}
	//�޸���Ʒҳ����ת
	public String edit(){
		//��ѯ��Ʒ
		product=productService.findByPid(product.getPid());
		//��ѯ���ж�������
		List<CategorySecond> csList= categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	//�޸���Ʒ
	public String update() throws IOException{
		product.setPdate(new Date());
		if(upload != null){
			//ɾ��ԭ��ͼƬ
			String path = product.getImage();
			String realPath=ServletActionContext
					.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
			//�ϴ���ͼƬ
			//����ļ��ϴ��Ĵ��̾���·��
			String realPaths=ServletActionContext.getServletContext().getRealPath("/products");
			File deskFile = new File(realPaths+"\\"+uploadFileName);
			FileUtils.copyFile(upload, deskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "editSuccess";
	}
}
