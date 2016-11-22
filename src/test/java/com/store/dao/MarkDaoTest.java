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
		int uId = 1000;
		String markList = "10000";
		markDao.addUserMarks(uId, markList);
	}

	@Test
	public void testAppendMarks() {
		int uId = 1000;
		int gId = 10001;
		String base = "10000";
		assertEquals(base, markDao.getMarksByUserId(uId));
		markDao.appendMarks(uId, gId);
		assertEquals("10000,10001", markDao.getMarksByUserId(uId));
	}

	@Test
	public void testGetMarksByUserId() {
		int uId = 1000;
		assertNotEquals(null, markDao.getMarksByUserId(uId));
	}

	@Test
	public void testUpdateUserMarks() {
		int uId = 1000;
		String markList = "10000";
		markDao.updateUserMarks(uId, markList);
		assertEquals("10000", markDao.getMarksByUserId(uId));
	}

}
