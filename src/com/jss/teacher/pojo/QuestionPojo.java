package com.jss.teacher.pojo;

import java.util.Arrays;

public class QuestionPojo {
	private int Qid;
	private String Qtype;
	private String Qcontent;
	private String Qchoice;
	private String[] choice;
	private String Qanswer;
	private int Qdegree;
	private String Cname;
	private String Qchapter;
	private String Qparagraph;
	
	private Float score;//题目分数
	private String Answer;//学生答案
	private Float grade;//学生得到的分数
	
	
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String Answer) {
		this.Answer = Answer;
	}
	public String[] getChoice() {
		return choice;
	}
	public void setChoice(String[] choice) {
		this.choice = choice;
	}
	public int getQid() {
		return Qid;
	}
	public void setQid(int qid) {
		Qid = qid;
	}
	public String getQtype() {
		return Qtype;
	}
	public void setQtype(String qtype) {
		Qtype = qtype;
	}
	public String getQcontent() {
		return Qcontent;
	}
	public void setQcontent(String qcontent) {
		Qcontent = qcontent;
	}
	public String getQchoice() {
		return Qchoice;
	}
	public void setQchoice(String qchoice) {
		Qchoice = qchoice;
	}
	public String getQanswer() {
		return Qanswer;
	}
	public void setQanswer(String qanswer) {
		Qanswer = qanswer;
	}
	public int getQdegree() {
		return Qdegree;
	}
	public void setQdegree(int qdegree) {
		Qdegree = qdegree;
	}
	public String getQchapter() {
		return Qchapter;
	}
	public void setQchapter(String qchapter) {
		Qchapter = qchapter;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	
	public String getQparagraph() {
		return Qparagraph;
	}
	public void setQparagraph(String qparagraph) {
		Qparagraph = qparagraph;
	}
	@Override
	public String toString() {
		return "QuestionPojo [Qid=" + Qid + ", Qtype=" + Qtype + ", Qcontent="
				+ Qcontent + ", Qchoice=" + Qchoice + ", choice="
				+ Arrays.toString(choice) + ", Qanswer=" + Qanswer
				+ ", Qdegree=" + Qdegree + ", Cname=" + Cname + ", Qchapter="
				+ Qchapter + ", Qparagraph=" + Qparagraph + ", score=" + score
				+ ", sAnswer=" + Answer + "]";
	}

	
}
