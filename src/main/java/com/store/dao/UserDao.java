package com.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.store.model.User;
import com.store.model.UserLevel;
/**
 * when user create an account, then we insert a clause in user,mart and cart table 
 * @author luxuanren
 *
 */
@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate template;
	
	public boolean FindUser(String email, String password) {
		return false;
	}
	public boolean findUserName(String username) {
		return false;
	}
	public boolean findEmail(String email) {
		return false;
	}
	public boolean addUser(User user) {
		return false;
	}
	public boolean alterPassword(String uId, String password) {
		return false;
	}
	public boolean updateUserLevel(String uId, UserLevel level) {
		return false;
	}
	public boolean reduceAccount(String uId, float num) {
		return false;
	}
	public boolean chargeAccount(String uId, float num) {
		return false;
	}
}
