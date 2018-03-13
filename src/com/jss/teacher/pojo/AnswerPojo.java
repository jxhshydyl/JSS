package com.jss.teacher.pojo;

public class AnswerPojo {
	private String ano;
	private String acontent;
	private String atime;
	private String tno;
	private String tname;
	private int tid;
	private int qid;
	private String ask_no;
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
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
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getAsk_no() {
		return ask_no;
	}
	public void setAsk_no(String ask_no) {
		this.ask_no = ask_no;
	}
	
	@Override
	public String toString() {
		return "AnswerPojo [ano=" + ano + ", acontent=" + acontent + ", atime="
				+ atime + ", tno=" + tno + ", tname=" + tname + ", tid=" + tid
				+ ", qid=" + qid + ", ask_no=" + ask_no + "]";
	}
	
}
