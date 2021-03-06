package com.store.constant;

public enum GoodsType {
	NON_TYPE(0), BOOK(1), FOOD(2), CLOTHS(3), ELECTRONIC(4), FURNITURE(5);

	private static String[] names = { "未分类", "书籍类", "食物类", "衣服类", "电子类", "家具类" };
	private int type;

	private GoodsType(int type) {
		this.type = type;
	}

	public static GoodsType getType(int type) {
		switch (type) {
		case 1:
			return GoodsType.BOOK;
		case 2:
			return GoodsType.FOOD;
		case 3:
			return GoodsType.CLOTHS;
		case 4:
			return GoodsType.ELECTRONIC;
		case 5:
			return GoodsType.FURNITURE;
		default:
			return GoodsType.NON_TYPE;
		}
	}

	@Override
	public String toString() {
		return GoodsType.names[this.type];
	}

	public int toInt() {
		return this.type;
	}
}
