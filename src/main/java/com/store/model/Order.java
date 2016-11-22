package com.store.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {
	private int id;
	private String date;
	private int userId;
	private ArrayList<TradeItem> tradeList;

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

	public ArrayList<TradeItem> getTradeList() {
		return tradeList;
	}

	public void setTradeList(String data) {
		String regex = "\\d+:\\d+:\\d";
		Matcher matcher = Pattern.compile(regex).matcher(data);
		tradeList = new ArrayList<>();
		while (matcher.find()) {
			tradeList.add(new TradeItem(matcher.group()));
		}
	}
}
