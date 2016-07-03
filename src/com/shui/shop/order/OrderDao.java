package com.shui.shop.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shui.shop.user.User;
import com.shui.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	// 按用户查询订单
		public List<Order> findByUid(User existUser) {
			List<Order> list = this.getHibernateTemplate().find("from Order o where o.user.uid=? order by ordertime desc",existUser.getUid());
			return list;
		}

		// 查询订单的数量
		public Integer findCount() {
			List<Long> list = this.getHibernateTemplate().find("select count(*) from Order");
			return list.get(0).intValue();
		}

		// 查询每页显示的订单数据
		public List<Order> findByPage(Integer begin, Integer limit) {
			String hql = "from Order order by ordertime desc";
			List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(hql, null, begin, limit));
			return list;
		}

		public Integer findCount(Integer state) {
			List<Long> list = this.getHibernateTemplate().find("select count(*) from Order where state = ?",state);
			return list.get(0).intValue();
		}

		public List<Order> findByPage(Integer state, Integer begin, Integer limit) {
			String hql = "from Order where state = ? order by ordertime desc";
			List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Order>(hql, new Object[]{state}, begin, limit));
			return list;
		}

}
