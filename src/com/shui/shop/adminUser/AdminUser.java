package com.shui.shop.adminUser;
/**
 * 后台登陆人员类
 * @author shui
 *
 */
public class AdminUser {
	private int uId;
	private String userName;
	private String password;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
