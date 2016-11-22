package com.store.dao;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * when user login out or session invalid, store user's cart in database
 * 
 * @author luxuanren
 *
 */
@Repository
public class CartDao {
	@Autowired
	private JdbcTemplate template;

	public boolean addCartList(int uId, String cartList) {
		String sql = "INSERT INTO cart VALUES(?,?)";
		Object[] args = new Object[] { uId, cartList };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}
	
	public String getCartList(int uId) {
		String sql = "SELECT c_list FROM cart WHERE u_id=?";
		Object[] args = new Object[] { uId };
		int[] argTypes = new int[] { Types.INTEGER };
		return template.queryForObject(sql, args, argTypes, String.class);
	}

	public boolean updateCart(int uId, String cartList) {
		String sql = "UPDATE cart SET c_list=? WHERE u_id=?";
		Object[] args = new Object[] {cartList, uId };
		int[] argTypes = new int[] {Types.VARCHAR ,Types.VARCHAR};
		return template.update(sql, args, argTypes) == 1;
	}
}
