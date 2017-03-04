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
	
	//ע��־ò�userDao
	@Resource
	private UserDao userDao;
	//����������ѯUser
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	//����ע����û�
	public void save(User user) {
		user.setState(0); // 0����δ��� 1�����Ѿ�����
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailUtils.sendMail(user.getEmail(), code);
	}
	//���ݼ������ѯ�Ƿ���ڸ��û�
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//����ɹ�������״̬��Ϊ1������������
	public void updateState(User exist) {
		userDao.updateState(exist);
	}
	//�û���¼��ҵ��
	public User login(String username, String password) {
		return userDao.login(username, password);
	}
	//��ҳ��ѯ�����û�
	public PageBean<User> findAllByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		//���õ�ǰҳ
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 5;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount%limit == 0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//���ò�ѯ���
		int begin=(page-1)*limit;
		//���ò�ѯ�����
		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//����Uid��ѯ�û�
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
	//ɾ���û�
	public void delete(User user) {
		userDao.delete(user);
	}
	//�޸��û�
	public void update(User user) {
		userDao.update(user);
	}
	
}
