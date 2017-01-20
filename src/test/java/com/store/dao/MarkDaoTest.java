package com.store.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class MarkDaoTest extends UnitilsJUnit4 {

	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("markDao")
	private MarkDao markDao;

	@Test
	public void testAddUserMarks() {
		int userId = 1000;
		String markList = "10000";
		markDao.addUserMarks(userId, markList);
	}

	@Test
	public void testAppendMarks() {
		int userId = 1000;
		int goodsId = 10001;
		String base = "10000";
		assertEquals(base, markDao.getMarksByUserId(userId));
		markDao.appendMarks(userId, goodsId);
		assertEquals("10000,10001", markDao.getMarksByUserId(userId));
	}

	@Test
	public void testGetMarksByUserId() {
		int userId = 1000;
		assertNotEquals(null, markDao.getMarksByUserId(userId));
	}

	@Test
	public void testUpdateUserMarks() {
		int userId = 1000;
		String markList = "10000";
		markDao.updateUserMarks(userId, markList);
		assertEquals("10000", markDao.getMarksByUserId(userId));
	}

}
