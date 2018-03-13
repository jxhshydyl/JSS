package com.jss.teacher.service;

import java.util.List;

import com.jss.teacher.dao.AnswerDao;
import com.jss.teacher.pojo.AnswerPojo;

public class AnswerService {

	public void addAnswer(AnswerPojo ansObj) {
		AnswerDao dao=new AnswerDao();
		dao.addAnswer(ansObj);
	}
	public List<AnswerPojo> queryAnswer(String ano) {
		AnswerDao dao=new AnswerDao();
		List<AnswerPojo> list=dao.queryAnswer(ano);
		return list;
	}
}
