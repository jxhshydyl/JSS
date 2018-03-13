package com.jss.teacher.pojo;

public class TaskPojo {
	private int Tid;
	private String task_name;
	private String tappendixes;
	private float tscore;
	private String tno;
	private String tname;
	private String subtime;
	private String ttype;
	private String tstate;
	private String cno;//课程编号
	private String cname;//课程名称
	private String tchapter;
	
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public int getTid() {
		return Tid;
	}
	public void setTid(int tid) {
		Tid = tid;
	}
	public String getTstate() {
		return tstate;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setTstate(String tstate) {
		this.tstate = tstate;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTappendixes() {
		return tappendixes;
	}
	public void setTappendixes(String tappendixes) {
		this.tappendixes = tappendixes;
	}
	public float getTscore() {
		return tscore;
	}
	public void setTscore(float tscore) {
		this.tscore = tscore;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getTchapter() {
		return tchapter;
	}
	public void setTchapter(String tchapter) {
		this.tchapter = tchapter;
	}
	@Override
	public String toString() {
		return "TaskPojo [Tid=" + Tid + ", task_name=" + task_name
				+ ", tappendixes=" + tappendixes + ", tscore=" + tscore
				+ ", tno=" + tno + ", tname=" + tname + ", subtime=" + subtime
				+ ", tstate=" + tstate + ", cno=" + cno + ", tchapter="
				+ tchapter + "]";
	}
	
}
