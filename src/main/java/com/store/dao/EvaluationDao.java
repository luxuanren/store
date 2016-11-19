package com.store.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.store.model.Evaluation;

@Repository
public class EvaluationDao {
	@Autowired
	private JdbcTemplate template;
	
	public List<Evaluation> getEvaluationByGoodsId(String gId) {
		return null;
	}
	public boolean addEvaluation(Evaluation evaluation) {
		return false;
	}
	public boolean appendEvaluation(Evaluation evaluation) {
		return false;
	}
}
