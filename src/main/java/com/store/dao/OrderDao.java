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
import com.store.model.Order;

/**
 * o_info pattern is [g_id:num:isEvaluate] eg: "[1001:2:0, 1002:2:1]" this pattern is
 * consisted of List.toString and TradeItem.toString(), 0 represent don't evaluate,
 * 1 represent evaluate yet. goods id is 1001, user buy two goods, and user don't 
 * evaluate it until now.
 * 
 * @author luxuanren
 *
 */
@Repository
public class OrderDao {
	@Autowired
	private JdbcTemplate template;

	public boolean addOrder(Order order) {
		String sql = "INSERT INTO orders VALUES(?,?,?,?)";
		Object[] args = new Object[] { order.getId(), order.getDate(), order.getUserId(),
				order.getTradeList().toString() };
		int[] argTypes = new int[] { Types.INTEGER, Types.TIMESTAMP, Types.INTEGER, Types.VARCHAR };
		template.update(sql, args, argTypes);
		return false;
	}

	public List<Order> getOrderList(int uId) {
		String sql = "SELECT * FROM orders WHERE u_id=?";
		final ArrayList<Order> list = new ArrayList<>();
		template.query(sql, new Object[] { uId }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDate(rs.getTimestamp(2).toString());
				order.setUserId(rs.getInt(3));
				order.setTradeList(rs.getString(4));
				list.add(order);
			}

		});
		return list;
	}

	public Order getOrder(int oId) {
		String sql = "SELECT * FROM orders WHERE o_id=?";
		final Order order = new Order();
		template.query(sql, new Object[] { oId }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				order.setId(rs.getInt(1));
				order.setDate(rs.getTimestamp(2).toString());
				order.setUserId(rs.getInt(3));
				order.setTradeList(rs.getString(4));
			}

		});
		return order;
	}

	public boolean updateOrder(int oId, String oInfo) {
		String sql = "UPDATE orders SET o_info=? WHERE o_id=?";
		Object[] args = new Object[] { oInfo, oId };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		return template.update(sql, args, argTypes) == 1;
	}
}
