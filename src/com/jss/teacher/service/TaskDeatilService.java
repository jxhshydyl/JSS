package com.jss.teacher.service;

import java.util.List;

import com.jss.teacher.dao.TaskdetailDao;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TdetailPojo;



public class TaskDeatilService {
	/**
	 * 根据作业id查询作业题目
	 * @param tid
	 * @return
	 */
	public List<QuestionPojo> queryTaskDetail(String tid) {
		TaskdetailDao dao=new TaskdetailDao();
		List<QuestionPojo> list=dao.queryTaskDetail(Integer.parseInt(tid));
		return list;
	}
	/**
	 * 根据作业id查询作业题目的所有类型和每种类型的分数
	 * @param tid
	 * @return
	 */
	public List<TdetailPojo> queryQuestionTypeAndScore(String tid) {
		TaskdetailDao dao=new TaskdetailDao();
		List<TdetailPojo> list=dao.queryQuestionTypeAndScore(Integer.parseInt(tid));
		return list;
	}
	/**
	 * 根据题目id和作业id删除题目
	 * @param qid
	 * @param tid
	 */
	public void deleteTaskQuestion(String qid, String tid) {
		TaskdetailDao dao=new TaskdetailDao();
		dao.deleteTaskQuestion(qid,tid);
	}
	public List<QuestionPojo> querySilimarQuestion(String qtype, String qchapter,String tid){
		TaskdetailDao dao=new TaskdetailDao();
		List<QuestionPojo> list = dao.querySilimarQuestion(qtype,qchapter,tid);
		return list;
	}
	public int replaceSilimarQuestion(String newQid, String qid, String tid) {
		TaskdetailDao dao=new TaskdetailDao();
		int num = dao.replaceSilimarQuestion(newQid,qid,tid);
		return num;
	}
}
