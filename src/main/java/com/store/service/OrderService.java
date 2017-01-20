package com.store.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CartDao;
import com.store.dao.EvaluationDao;
import com.store.dao.GoodsDao;
import com.store.dao.OrderDao;
import com.store.model.Cart;
import com.store.model.Evaluation;
import com.store.model.Goods;
import com.store.model.Order;
import com.store.model.TradeItem;
import com.store.model.TradeList;
import com.store.util.DateUtil;

@Service
public class OrderService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private EvaluationDao evaluationDao;
	
	public boolean checkInventory(TradeList tradeList) {
		List<Goods> goodsList = goodsDao.getGoodsByIdList(tradeList);
		Map<Integer, Integer> inventoryMap = new HashMap<Integer, Integer>();
		for (Goods goods : goodsList) {
			inventoryMap.put(goods.getId(), goods.getInventory());
		}
		
		for (TradeItem item : tradeList.getTradeList()) {
			if (item.getTradeNum() > inventoryMap.get(item.getGoodsId())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean deal(Integer uId, TradeList tradeList, Cart cart) {
		for (TradeItem item : tradeList.getTradeList()) {
			goodsDao.updateGoodsInventory(item);
			cart.deleteGoods(item.getGoodsId());
		}

		try {
			cartDao.updateCart(uId, cart.toString());
			Order order = new Order();
			order.setUserId(uId);
			order.setDate(DateUtil.getCurrentTime());
			order.setTradeList(tradeList);
			orderDao.addOrder(order);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean comment(int orderId, int goodsId, String username, String commence) {
		Order order = orderDao.getOrder(orderId);
		TradeList tradeList = order.getTradeList();
		order.markCommence(goodsId);
		orderDao.updateOrder(orderId, tradeList.toString());
		Evaluation e = new Evaluation();
		e.setAppendCommence(false);
		e.setGoodsId(goodsId);
		e.setUsername(username);
		e.setEvalTime(DateUtil.getCurrentTime());
		e.setInfo(commence);
		evaluationDao.addEvaluation(e);
		return true;
	}
	
	public boolean appendCommence(int orderId, int goodsId, String username, String commence) {
		Order order = orderDao.getOrder(orderId);
		for (TradeItem item : order.getTradeList().getTradeList()) {
			if (item.getGoodsId() == goodsId) {
				item.setAppendEvaluation(true);
				break;
			}
		}
		orderDao.updateOrder(orderId, order.getTradeList().toString());
		evaluationDao.appendEvaluation(goodsId, username, commence, DateUtil.getCurrentTime());
	
		return true;
	}
}
