package com.shui.shop.product;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shui.shop.category.Category;
import com.shui.shop.category.CategoryService;
import com.shui.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Integer cid;
	private Integer csid;
	private Integer page;
	private CategoryService categoryService;
	private ProductService productService;
	private PageBean<Product> pageBean;
	//模型驱动类
	private Product product = new Product();

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public PageBean<Product> getPageBean() {
		return pageBean;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	

	public Integer getCsid() {
		return csid;
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	public String findByCid(){
		//查询分类
		//1. 查询所有一级分类
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getValueStack()
		.set("categoryList", categoryList);
		pageBean = productService.findByPage(cid,page);
		System.out.println(pageBean.getList());
		
		return "findByCidSuccess";
	}

	public String findByPid(){
		//1. 查询所有一级分类
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getValueStack()
		.set("categoryList", categoryList);
		//查询商品
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	
	public String findByCsid(){
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();
		// 获得值栈:
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);
		
		pageBean = productService.findByCsid(csid, page);
		return "findByCsidSuccess";
	}
	
	
}
