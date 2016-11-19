package com.store.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.store.model.Goods;
import com.store.model.GoodsType;

@Repository
public class GoodsDao {
	@Autowired
	private JdbcTemplate template;
	
	public boolean addGoods(Goods goods) {
		return false;
	}
	public List<Goods> getGoodsByType(GoodsType type) {
		return null;
	}
	public List<Goods> getGoodsByKeyword(String keyword) {
		return null;
	}
}
