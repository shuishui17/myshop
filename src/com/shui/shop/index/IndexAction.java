package com.shui.shop.index;
/**
 * 编写跳转到主页的方法
 */
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shui.shop.category.Category;
import com.shui.shop.category.CategoryService;
import com.shui.shop.product.Product;
import com.shui.shop.product.ProductService;

public class IndexAction extends ActionSupport{
	
	//注入一级分类的Service
	private CategoryService categoryService;
	
	//注入商品
	private ProductService productService;
	
	private List<Product> hotProductList;
	private List<Product> newProductList;
	
	public List<Product> getNewProductList() {
		return newProductList;
	}

	public List<Product> getHotProductList() {
		return hotProductList;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		
		//查找一级列表 页面显示一级列表
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		
		//查找热门商品 页面显示热门商品
		hotProductList = productService.findHot(); 
		newProductList = productService.findNew();
		return "indexSuccess";
	}
}
