package com.store.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.store.model.Order;

/**
 * o_info pattern is <g_id:num:isEvaluate> eg "1001:2:0"
 * 0 represent don't evaluate , 1 represent evaluate yet
 * goods id is 1001, user buy two goods, and user don't evaluate it until now 
 * @author luxuanren
 *
 */
@Repository
public class OrderDao {
	@Autowired
	private JdbcTemplate template;
	
	public boolean addOrder(Order order) {
		return false;
	}
	public List<Order> getOrderListByUserId(String uId) {
		return null;
	}
	public String getOrderInfo(String oId) {
		return null;
	}
	public boolean updateOrderInfo(String info) {
		return false;
	}
}
