package com.shui.shop.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shui.shop.category.Category;
import com.shui.shop.category.CategoryService;
import com.shui.shop.categorysecond.CategorySecond;
import com.shui.shop.categorysecond.CategorySecondService;
import com.shui.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Integer cid;
	private Integer csid;
	private Integer page;
	private CategoryService categoryService;
	private ProductService productService;
	private PageBean<Product> pageBean;
	// 文件上传需要的三个属性:
	private File upload; // 上传文件
	private String uploadContentType; // 上传文件的MIME类型
	private String uploadFileName; // 上传文件名称
	// 模型驱动类
	private Product product = new Product();
	// 注入二级分类
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

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

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String findByCid() {
		// 查询分类
		// 1. 查询所有一级分类
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);
		pageBean = productService.findByPage(cid, page);
		System.out.println(pageBean.getList());

		return "findByCidSuccess";
	}

	public String findByPid() {
		// 1. 查询所有一级分类
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);
		// 查询商品
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}

	public String findByCsid() {
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();
		// 获得值栈:
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);

		pageBean = productService.findByCsid(csid, page);
		return "findByCsidSuccess";
	}

	/**
	 * 后台查询所有商品的方法
	 */
	public String adminFindAll() {
		pageBean = productService.findByPage(page);
		return "adminFindAllSuccess";
	}

	/**
	 * 后台：跳转到添加页面
	 */
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	/**
	 * 后台：商品添加
	 * 
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 文件上传的操作:
		// 获得上传的路径:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/products");
		String realPath = path + "/" + csid + "/" + uploadFileName;
		File diskFile = new File(realPath);
		// 文件上传:
		FileUtils.copyFile(upload, diskFile);
		// 保存到数据库:
		// 设置二级分类
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		// 设置时间:
		product.setPdate(new Date());
		// 设置图片上传路径:
		product.setImage("products/" + csid + "/" + uploadFileName);

		// 调用Serviec保存商品:
		productService.save(product);
		return "saveSuccess";
	}

	/**
	 * 后台：商品删除操作
	 */
	public String adminDelete() {
		productService.adminDelete(product);
		return "adminDeleteSuccess";
	}

	/**
	 * 后台：商品编辑显示页面
	 */
	public String edit() {
		// 查询商品
		product = productService.findByPid(product.getPid());
		// 查询二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	/**
	 * 后台：商品修改
	 * @throws IOException 
	 */
	public String modify() throws IOException {
		// 文件上传的操作:
		// 获得上传的路径:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/products");
		String realPath = path + "/" + csid + "/" + uploadFileName;
		File diskFile = new File(realPath);
		// 文件上传:
		FileUtils.copyFile(upload, diskFile);
		// 保存到数据库:
		// 设置二级分类
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		//设置主键
		product.setPid(product.getPid());
		// 设置时间:
		product.setPdate(new Date());
		// 设置图片上传路径:
		product.setImage("products/" + csid + "/" + uploadFileName);

		// 调用Serviec修改商品:
		productService.modify(product);
		return "modifySuccess";
	}
}
