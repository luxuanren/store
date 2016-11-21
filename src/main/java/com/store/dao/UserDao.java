package com.store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
	
	public User FindUser(String email, String password) {
		final String sql = "select * from user where u_email=? and u_password=?";
		final User user = new User();
		template.query(sql, new String[]{email, password}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setLevel(rs.getInt(5));
				user.setAccount(rs.getFloat(6));
			}
		});
		return user.getId() == 0 ? null : user;
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
