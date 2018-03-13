package com.jss.teacher.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jss.teacher.pojo.AddQuestionPojo;
import com.jss.teacher.pojo.Condition_QuestionPojo;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.QuestionService;
import com.jss.teacher.util.Pager;
import com.jss.teacher.util.SaveCondition;
import com.opensymphony.xwork2.ActionContext;

public class QuestionAction {
	private QuestionService qs;
	private int pageNow=1;
	private String isFirst;
	private Condition_QuestionPojo cq;
	private QuestionPojo qp;
	private AddQuestionPojo aqp;
	private String[] Qid;
	
	public String[] getQid() {
		return Qid;
	}
	public void setQid(String[] qid) {
		Qid = qid;
	}
	public AddQuestionPojo getAqp() {
		return aqp;
	}
	public void setAqp(AddQuestionPojo aqp) {
		this.aqp = aqp;
	}
	private String daan;
	private String daan1;
	public String getDaan1() {
		return daan1;
	}
	public void setDaan1(String daan1) {
		this.daan1 = daan1;
	}
	public String getDaan() {
		return daan;
	}
	public void setDaan(String daan) {
		this.daan = daan;
	}
	public QuestionPojo getQp() {
		return qp;
	}
	public void setQp(QuestionPojo qp) {
		this.qp = qp;
	}
	public Condition_QuestionPojo getCq() {
		return cq;
	}
	public void setCq(Condition_QuestionPojo cq) {
		this.cq = cq;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		if(pageNow==0){
			pageNow=1;
		}
		this.pageNow = pageNow;
	}
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
	/**
	 * 查询所有问题信息
	 */
	public String queryAllQuestion(){
		Map<String, Object> session=ActionContext.getContext().getSession();
		List<QuestionPojo> list=null;
		Pager page;
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		if("0".equals(isFirst)){//如果是点击试题列表查询所有问题
			session.put("condition", null);
		}
		if(session.get("condition") != null){//如果是点击分页查询所有问题
			qs =new QuestionService();
			cq=SaveCondition.cq;
		    page=new Pager(pageNow,qs.queryCount(cq.getCno(), cq.getKeyword(),tno));
			list=new ArrayList<QuestionPojo>();
			//按条件查询问题
			list=qs.queryQuestion(cq.getCno(), cq.getKeyword(), (pageNow-1)*page.getPageSize(), page.getPageSize(),tno);
			session.put("condition", cq);
		}else{
			qs =new QuestionService();
		    page=new Pager(pageNow,qs.queryAllCount(tno));
			list=new ArrayList<QuestionPojo>();
			list=qs.queryAllQuestion((pageNow-1)*page.getPageSize(),page.getPageSize(),tno);
		}
		session.put("question", list);
		session.put("page", page);
		return "question";
	}
	/**
	 * 按条件查询问题信息
	 */
	public String queryQuestion(){
		qs =new QuestionService();
		SaveCondition.cq=cq;
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
	    Pager page=new Pager(pageNow,qs.queryCount(cq.getCno(), cq.getKeyword(),tno));
		List<QuestionPojo> list=new ArrayList<QuestionPojo>();
		list=qs.queryQuestion(cq.getCno(), cq.getKeyword(), (pageNow-1)*page.getPageSize(), page.getPageSize(),tno);
		session.put("question", list);
		session.put("condition", cq);
		session.put("page", page);
		return "condition_question";
	}
	public String addQuestion(){
		qs =new QuestionService();
		qs.addQuestion(daan,aqp,qp,daan1);
		return "addquestion";
	}
	public String checkQuestionDetail(){
		qs =new QuestionService();
		qp=qs.queryQuestion(qp.getQid());
		Map<String, Object> session=ActionContext.getContext().getSession();
		session.put("QPojo", qp);
		return "checkquestiondetail";
	}
	public String queryModifyQuestionDetail(){
		qs =new QuestionService();
		qp=qs.queryQuestion(qp.getQid());
		Map<String, Object> session=ActionContext.getContext().getSession();
		session.put("QPojo", qp);
		return "querymodifyquestiondetail";
	}
	public String modifyQuestion(){
		qs =new QuestionService();
		qp=qs.modifyQuestion(daan,aqp,qp,daan1);
		return "modifyquestion";
	}
	public String deleteQuestion(){
		qs =new QuestionService();
		Qid=Qid[0].split(",");
		int[] qid=new int[Qid.length];
		for(int i=0;i<Qid.length;i++){
			qid[i]=Integer.parseInt(Qid[i]);
		}
		qs.deleteQuestion(qid);
		return "deletequestioin";
	}
}
