package com.store.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class TradeItem {
	private int goodsId;
	private double price;
	private int tradeNum;
	private double sum;
	private boolean evaluate;
	private boolean appendEvaluation;
	
	public static void main(String[] args) {
		TradeItem item = new TradeItem();
		item.setGoodsId(10000);
		item.setPrice(20.0);
		item.setTradeNum(1);
		item.setSum(20.0);
		item.setEvaluate(false);
		item.setAppendEvaluation(false);
		List<TradeItem> list = new ArrayList<>();
		list.add(item);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
	}
	public TradeItem() {
		// default constructor
	}

	public static TradeItem getTradeItem(String json) {
		Gson gson = new Gson();
		TradeItem item = gson.fromJson(json, TradeItem.class);
		return item;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}

	public boolean isEvaluate() {
		return evaluate;
	}

	public void setEvaluate(boolean isEvaluate) {
		this.evaluate = isEvaluate;
	}
    
	public boolean isAppendEvaluation() {
		return appendEvaluation;
	}

	public void setAppendEvaluation(boolean appendEvaluation) {
		this.appendEvaluation = appendEvaluation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}
