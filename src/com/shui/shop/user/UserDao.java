package com.shui.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shui.shop.product.Product;
import com.shui.shop.utils.PageHibernateCallback;

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

	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0){
			return list.get(0).intValue();			
		}
		return 0;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	public void adminSave(User user) {
		this.getHibernateTemplate().save(user);
	}

	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class,uid);
	}

	public void modify(User user) {
		this.getHibernateTemplate().update(user);
	}

}
