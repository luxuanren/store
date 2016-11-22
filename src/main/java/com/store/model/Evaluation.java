package com.store.model;

public class Evaluation {
	private boolean isValid;
	private int goodsId;
	private String username;
	private String evalTime;
	private String info;
	private String appendInfo;

	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEvalTime() {
		return evalTime;
	}

	public void setEvalTime(String evalTime) {
		this.evalTime = evalTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAppendInfo() {
		return appendInfo;
	}

	public void setAppendInfo(String appendInfo) {
		this.appendInfo = appendInfo;
	}
	
}
