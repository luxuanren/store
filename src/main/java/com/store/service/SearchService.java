package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.store.dao.GoodsDao;
import com.store.model.Goods;

@Service
public class SearchService {
	@Autowired
	private GoodsDao goodsDao;
	
	public String getGoodsByType(int type) {
		List<Goods> list = goodsDao.getGoodsByType(type);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	public List<Goods> getGoodsByKeyword(String keyword) {
		return goodsDao.getGoodsByKeyword(keyword);
	}
}
