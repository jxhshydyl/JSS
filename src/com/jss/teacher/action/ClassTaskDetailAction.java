package com.jss.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.ClassTaskDetailPojo;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.StudentPojo;
import com.jss.teacher.service.ClassTaskDetailService;
import com.jss.teacher.service.StudentService;

/**
 * 查看班级需要上交的作业的信息
 * @author Administrator
 */
public class ClassTaskDetailAction {
	private ClassTaskDetailService ctds;
	private StudentService ss;
	private String cno;
	private String tid;
	private String sid;
	private String qids;//教师评分的问题id
	private String scores;//教师评分的问题分数
	private String id;//学生提交的作业id
	private String totalScore;//学生总分
	
	
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getQids() {
		return qids;
	}
	public void setQids(String qids) {
		this.qids = qids;
	}
	public String getScores() {
		return scores;
	}
	public void setScores(String scores) {
		this.scores = scores;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 查询班级任务学生提交的作业信息
	 * @return
	 */
	public String queryClassTaskDetail(){
		ctds=new ClassTaskDetailService();
		List<ClassTaskDetailPojo> list= ctds.queryClassTaskDetail(tid,cno);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("classTaslDetail",list);
		return "queryclasstaskdetail";
	}
	/**
	 * 查询学生提交的作业的详情
	 * @return
	 */
	public String querySubmitTaskDetail(){
		ctds=new ClassTaskDetailService();
		List<QuestionPojo> list = ctds.querySubmitTaskDetail(sid);//学生作业详细信息
		/*float grade=0;
		for(QuestionPojo qusetion:list){
			if("单选题".equals(qusetion.getQtype())||"多选题".equals(qusetion.getQtype()) || "判断题".equals(qusetion.getQtype())){
				if(qusetion.getAnswer()!=null && qusetion.getAnswer().equals(qusetion.getQanswer())){
					grade=grade+qusetion.getScore();
				}
			}
		}*/
		ss=new StudentService();
		StudentPojo queryStudentByStaskId = ss.queryStudentByStaskId(sid);//学生作业基本信息
		//queryStudentByStaskId.setTaskGrade(grade);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("studentTaskDetail",list);
		request.setAttribute("studentTaskInformation",queryStudentByStaskId);
		return "querysubmittaskdetail";
	}
	/**
	 * 保存已批阅的作业详情
	 */
	public String saveSubmitTaskDetail(){
		ss=new StudentService();
		int num = ss.saveStudentTaskScoreByStaskId(id,totalScore);
		ctds=new ClassTaskDetailService();
		JSONArray arrayQids = JSONArray.fromObject(qids);
		JSONArray arrayScores = JSONArray.fromObject(scores);
		int key = ctds.saveSubmitTaskDetailScore(id,arrayQids,arrayScores);
		return "savesubmittaskdetail";
	}
}
