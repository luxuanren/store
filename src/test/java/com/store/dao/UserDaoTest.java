package com.store.dao;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.store.model.User;

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
		String email = "867773467@qq.com";
		String password = "123";
		assertNotSame(null, userDao.FindUser(email, password));
		
		password = "456";
		assertSame(null, userDao.FindUser(email, password));
	}

	@Test
	public void testFindUserName() {
		String username = "luxuanren";
		assertSame(true, userDao.findUserName(username));
		username = "lxr";
		assertSame(false, userDao.findUserName(username));
	}

	@Test
	public void testFindEmail() {
		String email = "867773467@qq.com";
		assertSame(true, userDao.findEmail(email));
		email = "exuanlu@ericsson.com";
		assertSame(false, userDao.findEmail(email));
	}

	@Test
	public void testAddUser() {
		User user = new User();
		user.setUsername("user1");
		user.setEmail("user1@test.com");
		user.setPassword("123");
		assertSame(true, userDao.addUser(user));
	}

	@Test
	public void testAlterPassword() {
		String email = "867773467@qq.com";
		int uId = 1000;
		String oldPassword = "123";
		String newPassword = "456";
		userDao.alterPassword(uId, oldPassword, newPassword);
		assertNotSame(null, userDao.FindUser(email, newPassword));
		
		userDao.alterPassword(uId, newPassword, oldPassword);
		assertNotSame(null, userDao.FindUser(email, oldPassword));
	}

	@Test
	public void testUpdateUserIntegral() {
		String email = "867773467@qq.com";
		String password = "123";
		String uId = "1000";
		userDao.updateUserIntegral(uId, 10);
		assertThat(userDao.FindUser(email, password).getIntegral(), closeTo(10, 0.0001));
	}

	@Test
	public void testChargeAndReduceAccount() {
		String email = "867773467@qq.com";
		String password = "123";
		String uId = "1000";
		double base = 100;
		double num = 100;
		double error = 0.0001;
		
		assertThat(userDao.FindUser(email, password).getAccount(), closeTo(base, error));
		userDao.reduceAccount(uId, num);
		assertThat(userDao.FindUser(email, password).getAccount(), closeTo(base - num, error));
		userDao.chargeAccount(uId, num);
		assertThat(userDao.FindUser(email, password).getAccount(), closeTo(base, error));
	}

}
