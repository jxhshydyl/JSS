package com.jss.teacher.pojo;

public class TeacherPojo {
	private String tno;
	private String password;
	private String tname;
	private String tsex;
	private String tphone;
	private String tdviser;
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
	public String getTdviser() {
		return tdviser;
	}
	public void setTdviser(String tdviser) {
		this.tdviser = tdviser;
	}
	@Override
	public String toString() {
		return "TeacherPojo [tno=" + tno + ", password=" + password
				+ ", tname=" + tname + ", tsex=" + tsex + ", tphone=" + tphone
				+ ", tdviser=" + tdviser + "]";
	}
	
}
