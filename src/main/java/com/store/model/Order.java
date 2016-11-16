package com.store.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private int id;
	private Date date;
	private int userId;
	private ArrayList<TradeItem> goodsList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ArrayList<TradeItem> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<TradeItem> goodsList) {
		this.goodsList = goodsList;
	}
	public void setGoodsList(String goodsList) {
		this.goodsList = new ArrayList<TradeItem>();
		TradeItem item = null;
		for (String info : goodsList.split("&")) {
			item = new TradeItem();
			String[] values = info.split(":");
			item.setGoodsId(Integer.valueOf(values[0]));
			item.setTradeNum(Integer.valueOf(values[1]));
			item.setEvaluate(Integer.valueOf(values[2]) == 1);
			this.goodsList.add(item);
		}
	}
//	public static void main(String[] args) {
//		String string = "100:2:1&12:1:0";
//		Order order = new Order();
//		order.setGoodsList(string);
//		for (TradeItem item : order.getGoodsList()) {
//			System.out.println(item.getGoodsId());
//			System.out.println(item.getTradeNum());
//			System.out.println(item.isEvaluate());
//		}
//	}
}
