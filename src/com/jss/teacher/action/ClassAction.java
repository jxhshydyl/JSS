package com.jss.teacher.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jss.teacher.pojo.ClassPojo;
import com.jss.teacher.pojo.Condition_ClassPojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.ClassService;
import com.jss.teacher.service.MajorService;
import com.jss.teacher.util.Pager;
import com.jss.teacher.util.SaveCondition;
import com.opensymphony.xwork2.ActionContext;

public class ClassAction {
	private ClassService cs;
	private Condition_ClassPojo cc;
	private int pageNow=1;
	private String isFirst;
	
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
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
	public Condition_ClassPojo getCc() {
		return cc;
	}
	public void setCc(Condition_ClassPojo cc) {
		this.cc = cc;
	}
	/*
	 * 查询所有班级信息
	 */
	public String queryAllClass(){
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		List<ClassPojo> list=null;
		Pager page;
		if("0".equals(isFirst)){
			session.put("condition", null);
		}
		if(session.get("condition") != null){
			cs =new ClassService();
			cc=SaveCondition.cc;
			MajorService.queryMajor(cc);//把专业编号转成专业名
		    page=new Pager(pageNow,cs.queryCount(cc,tno));
			list=new ArrayList<ClassPojo>();
			list=cs.queryClass(cc,(pageNow-1)*page.getPageSize(),page.getPageSize(),tno);//按条件查询班级
			session.put("class", list);
			cc.setMajor(SaveCondition.major_no);
			session.put("condition", cc);
		}else{
			cs =new ClassService();
		    page=new Pager(pageNow,cs.queryAllCount(tno));
			list=new ArrayList<ClassPojo>();
			list=cs.queryAllClass((pageNow-1)*page.getPageSize(),page.getPageSize(),tno);
		}
		session.put("class", list);
		session.put("page", page);
		return "class";
	}
	/*
	 * 按条件查询班级信息
	 */
	public String queryClass(){
	    cs =new ClassService();
	    SaveCondition.major_no=cc.getMajor();//得到专业编号
	    MajorService.queryMajor(cc);//把专业编号转成专业名
	    SaveCondition.cc=cc;
	    Map<String, Object> session=ActionContext.getContext().getSession();
	    String tno=((TeacherPojo)session.get("teacher")).getTno();
	    Pager page=new Pager(pageNow,cs.queryCount(cc,tno));
		List<ClassPojo> list=new ArrayList<ClassPojo>();
		list=cs.queryClass(cc,(pageNow-1)*page.getPageSize(),page.getPageSize(),tno);//按条件查询班级
		session.put("class", list);
		cc.setMajor(SaveCondition.major_no);
		session.put("condition", cc);
		session.put("page", page);
		return "condition_class";
	}
}
