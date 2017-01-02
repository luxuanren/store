package com.store.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Goods;
import com.store.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/type.do", produces="text/json;charset=UTF-8")
	public void searchByType(@RequestParam("type")int type, OutputStream os) throws IOException {
		String result = searchService.getGoodsByType(type);
		os.write(result.getBytes("UTF-8"));
	}

	@RequestMapping("/keyword.do")
	public ModelAndView searchByKeyword(@RequestParam("keyword")String keyword) {
		ModelAndView mav = new ModelAndView("home");
		List<Goods> list = searchService.getGoodsByKeyword(keyword);
		mav.addObject("list", list);
		return mav;
	}
}
