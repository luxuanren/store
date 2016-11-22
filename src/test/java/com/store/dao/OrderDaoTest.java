package com.store.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.store.model.Order;
import com.store.model.TradeItem;
import com.store.util.DateUtil;
@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class OrderDaoTest extends UnitilsJUnit4{

	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("orderDao")
	private OrderDao orderDao;

	@Test
	public void testAddOrder() {
		Order order = new Order();
		String data = "[10001:1:1, 10002:1:0]";
		order.setId(10001000);
		order.setDate(DateUtil.getCurrentTime());
		order.setUserId(1000);
		order.setTradeList(data);
		orderDao.addOrder(order);
	}

	@Test
	public void testGetOrderList() {
		int userId = 1000;
		List<Order> list = orderDao.getOrderList(userId);
		assertSame(1, list.size());
		
		userId = 1001;
		list = orderDao.getOrderList(userId);
		assertSame(0, list.size());
	}

	@Test
	public void testGetOrder() {
		int oId = 10001000;
		Order order = orderDao.getOrder(oId);
		assertEquals(oId, order.getId());
	}

	@Test
	public void testUpdateOrder() {
		int userId = 1000;
		int oId = 10001000;
		List<Order> list = orderDao.getOrderList(userId);
		List<TradeItem> tradeList = list.get(0).getTradeList();
		TradeItem item = tradeList.get(1);
		assertEquals(false, item.isEvaluate());
		
		item.setEvaluate(true);
		orderDao.updateOrder(oId, tradeList.toString());
		list = orderDao.getOrderList(userId);
		item = list.get(0).getTradeList().get(1);
		assertEquals(true, item.isEvaluate());
		
		tradeList = list.get(0).getTradeList();
		item.setEvaluate(false);
		orderDao.updateOrder(oId, tradeList.toString());
		list = orderDao.getOrderList(userId);
		assertEquals(false, list.get(0).getTradeList().get(1).isEvaluate());
	}

}
