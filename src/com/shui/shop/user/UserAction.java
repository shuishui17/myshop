package com.shui.shop.user;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shui.shop.utils.PageBean;
import com.shui.shop.utils.UUIDUtils;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	//注入UserService
	private UserService userService;
	private Integer page;
	PageBean<User> pageBean;
	private Integer uid;
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public PageBean<User> getPageBean() {
		return pageBean;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 跳转到注册页面的方法
	 */
	public String registPage(){
		return "registPageSuccess";
	}
	
	/**
	 * 跳转到登录页面的方法
	 */
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	
	/**
	 * 注册的方法
	 */
	public String regist(){
		userService.save(user);
		return "registSuccess";
	}
	
	/**
	 * 登陆方法
	 */
	public String login(){
		User existUser  = userService.login(user);
		if(existUser != null){
			//如果用户存在，登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}else{
			//如果用户不存在，登陆失败返回错误信息
			this.addActionError("用户名或密码错误或用户未激活!");
			return "loginInput";
		}
	}
	
	/**
	 * 用户退出方法
	 */
	public String quit(){
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "quitSuccess";
	}
	
	//后台显示用户列表
	public String adminFindAll(){
		pageBean = userService.findByPage(page);
		return "adminFindAllSuccess";
	}
	/**
	 * 后台：添加用户
	 */
	public String adminSave(){
		user.setState(0);
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userService.adminSave(user);
		return "saveSuccess";
	}
	
	/**
	 * 后台:删除用户
	 */
	public String delete(){
		userService.delete(user);
		return "deleteSuccess";
	}
	
	/**
	 * 后台：显示修改页面
	 */
	public String edit(){
		//System.out.println(user.getUid());
		user = userService.findByUid(user.getUid());
		//System.out.println(user.getName());
		return "editSuccess";
	}
	
	/**
	 * 后台：用户修改方法
	 */
	public String modify(){
		System.out.println(user.getName() + "---" + user.getUid() + user.getPassword() + user.getSex());
		user.setState(0);
		//user.setUid(uid);
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userService.modify(user);
		return "modifySuccess";
	}
}
