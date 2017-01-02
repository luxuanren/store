package com.store.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.store.model.Goods;
import com.store.model.GoodsType;
@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class GoodsDaoTest extends UnitilsJUnit4{
	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("goodsDao")
	private GoodsDao goodsDao;

	// please delete this clause in goods table before test
	@Test
	public void testAddGoods() {
		Goods goods = new Goods();
		goods.setId(1000);
		goods.setName("C++");
		goods.setPrice(28.0);
		goods.setInventory(10);
		goods.setType(GoodsType.BOOK);
		goodsDao.addGoods(goods);
	}

	@Test
	public void testGetGoodsByIdList(){
		List<Integer> idList = Arrays.asList(10000, 10001);
		List<Goods> goodsList = goodsDao.getGoodsByIdList(idList);
		assertEquals(2, goodsList.size());
	}

	@Test
	public void testGetGoodsByType() {
		List<Goods> list = goodsDao.getGoodsByType(GoodsType.BOOK.toInt());
		assertSame(3, list.size());
		
		list = goodsDao.getGoodsByType(GoodsType.CLOTHS.toInt());
		assertSame(0, list.size());
	}

	@Test
	public void testGetGoodsByKeyword() {
		String keyword = "java";
		List<Goods> list = goodsDao.getGoodsByKeyword(keyword);
		assertSame(3, list.size());
		
		keyword = "c++";
		list = goodsDao.getGoodsByKeyword(keyword);
		assertSame(0, list.size());
	}

}
