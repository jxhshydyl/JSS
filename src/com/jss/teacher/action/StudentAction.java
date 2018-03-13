package com.jss.teacher.action;

import java.util.List;
import java.util.Map;
import com.jss.teacher.pojo.StudentPojo;
import com.jss.teacher.service.StudentService;
import com.jss.teacher.util.Pager;
import com.jss.teacher.util.SaveCondition;
import com.opensymphony.xwork2.ActionContext;

public class StudentAction {
	private String className;
	private String condition;
	private int pageNow=1;
	private int classCnum;
	
	public int getClassCnum() {
		return classCnum;
	}

	public void setClassCnum(int classCnum) {
		this.classCnum = classCnum;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		if(pageNow==0){
			pageNow=1;
		}
		this.pageNow = pageNow;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	private StudentService ss;
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		SaveCondition.Sclass=className;
		this.className = className;
	}
	/**
	 * 查询班级所有学生需要一个条件：班级名称
	 * 按条件查询班级所有学生需要两个条件：班级名称（className），条件（condition）（学号或姓名）
	 * 进行分页的两个条件：当前页码（pageNow），总记录数（classCnum）
	 */
	//查询所有学生
	public String queryStudent(){
		className=SaveCondition.Sclass;//保存条件班级名称
		ss=new StudentService();
		List<StudentPojo> list=null;
		Pager page =null;
		classCnum=ss.queryStudentsCount(className,condition);
		page=new Pager(pageNow,classCnum);
		if(condition!=null && !"".equals(condition.trim())){
			list=ss.queryStudents(className,condition,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}else{
			list=ss.queryAllStudents(className,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}
		Map<String, Object> session=ActionContext.getContext().getSession();
		session.put("student", list);
		session.put("page", page);
		return "student";
	}
}
