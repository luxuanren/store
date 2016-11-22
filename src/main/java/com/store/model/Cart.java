package com.store.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cart {
	private HashMap<Integer, Integer> goodsMap = new HashMap<>();
	private HashMap<Integer, Goods> goodsItemMap = new HashMap<>();

	public Cart() {
		// default constructor
	}
	
	public Cart(String data) {
		String regex = "(\\d+):(\\d+)";
		Matcher matcher = Pattern.compile(regex).matcher(data);
		while (matcher.find()) {
			goodsMap.put(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
		}
	}

	public List<Integer> getGoodsIdList() {
		ArrayList<Integer> list = new ArrayList<>();
		Iterator<Entry<Integer, Integer>> iterator = goodsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator.next();
			list.add(entry.getKey());
		}
		return list;
	}

	public void setGoodsMap(HashMap<Integer, Integer> goodsMap) {
		this.goodsMap.putAll(goodsMap);
	}

	public HashMap<Integer, Integer> getGoodsMap() {
		return goodsMap;
	}

	public HashMap<Integer, Goods> getGoodsItemMap() {
		return goodsItemMap;
	}

	public void addGoods(Goods goods) {
		addGoods(goods, 1);
	}

	public void addGoods(Goods goods, int num) {
		Integer goodsId = goods.getId();
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, goodsMap.get(goodsId) + num);
		} else {
			goodsMap.put(goodsId, num);
			goodsItemMap.put(goodsId, goods);
		}
	}

	public void cutGoods(int goodsId) {
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, goodsMap.get(goodsId) - 1);
		}
	}

	public void updateGoods(int goodsId, int num) {
		if (goodsMap.get(goodsId) != null) {
			goodsMap.put(goodsId, num);
		}
	}

	public void deleteGoods(int goodsId) {
		goodsMap.remove(goodsId);
		goodsItemMap.remove(goodsId);
	}

	public int getGoodsSum() {
		return goodsMap.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterator<Entry<Integer, Integer>> iterator = goodsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator.next();
			Goods goods = goodsItemMap.get(entry.getKey());
			builder.append(goods.getId()).append(":").append(entry.getValue()).append(",");

		}
		return builder.toString();
	}

}
