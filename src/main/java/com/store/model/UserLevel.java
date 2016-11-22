package com.store.model;

public enum UserLevel {
	COMMEN(1), BRONZE(2), SILVER(3), GOLDEN(4), DIAMOND(5);

	private int level;

	private UserLevel(int level) {
		this.level = level;
	}

	public int toInt() {
		return this.level;
	}

	public static UserLevel getLevel(int level) {
		switch (level) {
		case 1:
			return UserLevel.COMMEN;
		case 2:
			return UserLevel.BRONZE;
		case 3:
			return UserLevel.SILVER;
		case 4:
			return UserLevel.GOLDEN;
		case 5:
			return UserLevel.DIAMOND;
		default:
			return UserLevel.COMMEN;
		}
	}
}
