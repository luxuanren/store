package com.store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.store.model.User;


/**
 * when user create an account, then we insert a clause in user,mart and cart
 * table
 * 
 * @author luxuanren
 *
 */
@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate template;

	public User FindUser(String email, String password) {
		final String sql = "SELECT * FROM user WHERE u_email=? AND u_password=?";
		final User user = new User();
		template.query(sql, new String[] { email, password }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setLevel(rs.getInt(5));
				user.setAccount(rs.getFloat(6));
				user.setIntegral(rs.getDouble(7));
			}
		});
		return user.getId() == 0 ? null : user;
	}

	public boolean findUserName(String username) {
		final String sql = "SELECT COUNT(*) FROM user WHERE u_name=?";
		return template.queryForObject(sql, new String[] { username }, Integer.class) > 0;
	}

	public boolean findEmail(String email) {
		final String sql = "SELECT COUNT(*) FROM user WHERE u_email=?";
		return template.queryForObject(sql, new String[] { email }, Integer.class) > 0;
	}

	public boolean addUser(User user) {
		String sql = "INSERT INTO user(u_name,u_email,u_password) VALUES(?,?,?)";
		String[] args = new String[] { user.getUsername(), user.getEmail(), user.getPassword() };
		int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean alterPassword(int uId, String oldPassword, String newPassword) {
		String sql = "UPDATE user SET u_password=? WHERE u_id=? AND u_password=?";
		Object[] args = new Object[] { newPassword, uId, oldPassword };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean updateUserIntegral(String uId, double score) {
		String sql = "UPDATE user SET u_integral=u_integral+? WHERE u_id=?";
		Object[] args = new Object[] {score, uId };
		int[] argTypes = new int[] { Types.DOUBLE, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean reduceAccount(String uId, double num) {
		String sql = "UPDATE user SET u_account=u_account-? WHERE u_id=?";
		Object[] args = new Object[] { num, uId };
		int[] argTypes = new int[] { Types.FLOAT, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean chargeAccount(String uId, double num) {
		String sql = "UPDATE user SET u_account=u_account+? WHERE u_id=?";
		Object[] args = new Object[] { num, uId };
		int[] argTypes = new int[] { Types.FLOAT, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}
}
