package com.store.model;

public class TradeItemWithGoods extends TradeItem {

	private Goods goods;

	public TradeItemWithGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
