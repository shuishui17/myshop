package com.shui.shop.user;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	//注入UserService
	private UserService userService;
	
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
}
