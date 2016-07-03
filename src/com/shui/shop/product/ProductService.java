package com.shui.shop.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shui.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean findByPage(Integer cid, Integer page) {
		int limit = 12; // 每页显示记录数.
		int totalPage = 0; // 总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setLimit(12);
		// 查询二级分类某一分类商品的所有数量
		Integer totalCount = productDao.findCount(cid);
		pageBean.setTotalCount(totalCount);
		// 计算总页数:
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品数据集合:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		int limit = 12;//总记录数
		int totalPage = 0;//总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setLimit(limit);
		// 总记录数
		Integer totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 计算总页数:
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 商品数据集合:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 查询商品(分页)
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPage(Integer page) {
		//封装pageBean
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int count = productDao.findCount();
		pageBean.setTotalCount(count);
		int totalPage = (count + limit - 1)/limit;
		pageBean.setTotalPage(totalPage);
		//将每一页的内容放到集合中
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public void adminDelete(Product product) {
		productDao.adminDelete(product);
	}

	public void modify(Product product) {
		productDao.modify(product);
	}

}
