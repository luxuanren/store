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

import com.store.constant.OrderStatus;
import com.store.model.Order;
import com.store.model.TradeList;

/**
 * o_info use json pattern
 * @author luxuanren
 *
 */
@Repository
public class OrderDao {
	@Autowired
	private JdbcTemplate template;

	public boolean addOrder(Order order) {
		String sql = "INSERT INTO orders(o_time, u_id, o_info) VALUES(?,?,?)";
		Object[] args = new Object[] {order.getDate(), order.getUserId(), order.getTradeList().toString() };
		int[] argTypes = new int[] {Types.TIMESTAMP, Types.INTEGER, Types.VARCHAR };
		template.update(sql, args, argTypes);
		return false;
	}

	public List<Order> getOrderList(int userId) {
		String sql = "SELECT * FROM orders WHERE u_id=?";
		final ArrayList<Order> list = new ArrayList<>();
		template.query(sql, new Object[] { userId }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDate(rs.getTimestamp(2).toString());
				order.setUserId(rs.getInt(3));
				order.setTradeList(new TradeList(rs.getString(4)));
				list.add(order);
			}

		});
		return list;
	}

	public Order getOrder(int orderId) {
		String sql = "SELECT * FROM orders WHERE o_id=?";
		final Order order = new Order();
		template.query(sql, new Object[] { orderId }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				order.setId(rs.getInt(1));
				order.setDate(rs.getTimestamp(2).toString());
				order.setUserId(rs.getInt(3));
				order.setTradeList(new TradeList(rs.getString(4)));
				order.setStatus(OrderStatus.getOrderStatus(rs.getInt(5)));
			}

		});
		return order;
	}

	public boolean updateOrder(int orderId, String oInfo) {
		String sql = "UPDATE orders SET o_info=? WHERE o_id=?";
		Object[] args = new Object[] { oInfo, orderId };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER };
		return template.update(sql, args, argTypes) == 1;
	}
}
