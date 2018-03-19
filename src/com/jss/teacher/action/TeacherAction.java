package com.jss.teacher.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.CoursePojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.TeacherService;

public class TeacherAction {
	private TeacherService ts;
	private String logname;//登录名既教师编号
	private String logpass;//登录密码
	private String hidden;//ajax返回的值（true为登录成功，false为登录失败）
	
	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getLogpass() {
		return logpass;
	}

	public void setLogpass(String logpass) {
		this.logpass = logpass;
	}
	public String login(){
		ts=new TeacherService();
		TeacherPojo teacher=new TeacherPojo();
		teacher.setPassword(logpass);
		teacher.setTno(logname);
		boolean isTrue = ts.login(teacher);
		if(isTrue){
			hidden="true";
			ts.queryTeacherCourse(teacher);
			List<CoursePojo> list = ts.queryTeacherClass(teacher);
			List<CoursePojo> list1 = ts.queryTeacherCourse(teacher);
			List<CoursePojo> list2 = ts.queryTeacherMajor(teacher);
			Map<String, Object> session=ServletActionContext.getContext().getSession();
			session.put("totalclass",list);
			session.put("totalcourse",list1);
			session.put("totalmajor",list2);
			session.put("teacher",teacher);
		}else{
			hidden="false";
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("hidden", hidden);
		JSONObject json =JSONObject.fromObject(map);
		hidden = json.toString();
		return "login";
	}
}
