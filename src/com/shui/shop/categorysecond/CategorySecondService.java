package com.shui.shop.categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shui.shop.utils.PageBean;

@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		//封装Page
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		
		pageBean.setPage(page);//设置当前页数
		Integer limit = 10;
		pageBean.setLimit(limit);//设置每一页的记录数
		//设置总的记录数，需要从数据库中进行查询
		Integer count = categorySecondDao.findCount();
		pageBean.setTotalCount(count);
		//设置总页数
		Integer totalPage = 0;
		if(count % limit == 0){
			totalPage = count/limit;
		}else{
			totalPage = count/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每一页显示的数据
		Integer begin = (page - 1 )* limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 查询二级分类，显示在商品添加界面
	 * @return
	 */
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public CategorySecond findById(Integer csid) {
		return categorySecondDao.findById(csid);
	}

	public void modify(CategorySecond categorySecond) {
		categorySecondDao.modify(categorySecond);
	}

}
