package com.store.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.store.model.Goods;
import com.store.model.Order;
import com.store.model.TradeItem;
import com.store.model.TradeItemWithGoods;

public class OrderUtil {
	public static List<Integer> getGoodsIdList(List<Order> orderList) {
		List<Integer> goodsIdList = new ArrayList<>();
		Integer goodsId;
		for (Order order : orderList) {
			for (TradeItem tradeItem : order.getTradeList().getTradeList()) {
				goodsId = tradeItem.getGoodsId();
				if (!goodsIdList.contains(goodsId)) {
					goodsIdList.add(goodsId);
				}
			}
		}
		return goodsIdList;
	}
	
	public static void wrapOrderListWithGoods(List<Order> orderList, Map<Integer, Goods> goodsMap) {
		for (Order order : orderList) {
			List<TradeItem> newList = new ArrayList<>();
			TradeItemWithGoods newItem;
			for (TradeItem tradeItem : order.getTradeList().getTradeList()) {
				newItem = new TradeItemWithGoods(goodsMap.get(tradeItem.getGoodsId()));
				newItem.setGoodsId(tradeItem.getGoodsId());
				newItem.setPrice(tradeItem.getPrice());
				newItem.setTradeNum(tradeItem.getTradeNum());
				newItem.setSum(tradeItem.getSum());
				newItem.setEvaluate(tradeItem.isEvaluate());
				newItem.setAppendEvaluation(tradeItem.isAppendEvaluation());
				newList.add(newItem);
			}
			order.getTradeList().setTradeList(newList);
		}
	}
}
