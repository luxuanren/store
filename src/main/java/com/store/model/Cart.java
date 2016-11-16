package com.store.model;
import java.util.HashMap;

public class Cart {
	private HashMap<Integer, Integer> goodsMap = new HashMap<>();
	private HashMap<Integer, Goods> goodsItemMap = new HashMap<>();
	
	public HashMap<Integer, Integer> getGoodsMap() {
		return goodsMap;
	}

	public HashMap<Integer, Goods> getGoodsItemMap() {
		return goodsItemMap;
	}

	public void addGoods(Goods target){
		addGoods(target, 1);
	}
	
	public void addGoods(Goods target, int num){
		Integer goodsId = target.getId();
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, goodsMap.get(goodsId) + num);
		}else {
			goodsMap.put(goodsId, num);
			goodsItemMap.put(goodsId, target);
		}
	}
	
	public void cutGoods(Goods target) {
		Integer goodsId = target.getId();
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, goodsMap.get(goodsId) - 1);
		}
	}
	public void updateGoods(Goods target, int num) {
		Integer goodsId = target.getId();
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, num);
		}
	}
	public void deleteGoods(Goods target) {
		Integer goodsId = target.getId();
		goodsMap.remove(goodsId);
		goodsItemMap.remove(goodsId);
	}
	public int getGoodsSum() {
		return goodsMap.size();
	}
}
