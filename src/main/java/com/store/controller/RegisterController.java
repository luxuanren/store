package com.store.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.constant.Status;
import com.store.dao.UserDao;
import com.store.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/username.do")
	public void checkUsername(OutputStream os, String username) {
		try {
			if (userDao.findUserName(username)) {
				os.write(Status.FAILED.getBytes());
			}else {
				os.write(Status.SUCCESS.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/email.do")
	public void checkkEmail(OutputStream os, String email) {
		try {
			if (userDao.findEmail(email)) {
				os.write(Status.FAILED.getBytes());
			}else {
				os.write(Status.SUCCESS.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/register.do")
	public ModelAndView register(User user) {
		try {
			userDao.addUser(user);
			ModelAndView mav = new ModelAndView("registerSuccess");
			mav.addObject("message", "恭喜您注册成功！");
			return mav;
		} catch (Exception e) {
			return new ModelAndView("errorPage");
		}
	}
}
