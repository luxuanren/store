package com.store.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TradeList {
	private List<TradeItem> tradeList = new ArrayList<>();
	private Gson gson = new Gson();

	public TradeList() {
		// default constructor
	}

	public TradeList(String json) {
		if (json != null && json.length() > 2) {
			Type listType = new TypeToken<List<TradeItem>>() {}.getType();
			tradeList = gson.fromJson(json, listType);
		}
	}

	public List<TradeItem> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<TradeItem> tradeList) {
		this.tradeList = tradeList;
	}

	public TradeItem get(int index) {
		return tradeList.get(index);
	}

	public void add(TradeItem item) {
		tradeList.add(item);
	}

	@Override
	public String toString() {
		return gson.toJson(tradeList);
	}
}
