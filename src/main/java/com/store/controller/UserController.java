package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.dao.CartDao;
import com.store.dao.GoodsDao;
import com.store.dao.MarkDao;
import com.store.dao.OrderDao;
import com.store.dao.UserDao;
import com.store.model.Cart;
import com.store.model.Goods;
import com.store.model.MarkList;
import com.store.model.Order;
import com.store.model.User;
import com.store.util.GoodsUtil;
import com.store.util.OrderUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private MarkDao markDao;
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping(value = "/loginCheck.do")
	public ModelAndView loginCheck(HttpServletRequest request, String email, String password, String target) {
		ModelAndView mav = new ModelAndView();
		User user = userDao.FindUser(email, password);
		MarkList markList;
		Cart cart;

		if (user != null) {
			// initialize user and cart information 
			request.getSession().setAttribute("user", user);
			mav.addObject("user", user);
			cart = new Cart(cartDao.getCartList(user.getId()), goodsDao);
			markList = new MarkList(markDao.getMarksByUserId(user.getId()));
			request.getSession().setAttribute("markList", markList);
			request.getSession().setAttribute("cart", cart);
			if (target != null && target.length() > 2) {
				mav.setViewName("redirect:" + (target.length() > 0 ? target : "/home.do"));
			}else {
				mav.setViewName("home");
			}
		} else {
			mav.addObject("message", "用戶名或密码错误！");
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping("/information.do")
	public String userInfo(HttpServletRequest request){
		if ( request.getSession().getAttribute("user") == null ) {
			return "redirect:/login.do?target=/user/information.do";
		}
		return "userInfo";
	}

	@RequestMapping("/order.do")
	public ModelAndView userOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		if ( user == null ) {
			mav.setViewName("redirect:/login.do?target=/user/order.do");
		}else {
			List<Order> orderList = orderDao.getOrderList(user.getId());
			List<Goods> goodsList = goodsDao.getGoodsByIdList(OrderUtil.getGoodsIdList(orderList));
			OrderUtil.wrapOrderListWithGoods(orderList, GoodsUtil.getGoodsMap(goodsList));
			mav.addObject("orderList", orderList);
			mav.addObject("username", user.getUsername());
			mav.setViewName("order");
		}
		return mav;
	}

	@RequestMapping("/cart.do")
	public ModelAndView userCart(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		if ( user == null ) {
			mav.setViewName("redirect:/login.do?target=/user/cart.do");
		}else {
			Cart cart = new Cart(cartDao.getCartList(user.getId()), goodsDao);
			mav.addObject("username", user.getUsername());
			mav.addObject("list", cart.getCartList());
			mav.setViewName("cart");
		}
		return mav;
	}
	
	@RequestMapping("/mark.do")
	public ModelAndView userMark(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		if ( user == null ) {
			mav.setViewName("redirect:/login.do");
			mav.addObject("value", "a");
		}else {
			MarkList markList = new MarkList(markDao.getMarksByUserId(user.getId()));
			List<Goods> list = goodsDao.getGoodsByIdList(markList.getList());
			mav.addObject("markName", "移除关注");
			mav.addObject("markOp", "delete-mark");
			mav.addObject("list", list);
			mav.setViewName("home");
		}
		return mav;
	}
}
