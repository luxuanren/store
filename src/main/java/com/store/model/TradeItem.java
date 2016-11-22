package com.store.model;

public class TradeItem {
	private int goodsId;
	private int tradeNum;
	private boolean isEvaluate;

	public TradeItem() {
		// default contructor
	}

	public TradeItem(String data) {
		String[] src = data.split(":");
		goodsId = Integer.valueOf(src[0]);
		tradeNum = Integer.valueOf(src[1]);
		isEvaluate = "1".equals(src[2]);
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
		return isEvaluate;
	}

	public void setEvaluate(boolean isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(goodsId).append(":").append(tradeNum).append(":");
		if (isEvaluate) {
			builder.append(1);
		} else {
			builder.append(0);
		}
		return builder.toString();
	}

}
