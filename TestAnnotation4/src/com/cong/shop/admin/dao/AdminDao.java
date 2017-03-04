package com.cong.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.admin.vo.AdminUser;
import com.cong.shop.util.BaseDao;

@Repository("admonDao")
public class AdminDao extends BaseDao{

	//ºóÌ¨µÇÂ¼
	public AdminUser login(AdminUser adminUser) {
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> list=(List<AdminUser>) hibernateTemplate.find(hql, adminUser.getUsername(), adminUser.getPassword());
		if(list!= null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
}
