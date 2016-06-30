package com.shui.shop.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	

	private Category category = new Category();
	private CategoryService categoryService;
	@Override
	public Category getModel() {
		return category;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String adminFindAll(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);;
		return "adminFindAllSuccess";
	}
	
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	/**
	 * 后台：编辑一级分类(查询一级分类)
	 * @return
	 */
	public String edit(){
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	/**
	 * 后台：编辑一级分类
	 * @return
	 */
	public String modify(){
		categoryService.modify(category);
		return "modifySuccess";
	}
}
