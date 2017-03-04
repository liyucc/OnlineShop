package com.cong.shop.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cong.shop.user.vo.User;
import com.cong.shop.util.BaseDao;
import com.cong.shop.util.PageHibernateCallback;

@Repository("userDao")
public class UserDao extends BaseDao{
	
	//根据姓名查询User
	public User findByUsername(String username) {
		String hql = "from User u where u.username = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, username);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//保存注册的用户
	public void save(User user) {
		hibernateTemplate.save(user);
	}
	//根据激活码查询用户
	public User findByCode(String code) {
		String hql = "from User u  where u.code = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, code);
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}
		return null;
	}
	//修改账号激活状态
	public void updateState(User exist) {
		hibernateTemplate.update(exist);
	}
	//根据用户名和密码查询用户
	public User login(String username, String password) {
		String hql="from User u where u.username=? and u.password=?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//查询总记录
	public int findCount() {
		String hql="select count(*) from User";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//查询结果集
	public List<User> findByPage(int begin, int limit) {
		String hql="from User order by uid";
		List<User> list = hibernateTemplate.execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		if(list!=null && list.size() > 0 ){
			return list;
		}
		return null;
	}
	public User findByUid(Integer uid) {
		return hibernateTemplate.get(User.class, uid);
	}
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}
	
	public void update(User user) {
		hibernateTemplate.update(user);
	}
	
}
