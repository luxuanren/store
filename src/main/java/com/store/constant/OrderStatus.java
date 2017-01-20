package com.store.constant;

public enum OrderStatus {
	UNFINISHED(0), FINISHEND(1);
	private static String[] name = { "未完成", "已完成" };
	private int status;

	private OrderStatus(int status) {
		this.status = status;
	}

	public static OrderStatus getOrderStatus(int status) {
		if (status == 0) {
			return OrderStatus.UNFINISHED;
		} else if (status == 1) {
			return OrderStatus.FINISHEND;
		}
		return OrderStatus.UNFINISHED;
	}

	public String toString() {
		return OrderStatus.name[this.status];
	}
}
