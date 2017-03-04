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
	//模型驱动接收商品参数
	private Product product = new Product();
	//注入service
	@Resource
	private ProductService productService;
	//接收page参数
	private Integer page;
	//注入二级分类service
	@Resource
	private CategorySecondService categorySecondService;
	//文件上传参数
	private File upload; //上传的文件
	private String uploadFileName; //接收文件上传的文件名
	private String uploadContextType; //接收文件上传的文件类型
	
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
	//查询所以商品的方法
	public String findAll(){
		//调用service完成查询操作
		PageBean<Product> pageBean=productService.findByPage(page);
		//将数据传递到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//添加商品页面跳转
	public String addPage(){
		//查询所有的二级分类
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	//保存商品
	public String save() throws IOException{
		//调用service保存商品
		product.setPdate(new Date());
		if(upload != null){
			//获得磁盘绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile= new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	//删除商品
	public String delete(){
		//查询商品
		product=productService.findByPid(product.getPid());
		//删除商品上传的图片
		String path=product.getImage();
		if(path != null){
			String realPath=ServletActionContext
					.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	//修改商品页面跳转
	public String edit(){
		//查询商品
		product=productService.findByPid(product.getPid());
		//查询所有二级分类
		List<CategorySecond> csList= categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	//修改商品
	public String update() throws IOException{
		product.setPdate(new Date());
		if(upload != null){
			//删除原来图片
			String path = product.getImage();
			String realPath=ServletActionContext
					.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
			//上传新图片
			//获得文件上传的磁盘绝对路径
			String realPaths=ServletActionContext.getServletContext().getRealPath("/products");
			File deskFile = new File(realPaths+"\\"+uploadFileName);
			FileUtils.copyFile(upload, deskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "editSuccess";
	}
}
