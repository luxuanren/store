package com.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * marks pattern is <goodsId&goodsId...>
 * @author luxuanren
 *
 */
@Repository
public class MarkDao {
	@Autowired
	private JdbcTemplate template;
	
	public boolean addUserMarks(String uId, String marks) {
		return false;
	}
	public String getMarksByUserId(String uId) {
		return null;
	}
	public boolean updateUserMarks(String marks) {
		return false;
	}
}
