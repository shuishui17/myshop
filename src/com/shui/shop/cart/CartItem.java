package com.shui.shop.cart;

import com.shui.shop.product.Product;

public class CartItem {
	//商品
	private Product product;
	//数量
	private Integer count;
	//小计
	private Double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public Double getSubtotal() {
		return count * product.getShop_price();
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
