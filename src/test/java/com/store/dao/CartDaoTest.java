package com.store.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class CartDaoTest extends UnitilsJUnit4{
	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("cartDao")
	private CartDao cartDao;

	@Test
	public void testAddCartList() {
		int uId = 1000;
		String cartList = "10000:1,10001:2";
		cartDao.addCartList(uId, cartList);
	}
	
	@Test
	public void testGetCartList() {
		int uId = 1000;
		String cartList = "10000:1,10001:2";
		assertEquals(cartList, cartDao.getCartList(uId));
	}

	@Test
	public void testUpdateCart() {
		int uId = 1000;
		String cartList = "10000:1,10001:2";
		cartDao.updateCart(uId, cartList);
		assertEquals(cartList, cartDao.getCartList(uId));
	}

}
