package com.jss.teacher.pojo;

public class MessageDetailPojo {
	private int Ano;
	private String Ask_time;
	private String Sno;
	private String Sname;
	private String Qid;
	private String Tid;
	private String Ask_content;
	private String Qcontent;
	private String Tno;
	private String Tname;
	
	public String getQid() {
		return Qid;
	}
	public void setQid(String qid) {
		Qid = qid;
	}
	public String getTid() {
		return Tid;
	}
	public void setTid(String tid) {
		Tid = tid;
	}
	public int getAno() {
		return Ano;
	}
	public void setAno(int ano) {
		Ano = ano;
	}
	public String getAsk_time() {
		return Ask_time;
	}
	public void setAsk_time(String ask_time) {
		Ask_time = ask_time;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getAsk_content() {
		return Ask_content;
	}
	public void setAsk_content(String ask_content) {
		Ask_content = ask_content;
	}
	public String getQcontent() {
		return Qcontent;
	}
	public void setQcontent(String qcontent) {
		Qcontent = qcontent;
	}
	public String getTno() {
		return Tno;
	}
	public void setTno(String tno) {
		Tno = tno;
	}
	public String getTname() {
		return Tname;
	}
	public void setTname(String tname) {
		Tname = tname;
	}
	@Override
	public String toString() {
		return "MessageDetailPojo [Ano=" + Ano + ", Ask_time=" + Ask_time
				+ ", Sno=" + Sno + ", Sname=" + Sname + ", Qid=" + Qid
				+ ", Tid=" + Tid + ", Ask_content=" + Ask_content
				+ ", Qcontent=" + Qcontent + ", Tno=" + Tno + ", Tname=" + Tname + "]";
	}
	
}
