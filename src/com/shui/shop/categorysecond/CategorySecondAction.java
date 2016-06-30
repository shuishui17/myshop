package com.shui.shop.categorysecond;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shui.shop.utils.PageBean;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	
	private Integer page = 0;
	private CategorySecondService categorySecondService;
	private CategorySecond categorySecond = new CategorySecond();
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}



	public void setPage(Integer page) {
		this.page = page;
	}


	//查询所有二级分类
	public String adminFindAll(){
		
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "adminFindAllSuccess";
	}
}
