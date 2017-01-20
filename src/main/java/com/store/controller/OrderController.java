package com.store.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.constant.Status;
import com.store.model.Cart;
import com.store.model.TradeList;
import com.store.model.User;
import com.store.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/deal.do")
	public void addOrder(HttpServletRequest request, OutputStream os, String json) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			TradeList tradeList = new TradeList(json);
			if (orderService.checkInventory(tradeList)) {
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				if (orderService.deal(user.getId(), tradeList, cart)) {
					os.write(Status.SUCCESS.getBytes("UTF-8"));
				}
			}else {
				os.write(Status.INSUFFICIENT.getBytes("UTF-8"));
			}
		}else {
			os.write(Status.UN_LOGIN.getBytes("UTF-8"));
		}
	}
	
	@RequestMapping("/comment.do")
	public void addCommence(OutputStream os, int orderId, int goodsId, String username, String comment) throws IOException {
		if (orderService.comment(orderId, goodsId, username, comment)) {
			os.write(Status.SUCCESS.getBytes("UTF-8"));
		}else {
			os.write(Status.FAILED.getBytes("UTF-8"));
		}
	}
	
	@RequestMapping("/appendComment.do")
	public void appendCommence(OutputStream os, int orderId, int goodsId, String username, String comment) throws IOException {
		if (orderService.appendCommence(orderId, goodsId, username, comment)) {
			os.write(Status.SUCCESS.getBytes("UTF-8"));
		}else {
			os.write(Status.FAILED.getBytes("UTF-8"));
		}
	}
}
