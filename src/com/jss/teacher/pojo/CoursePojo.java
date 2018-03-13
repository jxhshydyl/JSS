package com.jss.teacher.pojo;

public class CoursePojo {
	private String cno;//班级编号
	private String cname;//班级名称
	private String cid;//课程编号
	private String course_name;//课程名称
	private String major;//专业编号
	private String mno;//专业名称
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	@Override
	public String toString() {
		return "CoursePojo [cno=" + cno + ", cname=" + cname + ", cid=" + cid
				+ ", course_name=" + course_name + ", major=" + major
				+ ", mno=" + mno + "]";
	}

	
}
