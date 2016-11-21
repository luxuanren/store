package com.store.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class UserDaoTest extends UnitilsJUnit4{
	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("userDao")
	private UserDao userDao;
	
	@Test
	public void testSetUp(){
		assertNotNull(context);
		assertNotNull(userDao);
	}
	
	@Test
	public void testFindUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserName() {
		String email = "867773467@qq.com";
		String password = "123";
		assertNotSame(null, userDao.FindUser(email, password));
	}

	@Test
	public void testFindEmail() {
		
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserLevel() {
		fail("Not yet implemented");
	}

	@Test
	public void testReduceAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargeAccount() {
		fail("Not yet implemented");
	}

}
