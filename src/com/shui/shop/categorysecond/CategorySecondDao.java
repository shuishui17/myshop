package com.shui.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shui.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	public Integer findCount() {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	//分页查询
	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<CategorySecond>(hql, null , begin, limit));
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	//保存二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return this.getHibernateTemplate().find("from CategorySecond");
	}

	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	public CategorySecond findById(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class,csid);
	}

	public void modify(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
}
