package com.cong.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cong.shop.category.vo.Category;
import com.cong.shop.product.vo.Product;
@Entity
@Table(name="categorysecond")
public class CategorySecond implements Serializable{
	
	private Integer csid;
	private String csname;
	//所属的一级分类
	private Category category;
	//商品
	private Set<Product> products = new HashSet<Product>();
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="csid")
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	@Column(name="csname")
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cid")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "categorySecond")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
