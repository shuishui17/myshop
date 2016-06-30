package com.shui.shop.user;

import org.springframework.transaction.annotation.Transactional;

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
	
}
