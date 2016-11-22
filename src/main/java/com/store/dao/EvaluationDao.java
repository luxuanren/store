package com.store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.store.model.Evaluation;

@Repository
public class EvaluationDao {
	@Autowired
	private JdbcTemplate template;

	public List<Evaluation> getEvaluationByGoodId(int gId) {
		String sql = "SELECT * FROM evaluation WHERE g_id=?";
		final List<Evaluation> list = new ArrayList<>();
		template.query(sql, new Object[] { gId }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Evaluation e = new Evaluation();
				e.setValid(rs.getInt(2) == 1);
				e.setGoodsId(rs.getInt(3));
				e.setEvalTime(rs.getTimestamp(4).toString());
				e.setUsername(rs.getString(5));
				e.setInfo(rs.getString(6));
				e.setAppendInfo(rs.getString(7));
				list.add(e);
			}
		});
		return list;
	}

	public boolean addEvaluation(Evaluation e) {
		String sql = "INSERT INTO evaluation(g_id,e_time,u_name,e_info) VALUES(?,?,?,?)";
		Object[] args = new Object[] { e.getGoodsId(), e.getEvalTime(), e.getUsername(), e.getInfo() };
		int[] argTypes = new int[] { Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}

	public boolean appendEvaluation(int gId, String username, String info) {
		String sql = "UPDATE evaluation SET e_append=? WHERE g_id=? AND u_name=?";
		Object[] args = new Object[] { info, gId, username };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR };
		return template.update(sql, args, argTypes) == 1;
	}
}
