package com.jss.teacher.pojo;

public class StudentPojo {
	private int id;
	private String Sno;
	private String Password;
	private String Sname;
	private String Ssex;
	private String Sbirthday;
	private String Sage;
	private String Syear;
	private String Sacademy;
	private String Smajor;
	private String Sclass;
	private String taskName;//作业名称
	private String taskChapter;//作业章节
	private String courseName;//课程名称
	private Float taskGrade;//作业成绩
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Float getTaskGrade() {
		return taskGrade;
	}
	public void setTaskGrade(Float taskGrade) {
		this.taskGrade = taskGrade;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskChapter() {
		return taskChapter;
	}
	public void setTaskChapter(String taskChapter) {
		this.taskChapter = taskChapter;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public String getSbirthday() {
		return Sbirthday;
	}
	public void setSbirthday(String sbirthday) {
		Sbirthday = sbirthday;
	}
	public String getSage() {
		return Sage;
	}
	public void setSage(String sage) {
		Sage = sage;
	}
	public String getSyear() {
		return Syear;
	}
	public void setSyear(String syear) {
		Syear = syear;
	}
	public String getSacademy() {
		return Sacademy;
	}
	public void setSacademy(String sacademy) {
		Sacademy = sacademy;
	}
	public String getSmajor() {
		return Smajor;
	}
	public void setSmajor(String smajor) {
		Smajor = smajor;
	}
	public String getSclass() {
		return Sclass;
	}
	public void setSclass(String sclass) {
		Sclass = sclass;
	}
	
}
