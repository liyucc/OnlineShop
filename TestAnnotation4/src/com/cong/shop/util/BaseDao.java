package com.cong.shop.util;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;

public class BaseDao{  
    
	@Resource
	protected HibernateTemplate hibernateTemplate;
}  