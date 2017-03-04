package com.cong.shop.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cong.shop.user.dao.UserDao;
import com.cong.shop.user.vo.User;
import com.cong.shop.util.MailUtils;
import com.cong.shop.util.PageBean;
import com.cong.shop.util.UUIDUtils;

@Transactional
@Service("userService")
public class UserService{
	
	//注入持久层userDao
	@Resource
	private UserDao userDao;
	//根据姓名查询User
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	//保存注册的用户
	public void save(User user) {
		user.setState(0); // 0代表未激活， 1代表已经激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}
	//根据激活码查询是否存在该用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//激活成功将激活状态改为1，激活码清零
	public void updateState(User exist) {
		userDao.updateState(exist);
	}
	//用户登录的业务
	public User login(String username, String password) {
		return userDao.login(username, password);
	}
	//分页查询所有用户
	public PageBean<User> findAllByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置查询起点
		int begin=(page-1)*limit;
		//设置查询结果集
		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据Uid查询用户
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
	//删除用户
	public void delete(User user) {
		userDao.delete(user);
	}
	//修改用户
	public void update(User user) {
		userDao.update(user);
	}
	
}
