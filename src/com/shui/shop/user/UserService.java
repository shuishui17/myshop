package com.shui.shop.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shui.shop.utils.PageBean;
import com.shui.shop.utils.UUIDUtils;

/**
 * user的service层代码
 * @author shui
 *
 */
@Transactional
public class UserService {
	//注入userDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user) {
		// 保存用户:
		user.setState(0);// 0 未激活  1已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userDao.save(user);
	}

	public User login(User user) {
		return userDao.login(user);
	}

	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean();
		//封装pageBean
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		//封装总记录数
		int count = userDao.findCount();
		pageBean.setTotalCount(count);
		//封装总页数
		int totalPage = (count + limit - 1)/limit;
		pageBean.setTotalPage(totalPage);
		//封装每一页的记录
		int begin = (page - 1) * limit;
		List<User> uList = userDao.findByPage(begin,limit);
		pageBean.setList(uList);
		return pageBean;
	}

	public void adminSave(User user) {
		userDao.adminSave(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	public void modify(User user) {
		userDao.modify(user);
	}
	
}
