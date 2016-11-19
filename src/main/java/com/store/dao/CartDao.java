package com.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.store.model.Cart;
/**
 * when user login out or session invalid, 
 * store user's cart in database
 * @author luxuanren
 *
 */
@Repository
public class CartDao {
	@Autowired
	private JdbcTemplate template;
	
	public boolean updateCartByUserId(String uId, Cart cart) {
		return false;
	}
}
