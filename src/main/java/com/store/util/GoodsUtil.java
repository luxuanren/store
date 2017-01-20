package com.store.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.store.model.Goods;

public class GoodsUtil {
	public static Map<Integer, Goods> getGoodsMap(List<Goods> goodsList) {
		Map<Integer, Goods> goodsMap = new HashMap<>();
		for (Goods goods : goodsList) {
			goodsMap.put(goods.getId(), goods);
		}
		return goodsMap;
	}
}
