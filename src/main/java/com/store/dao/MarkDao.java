package com.store.dao;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * marks pattern is [goodsId, goodsId]
 * 
 * @author luxuanren
 *
 */
@Repository
public class MarkDao {
	@Autowired
	private JdbcTemplate template;

	public boolean addUserMarks(int uId, String markList) {
		String sql = "INSERT INTO mark VALUES(?,?)";
		Object[] args = new Object[] { uId, markList };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean appendMarks(int uId, int gId) {
		String sql = "UPDATE mark SET m_list=concat(m_list,',',?) WHERE u_id=?";
		Object[] args = new Object[] { gId, uId };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		return template.update(sql, args, argTypes) == 1;
	}
	
	public String getMarksByUserId(int uId) {
		String sql = "SELECT m_list FROM mark WHERE u_id=?";
		Object[] args = new Object[] { uId };
		int[] argTypes = new int[] { Types.INTEGER };
		return template.queryForObject(sql, args, argTypes, String.class);
	}

	public boolean updateUserMarks(int uId, String markList) {
		String sql = "UPDATE mark SET m_list=? WHERE u_id=?";
		Object[] args = new Object[] {markList, uId };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		return template.update(sql, args, argTypes) == 1;
	}
}
