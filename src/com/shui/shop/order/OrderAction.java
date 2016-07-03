package com.shui.shop.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shui.shop.cart.Cart;
import com.shui.shop.cart.CartItem;
import com.shui.shop.user.User;
import com.shui.shop.utils.PageBean;

public class OrderAction extends ActionSupport {
	private OrderService orderService;
	private Order order = new Order();
	//后台查询所需属性
	private Integer page;
	private Integer state;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String saveOreder() {
		order = new Order();
		// 封装订单数据
		order.setOrdertime(new Date());
		order.setState(1);// 未付款 2.已付款 3.已发货 4.已收货
		// 从购物车获取购物车
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("您还没有购物!请先去购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 订单所属的用户:
		User existUser = (User) request.getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("您还没有登录!请先去登录!");
			return "msg";
		}
		/******************** 封装订单项数据 *************/
		// 订单项数据从 购物项的数据获得.
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		
		orderService.save(order);
		return "saveSuccess";
	}
	/**
	 * 后台按状态查询
	 * @return
	 */
	public String adminFindByState(){
		PageBean<Order> pageBean = orderService.findByPage(state, page);
		// 将PageBean的数据保存到页面:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}
	/**
	 * 后台查询所有订单
	 */
	public String adminFindAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		// 将PageBean的数据保存到页面:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
}
