package com.shui.shop.adminUser;

public class AdminUserService {
	
	//注入Dao
	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
