package com.store.model;

import java.util.Date;

public class Evaluation {
	private String username;
	private Date evalTime;
	private String info;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getEvalTime() {
		return evalTime;
	}
	public void setEvalTime(Date evalTime) {
		this.evalTime = evalTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
