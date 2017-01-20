package com.store.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.constant.Status;
import com.store.dao.MarkDao;
import com.store.model.MarkList;
import com.store.model.User;

@Controller
@RequestMapping("/mark")
public class MarkController {
	@Autowired
	private MarkDao markDao;

	@RequestMapping("/add.do")
	public void addMark(HttpServletRequest request, OutputStream os, int goodsId) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			MarkList markList = (MarkList) session.getAttribute("markList");
			markList.addItem(goodsId);
			markDao.updateUserMarks(user.getId(), markList.toString());
			os.write(Status.SUCCESS.getBytes("UTF-8"));
		}else {
			os.write(Status.UN_LOGIN.getBytes("UTF-8"));
		}
	}
	
	@RequestMapping("/delete.do")
	public void deleteMark(HttpServletRequest request, OutputStream os, int goodsId) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			MarkList markList = (MarkList) session.getAttribute("markList");
			markList.deleteItem(goodsId);
			markDao.updateUserMarks(user.getId(), markList.toString());
			os.write(Status.SUCCESS.getBytes("UTF-8"));
		}else {
			os.write(Status.UN_LOGIN.getBytes("UTF-8"));
		}
	}
}
