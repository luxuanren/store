package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CartDao;
import com.store.dao.GoodsDao;
import com.store.dao.OrderDao;
import com.store.dao.UserDao;
import com.store.model.Cart;
import com.store.model.Order;
import com.store.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	/**
	 * username,email,password are required
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		return userDao.addUser(user);
	}
	
	public User login(String email, String password) {
		return userDao.FindUser(email, password);
	}
	
	public boolean alterPassword(int uId, String oldPassword, String newPassword) {
		return userDao.alterPassword(uId, oldPassword, newPassword);
	}
	
	public Cart getCartFromDb(int uId) {
		String data = cartDao.getCartList(uId);
		Cart cart = new Cart(data, goodsDao);
		return cart;
	}
	
	public List<Order> getUserOrder(int uId) {
		return orderDao.getOrderList(uId);
	}
}
