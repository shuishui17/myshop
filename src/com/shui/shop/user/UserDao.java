package com.shui.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {

	/**
	 * Dao层保存用户信息
	 * 
	 * @param user
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User login(User user) {
		List<User> list = this.getHibernateTemplate().find(
				"from User where username = ? and password = ? and state = ?",
				user.getUsername(), user.getPassword(), 1);
		if(list.size() != 0){
			return list.get(0);
		}
		return null;
	}

}
