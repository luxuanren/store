package com.store.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CartTest {

	@Test
	public void testCartString() {
		int[] goodsIds = new int[]{10001,10002,10003};
		int[] nums = new int[]{1,2,3};
		String data = "10001:1,10002:2,10003:3,";
		Cart cart = new Cart(data);
		
		Map<Integer, Integer> goodsMap = cart.getGoodsMap();
		for (int i = 0; i < nums.length; i++) {
			assertSame(nums[i], goodsMap.get(goodsIds[i]));
		}
	}

	@Test
	public void	testGetGoodsIdList(){
		String data = "10001:1,10002:2,10003:3,";
		Cart cart = new Cart(data);
		List<Integer> list = cart.getGoodsIdList();
		Map<Integer, Integer> goodsMap = cart.getGoodsMap();
		for (Integer integer : list) {
			assertEquals(true, goodsMap.containsKey(integer));
		}
	}

	@Test
	public void testAddGoodsGoods() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods);
		assertSame(1, cart.getGoodsMap().get(goodsId));
		assertEquals(goods, cart.getGoodsItemMap().get(goodsId));
	}

	@Test
	public void testAddGoodsGoodsInt() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods, 2);
		assertSame(2, cart.getGoodsMap().get(goodsId));
		assertEquals(goods, cart.getGoodsItemMap().get(goodsId));
	}
	
	@Test
	public void testCutGoods() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods, 2);
		assertSame(2, cart.getGoodsMap().get(goodsId));
		cart.cutGoods(goodsId);
		assertSame(1, cart.getGoodsMap().get(goodsId));
	}

	@Test
	public void testUpdateGoods() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		int num = 3;
		goods.setId(goodsId);
		cart.addGoods(goods);
		cart.updateGoods(goodsId, num);
		assertSame(3, cart.getGoodsMap().get(goodsId));
	}

	@Test
	public void testDeleteGoods() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods);
		assertEquals(goods, cart.getGoodsItemMap().get(goodsId));
		cart.deleteGoods(goodsId);
		assertSame(null, cart.getGoodsItemMap().get(goodsId));
	}

	@Test
	public void testGetGoodsSum() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods);
		assertEquals(1, cart.getGoodsSum());
	}

	@Test
	public void testToString() {
		Cart cart = new Cart();
		Goods goods = new Goods();
		Integer goodsId = 10000;
		goods.setId(goodsId);
		cart.addGoods(goods);
		
		goods = new Goods();
		goods.setId(++goodsId);
		cart.addGoods(goods, 2);
		
		String expected = "10000:1,10001:2,";
		assertEquals(expected, cart.toString());
	}

}
