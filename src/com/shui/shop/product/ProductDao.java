package com.shui.shop.product;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shui.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHot() {
		List<Product> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Product>(
						"from Product where is_hot=?", new Object[] { 1 }, 0,
						10));

		return list;
	}

	public List<Product> findNew() {
		List<Product> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Product>(
						"from Product order by pdate desc", null, 0, 10));

		return list;
	}

	public Integer findCount(Integer cid) {
		String hql = "select count(*) from Product p , CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		//		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
				List<Long> list = this.getHibernateTemplate().find(hql,cid);
				System.out.println("list:============="+list.get(0).intValue() + "cid:" + cid);
				return list.get(0).intValue();
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		return list;
	}
	public List<Product> findByPage(Integer cid, int begin, int limit) {
		 String hql = "select p from Product p ,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		//String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		return list;
	}

	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		return list.get(0).intValue();
	}
}
