package com.shui.shop.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shui.shop.product.Product;
import com.shui.shop.product.ProductService;

/**
 * cart的action对象
 * 
 * @author shui
 * 
 */
public class CartAction extends ActionSupport {
	// 接收pid
	private Integer pid;
	// 接收count
	private Integer count;
	// 注入ProductService
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 从session范围获取购物车对象的方法
	 * 
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断:
		if (cart == null) {
			// 创建购物车对象
			cart = new Cart();
			// 将购物车对象放入到session范围:
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	// 添加购物车的方法
	public String addCart() {

		// 查询获取product对象
		Product product = productService.findByPid(pid);
		// 需要封装的购物项对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		// 获取购物车 需要依赖request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.addCart(cartItem);

		return "addCartSuccess";
	}

	// 添加清空购物车的方法
	public String clearCart() {
		// 从session中获取cart
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.clearCart();
		return "clearCartSuccess";
	}

	// 移除购物项
	public String removeCart() {
		// 获取Cart对象.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
}
