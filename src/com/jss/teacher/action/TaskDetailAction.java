package com.jss.teacher.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TaskPojo;
import com.jss.teacher.pojo.TdetailPojo;
import com.jss.teacher.service.TaskDeatilService;
import com.jss.teacher.service.TaskService;
import com.jss.teacher.util.DBUtil;

public class TaskDetailAction {
	//private String tno;//教师编号
	private String tid;//作业编号
	private String qid;//题目id
	private String newQid;//需要替换的新的问题id
	private String qtype;
	private String qchapter;
	private String result;//ajax返回的数据
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getQchapter() {
		return qchapter;
	}

	public void setQchapter(String qchapter) {
		this.qchapter = qchapter;
	}
	private TaskDeatilService tds;
	private TaskService ts;

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}


	public TaskDeatilService getTds() {
		return tds;
	}


	public void setTds(TaskDeatilService tds) {
		this.tds = tds;
	}
	
	public String getNewQid() {
		return newQid;
	}

	public void setNewQid(String newQid) {
		this.newQid = newQid;
	}

	/**
	 * 通过作业编号查询作业详细信息
	 * @return
	 */
	public String queryTaskDetail(){
		tds=new TaskDeatilService();
		ts=new TaskService();
		TaskPojo task = ts.queryTask(tid);//得到作业的基本信息
		List<QuestionPojo> list = tds.queryTaskDetail(tid);//得到作业的题目
		List<TdetailPojo> list1 = tds.queryQuestionTypeAndScore(tid);//得到作业的分数情况
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("question", list);
		request.setAttribute("detail", list1);
		request.setAttribute("task", task);
		return "querytaskdetail";
	}
	/**
	 * 删除作业题目
	 */
	public String deleteTaskQuestion(){
		tds=new TaskDeatilService();
		ts=new TaskService();
		tds.deleteTaskQuestion(qid,tid);//删除作业的某一个题目
		List<TdetailPojo> list = tds.queryQuestionTypeAndScore(tid);//得到作业的分数情况
		float totalscore=0;//总分
		for(int i=0;i<list.size();i++){
			totalscore=totalscore+list.get(i).getScore();
		}
		int num=ts.updateTaskScore(totalscore,tid);
		if(num ==0 ){
			return "error";
		}
		return "deletetaskquestion";
	}
	/**
	 * 查询类似题目用于替换题目
	 */
	public String querySilimarQuestion(){
		tds=new TaskDeatilService();
		List<QuestionPojo> list=tds.querySilimarQuestion(qtype, qchapter,tid);
		Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
		return "querysilimarquestion";
	}
	/**
	 * 替换相似问题
	 * @return
	 */
	public String replaceSilimarQuestion(){
		tds=new TaskDeatilService();
		int num = tds.replaceSilimarQuestion(newQid,qid,tid);
		Map<String,Object> map = new HashMap<String,Object>();
		if(num!=0){
			map.put("isSuccess",true);
		}else{
			map.put("isSuccess",false);
		}
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
		return "replacesilimarquestion";
	}
}
