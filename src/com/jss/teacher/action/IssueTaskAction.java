package com.jss.teacher.action;

import com.jss.teacher.service.IssueTaskService;
import com.jss.teacher.service.TaskService;

public class IssueTaskAction {
	private String cno;//要安排作业的班级编号
	private String end_time;//要安排的班级的截止时间
	private String tid;//安排作业的编号
	private IssueTaskService its;
	private TaskService ts;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String issueTask(){
		its=new IssueTaskService();
		ts=new TaskService();
		its.issueTask(tid,end_time,cno);
		int num = ts.updateTaskState(tid);
		if(num==0){
			return "error";
		}else{
			return "issuetask";
		}
		
	}
}
