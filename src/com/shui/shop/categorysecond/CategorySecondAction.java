package com.shui.shop.categorysecond;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shui.shop.category.Category;
import com.shui.shop.category.CategoryService;
import com.shui.shop.utils.PageBean;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	
	private Integer page = 0;
	private CategorySecondService categorySecondService;
	private CategorySecond categorySecond = new CategorySecond();
	private CategoryService categoryService;
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

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


	//查询所有二级分类(分页)
	public String adminFindAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台：二级分类跳转到添加页面
	 * @return
	 */
	public String addPage(){
		//查询一级分类
		List<Category> cList = categoryService.findAll();
		//要将内容显示到页面，进行手动圧栈！
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	/**
	 * 后台：二级分类添加
	 * @return
	 */
	public String save(){
		Category category = new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	/**
	 * 后台：删除操作
	 */
	public String delete(){
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	/**
	 * 后台：跳转到修改页面
	 */
	public String edit(){
		//查询商品
		categorySecond = categorySecondService.findById(categorySecond.getCsid());
		//查询一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "editSuccess";
	}
	
	/**
	 * 后台：修改页面
	 */
	public String modify(){
		categorySecondService.modify(categorySecond);
		return "modifySuccess";
	}
}
