package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.dao.EvaluationDao;
import com.store.dao.GoodsDao;
import com.store.model.Evaluation;
import com.store.model.Goods;
import com.store.model.User;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private EvaluationDao evaluationDao;
	@Autowired
	private GoodsDao goodsDao;

	@RequestMapping("/keyword.do")
	public ModelAndView searchByKeyword(@RequestParam("keyword")String keyword) {
		ModelAndView mav = new ModelAndView("home");
		List<Goods> list = goodsDao.getGoodsByKeyword(keyword);
		mav.addObject("markName", "关注");
		mav.addObject("markOp", "add-mark");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/goods.do")
	public ModelAndView openGoodsPage(HttpServletRequest request, int goodsId) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ModelAndView mav = new ModelAndView("goods");
		List<Evaluation> evaluationList = evaluationDao.getEvaluationByGoodId(goodsId);
		Goods goods = goodsDao.getGoodsById(goodsId);
		if (user != null) {
			mav.addObject("username", user.getUsername());
		}
		mav.addObject("goods", goods);
		mav.addObject("list", evaluationList);
		return mav;
	}
}
