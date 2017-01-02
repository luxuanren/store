 package com.store.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.constant.Status;
import com.store.dao.CartDao;
import com.store.model.Cart;
import com.store.model.Goods;
import com.store.model.User;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartDao cartDao;
	
	@RequestMapping("/add.do")
	public void addGoods(HttpServletRequest request, OutputStream os, @ModelAttribute("goods")Goods goods) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			cart.addGoods(goods);
			cartDao.updateCart(user.getId(), cart.toString());
			os.write(Status.SUCCESS.getBytes("UTF-8"));
		}else {
			os.write(Status.UN_LOGIN.getBytes("UTF-8"));
		}
		
	}

	@RequestMapping("/delete.do")
	public void deleteGoods(HttpServletRequest request, OutputStream os, int goodsId) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		cart.deleteGoods(goodsId);
		cartDao.updateCart(user.getId(), cart.toString());
		os.write(Status.SUCCESS.getBytes("UTF-8"));
	}
	
	@RequestMapping("/update.do")
	public void updateCart(HttpServletRequest request, OutputStream os, int goodsId, int num) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateGoods(goodsId, num);
		cartDao.updateCart(user.getId(), cart.toString());
		os.write(Status.SUCCESS.getBytes("UTF-8"));
	}
}
