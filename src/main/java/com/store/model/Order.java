package com.store.model;

import com.store.constant.OrderStatus;

public class Order {
	private int id;
	private String date;
	private int userId;
	private OrderStatus status;
	private TradeList tradeList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public TradeList getTradeList() {
		return tradeList;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setTradeList(TradeList tradeList) {
		this.tradeList = tradeList;
	}

	public void markCommence(int goodsId) {
		for (TradeItem item : tradeList.getTradeList()) {
			if (item.getGoodsId() == goodsId) {
				item.setEvaluate(true);
				break;
			}
		}
	}
	
	public void markAppendCommence(int goodsId) {
		for (TradeItem item : tradeList.getTradeList()) {
			if (item.getGoodsId() == goodsId) {
				item.setAppendEvaluation(true);
				break;
			}
		}
	}
}
