package com.store.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.store.model.Evaluation;
import com.store.util.DateUtil;
@SpringApplicationContext({"dispatcher-servlet.xml","classpath:applicationContext.xml"})
public class EvaluationDaoTest extends UnitilsJUnit4 {
	@SpringApplicationContext
	private ClassPathXmlApplicationContext context;
	@SpringBean("evaluationDao")
	private EvaluationDao evaluationDao;

	@Test
	public void testGetEvaluationByGoodId() {
		List<Evaluation> list = evaluationDao.getEvaluationByGoodId(10001);
		assertEquals(1, list.size());
	}

	@Test
	public void testAddEvaluation() {
		Evaluation e = new Evaluation();
		e.setGoodsId(10001);
		e.setEvalTime(DateUtil.getCurrentTime());
		e.setUsername("luxuanren");
		e.setInfo("this book is very useful");
		evaluationDao.addEvaluation(e);
	}

	@Test
	public void testAppendEvaluation() {
		String appendInfo = "realy good";
		evaluationDao.appendEvaluation(10001, "luxuanren", appendInfo, DateUtil.getCurrentTime());
		List<Evaluation> list = evaluationDao.getEvaluationByGoodId(10001);
		assertEquals(appendInfo, list.get(0).getAppendInfo());
		
	}

}
