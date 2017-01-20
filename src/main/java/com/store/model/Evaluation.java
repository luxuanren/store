package com.store.model;

public class Evaluation {
	private boolean appendCommence;
	private int goodsId;
	private String username;
	private String evalTime;
	private String info;
	private String appendInfo;
	private String appendEvalTime;

	
	public String getAppendEvalTime() {
		return appendEvalTime;
	}

	public void setAppendEvalTime(String appendEvalTime) {
		this.appendEvalTime = appendEvalTime;
	}

	public boolean isAppendCommence() {
		return appendCommence;
	}

	public void setAppendCommence(boolean appendCommence) {
		this.appendCommence = appendCommence;
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
