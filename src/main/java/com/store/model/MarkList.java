package com.store.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MarkList {
	private List<Integer> list;
	private Gson gson = new Gson();

	public MarkList(String json) {
		if (json != null && json.length() > 2) {
			Type listType = new TypeToken<List<Integer>>() {}.getType();
			list = gson.fromJson(json, listType);
		} else {
			list = new ArrayList<>();
		}
	}

	public List<Integer> getList() {
		return list;
	}

	public void addItem(Integer goodsId) {
		if (!list.contains(goodsId)) {
			list.add(goodsId);
		}
	}

	public void deleteItem(Integer goodsId) {
		list.remove(goodsId);
	}

	@Override
	public String toString() {
		return gson.toJson(list);
	}

}
