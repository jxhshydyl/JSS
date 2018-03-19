package com.jss.teacher.service;

import java.util.List;

import com.jss.teacher.dao.CourseDao;
import com.jss.teacher.dao.QuestionDao;
import com.jss.teacher.pojo.AddQuestionPojo;
import com.jss.teacher.pojo.CodePojo;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.util.ManageOptioin;
import com.jss.teacher.util.SaveCondition;

public class QuestionService {
	/**
	 *查询问题总数 
	 */
	public int queryAllCount(String tno){
		QuestionDao dao=new QuestionDao();
		int num=dao.queryAllQuestionCount(tno);
		return num;
	}
	/**
	 *按条件查询问题总数 
	 */
	public int queryCount(String Cno,String keyword,String tno){
		QuestionDao dao=new QuestionDao();
		int num=0;
		if("".equals(keyword.trim())&&"course".equals(Cno)){
			num=dao.queryAllQuestionCount(tno);
		}else if(!"".equals(keyword.trim())){//按关键字查询
			num=dao.queryQuestionCount(keyword,tno);
		}else{//按课程查询
			num=dao.queryQuestionCounts(Cno);
		}
		return num;
	}
	/**
	 * 查询所有的问题信息
	 */
	public List<QuestionPojo> queryAllQuestion(int pageNow,int pageSize,String tno){
		QuestionDao dao=new QuestionDao();
		List<QuestionPojo> list=dao.queryAllQuestion(tno,pageNow, pageSize);
		return list;
	}
	/**
	 * 根据条件查询所有的问题信息
	 */
	public List<QuestionPojo> queryQuestion(String Cno,String keyword,int pageNow,int pageSize,String tno){
		QuestionDao dao=new QuestionDao();
		List<QuestionPojo> list = null;
		if("".equals(keyword.trim())&&"course".equals(Cno)){
			list=dao.queryAllQuestion(tno,pageNow, pageSize);
		}else if(!"".equals(keyword.trim())){
			list=dao.queryQuestion(tno,keyword, pageNow, pageSize);
		}else{
			list=dao.queryQuestions(Cno, pageNow, pageSize);
		}
		return list;
	}
	/**
	 * 根据题目id查询问题信息
	 */
	public QuestionPojo queryQuestion(int Qid){
		QuestionDao dao=new QuestionDao();
		QuestionPojo qpojo = null;
		qpojo=dao.queryQuestions(Qid);
		for(int i=0;i<SaveCondition.Qtype.length;i++){
			if(SaveCondition.Qtype[i]==qpojo.getQtype()){
				qpojo.setQtype(i+1+"");
			}
		}
		return qpojo;
	}
	/**
	 * 单个增加问题信息
	 */
	public int addQuestion(String Qanswer,AddQuestionPojo aqp,QuestionPojo qp,String daan1){
		qp=ManageOptioin.manageOption(Qanswer, aqp, qp, daan1);
		QuestionDao dao=new QuestionDao();
		dao.addQuestion(qp);
		return 0;
	}
	/**
	 * 批量增加问题信息
	 */
	public int addQuestion(QuestionPojo qp){
		QuestionDao dao=new QuestionDao();
		dao.addQuestion(qp);
		return 0;
	}
	public QuestionPojo modifyQuestion(int qid) {
		// TODO Auto-generated method stub
		return null;
	}
	public QuestionPojo modifyQuestion(String Qanswer,AddQuestionPojo aqp,QuestionPojo qp,String daan1) {
		qp=ManageOptioin.manageOption(Qanswer, aqp, qp, daan1);
		QuestionDao dao=new QuestionDao();
		dao.modifyQuestion(qp);
		return null;
	}
	public QuestionPojo deleteQuestion(int[] qid) {
		QuestionDao dao=new QuestionDao();
		for(int i=0;i<qid.length;i++){
			dao.deleteQuestion(qid[i]);
		}
		return null;
	}
	public int queryQuestionCount(String type, String cno, String chapter) {
		QuestionDao dao=new QuestionDao();
		int num=0;
		if("编程题".equals(type)) {
			num=dao.queryCodeQuestionCount(type,cno,chapter);
		}else {
			num=dao.queryQuestionCount(type,cno,chapter);
		}
		
		return num;
	}
	public int addCodeQuestion(CodePojo que) {
		QuestionDao dao=new QuestionDao();
		dao.addCodeQuestion(que);
		return 0;
	}
}
