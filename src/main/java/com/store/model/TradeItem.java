package com.store.model;

public class TradeItem {
	private int goodsId;
	private int tradeNum;
	private boolean isEvaluate;
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
		return isEvaluate;
	}
	public void setEvaluate(boolean isEvaluate) {
		this.isEvaluate = isEvaluate;
	}
}
