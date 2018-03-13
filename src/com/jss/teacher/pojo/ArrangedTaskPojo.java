package com.jss.teacher.pojo;
/**
 * 存放已经安排了的作业的信息
 * @author Administrator
 *
 */
public class ArrangedTaskPojo {
	private String class_name;//班级名称
	private String cno;//班级编号
	private String task_name;//作业名称
	private int tid;//作业编号
	private String course_name;//课程名称
	private String chapter;//章节
	private String end_time;//截止时间
	
	
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "ArrangedTaskPojo [class_name=" + class_name + ", cno=" + cno
				+ ", task_name=" + task_name + ", tid=" + tid
				+ ", course_name=" + course_name + ", chapter=" + chapter
				+ ", end_time=" + end_time + "]";
	}
	
}
