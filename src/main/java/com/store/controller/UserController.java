package com.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.dao.CartDao;
import com.store.dao.GoodsDao;
import com.store.model.Cart;
import com.store.model.User;
import com.store.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private GoodsDao goodsDao;
	
	@RequestMapping(value = "/loginCheck.do")
	public ModelAndView loginCheck(HttpServletRequest request, String email, String password, @ModelAttribute("target")String target) {
		ModelAndView mav = new ModelAndView();
		User user = userService.login(email, password);
		Cart cart = null;

		if (user != null) {
			// initialize user and cart information 
			request.getSession().setAttribute("user", user);
			mav.addObject("user", user);
			cart = new Cart(cartDao.getCartList(user.getId()), goodsDao);
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
			return "forward:/login.do?target=/user/information";
		}
		return "userInfo";
	}

	@RequestMapping("/order.do")
	public String userOrder(HttpServletRequest request) {
		if ( request.getSession().getAttribute("user") == null ) {
			return "redirect:/login.do?target=/user/order.do";
		}else {
			return "order";
		}
	}

	@RequestMapping("/cart.do")
	public ModelAndView userCart(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("user");
		if ( user == null ) {
			mav.setViewName("forward:/login.do?target=/user/cart.do");
			return mav;
		}else {
			Cart cart = new Cart(cartDao.getCartList(user.getId()), goodsDao);
			mav.addObject("username", user.getUsername());
			mav.addObject("list", cart.getCartList());
			mav.setViewName("cart");
			return mav;
		}
	}
	
	@RequestMapping("/mark.do")
	public String userMark(HttpServletRequest request) {
		if ( request.getSession().getAttribute("user") == null ) {
			return "forward:/login.do?target=/user/mark.do";
		}else {
			return "mark";
		}
	}
}
