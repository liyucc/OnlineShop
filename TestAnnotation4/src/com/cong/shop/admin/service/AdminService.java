package com.cong.shop.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.admin.dao.AdminDao;
import com.cong.shop.admin.vo.AdminUser;

@Transactional
@Service("adminService")
public class AdminService {
	//ע��dao
	@Resource
	private AdminDao adminDao;
	
	//��̨��½
	public AdminUser login(AdminUser adminUser) {
		return adminDao.login(adminUser);
	}
}
