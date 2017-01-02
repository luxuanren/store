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

import com.store.model.Goods;
import com.store.model.GoodsType;

@Repository
public class GoodsDao {
	@Autowired
	private JdbcTemplate template;

	public boolean addGoods(Goods goods) {
		String sql = "INSERT INTO goods(g_id,g_name,g_price,g_inventory,g_type) VALUES(?,?,?,?,?)";
		Object[] args = new Object[] { goods.getId(), goods.getName(), goods.getPrice(), goods.getInventory(),
				goods.getType().toInt() };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.DOUBLE, Types.INTEGER, Types.INTEGER };
		return template.update(sql, args, argTypes) == 1;
	}

	public List<Goods> getGoodsByType(int type) {
		String sql = "SELECT * FROM goods WHERE g_type=?";
		final ArrayList<Goods> list = new ArrayList<>();
		template.query(sql, new Object[] { type }, new int[] { Types.INTEGER }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Goods goods = new Goods();
				goods.setId(rs.getInt(1));
				goods.setName(rs.getString(2));
				goods.setPrice(rs.getDouble(3));
				goods.setInventory(rs.getInt(4));
				goods.setType(GoodsType.getType(rs.getInt(5)));
				goods.setValid(rs.getInt(6) == 1);
				list.add(goods);
			}
		});
		return list;
	}

	public List<Goods> getGoodsByKeyword(String keyword) {
		String sql = "SELECT * FROM goods WHERE g_name LIKE concat('%',?,'%') OR g_title LIKE concat('%',?,'%')";
		final ArrayList<Goods> list = new ArrayList<>();
		template.query(sql, new Object[] { keyword, keyword }, new int[] { Types.VARCHAR, Types.VARCHAR },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Goods goods = new Goods();
						goods.setId(rs.getInt(1));
						goods.setName(rs.getString(2));
						goods.setPrice(rs.getDouble(3));
						goods.setInventory(rs.getInt(4));
						goods.setType(GoodsType.getType(rs.getInt(5)));
						goods.setValid(rs.getInt(6) == 1);
						goods.setTitle(rs.getString(7));
						goods.setDetail(rs.getString(8));
						list.add(goods);
					}
				});
		return list;
	}

	public List<Goods> getGoodsByIdList(List<Integer> goodsIdList) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM goods WHERE g_id in(");
		int length = goodsIdList.size();
		for (int i = 0; i < length; i++) {
			sqlBuilder.append("?,");
		}
		sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
		sqlBuilder.append(")");

		String sql = sqlBuilder.toString();
		final List<Goods> list = new ArrayList<>();
		Object[] args = new Object[length];

		for (int i = 0; i < length; i++) {
			args[i] = goodsIdList.get(i);
		}

		template.query(sql, args, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Goods goods = new Goods();
				goods.setId(rs.getInt(1));
				goods.setName(rs.getString(2));
				goods.setPrice(rs.getDouble(3));
				goods.setInventory(rs.getInt(4));
				goods.setType(rs.getInt(5));
				goods.setTitle(rs.getString(6));
				goods.setDetail(rs.getString(7));
				list.add(goods);
			}
		});
		return list;
	}
}
