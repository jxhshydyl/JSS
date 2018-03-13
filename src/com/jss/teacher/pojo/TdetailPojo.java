package com.jss.teacher.pojo;

public class TdetailPojo {
	private String type;
	private String qname;
	private int qno;
	private int qid;
	private float score;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	@Override
	public String toString() {
		return "TdetailPojo [type=" + type + ", qname=" + qname + ", qno="
				+ qno + ", qid=" + qid + ", score=" + score + ", count="
				+ count + "]";
	}
	
}
