package com.shui.shop.categorysecond;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
}
