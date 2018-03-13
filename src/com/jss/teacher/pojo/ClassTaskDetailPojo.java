package com.jss.teacher.pojo;
/**
 * 班级需要上交的作业的信息
 * @author Administrator
 *
 */
public class ClassTaskDetailPojo {
	private int id;//学生上交的序号（对应学生的该作业的详细信息）
	private String sno;//学号
	private String sname;//姓名
	private String task_name;//需要上交的作业名称
	private String tid;//需要上交的作业编号
	private String end_time;//作业的截止时间
	private String tstate;//作业的状态（是否被批阅）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getTstate() {
		return tstate;
	}
	public void setTstate(String tstate) {
		this.tstate = tstate;
	}
	@Override
	public String toString() {
		return "ClassTaskDetailPojo [id=" + id + ", sno=" + sno + ", sname="
				+ sname + ", task_name=" + task_name + ", tid=" + tid
				+ ", end_time=" + end_time + ", Tstate=" + tstate + "]";
	}
	
}
