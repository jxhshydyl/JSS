package com.jss.teacher.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.ArrangedTaskPojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.ArrangedTaskService;
import com.jss.teacher.service.QuestionService;
import com.jss.teacher.util.Pager;

public class ArrangedTaskAction {
	private ArrangedTaskService ats;
	private String cno;//班级编号
	private String course;//课程编号
	private String key;//关键字
	private int pageNow=1;//
	private String isFirst;//是否是第一次查看已安排的作业
	private String[] Tid;//需要删除的作业的编号
	private String[] no;//需要删除的班级的编号
	
	public String[] getTid() {
		return Tid;
	}
	public void setTid(String[] tid) {
		Tid = tid;
	}
	public String[] getNo() {
		return no;
	}
	public void setNo(String[] no) {
		this.no = no;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
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
	/**
	 * 查询所有已经发布的任务
	 * @return
	 */
	public String queryArrangedTask(){
		ats=new ArrangedTaskService();
		Map<String, Object> session=ServletActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		if("0".equals(isFirst)){//第一次查询
			session.put("cno", null);
			session.put("key", null);
			session.put("course", null);
		}else{//得到条件
			cno=(String)session.get("cno");
			key=(String)session.get("key");
			course=(String)session.get("course");
		}
		List<ArrangedTaskPojo> list = null;
		Pager page = null;
		//所有条件为空
		if((cno==null && key==null && course==null)||("class".equals(cno.trim()) && "".equals(key.trim())&& "course".equals(course.trim()))){
			page=new Pager(pageNow,ats.queryAllTaskCount(tno));
	        list = ats.queryArrangedTask(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		//关键字不为空，其他条件为空
		}else if((cno==null && key!=null && course==null)||("class".equals(cno.trim()) && !"".equals(key.trim())&& "course".equals(course.trim()))){
			page=new Pager(pageNow,ats.queryTaskCount(tno,key));
			list = ats.queryArrangedTask(tno,key,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}else{//关键字为空，班级和课程有一个不为空
			page=new Pager(pageNow,ats.queryTaskCount(tno,cno,course));
			list = ats.queryArrangedTask(tno,course,cno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}
		request.setAttribute("arrangedTask",list);
		request.setAttribute("page", page);
		return "queryarrangedtask";
	}
	/**
	 * 按条件查询已经发布的任务
	 * @return
	 */
	public String queryArrangedTaskByCondition(){
		ats=new ArrangedTaskService();
		Map<String, Object> session=ServletActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		List<ArrangedTaskPojo> list = null;
		Pager page = null;
		if((cno==null && key==null && course==null)||("class".equals(cno.trim()) && "".equals(key.trim())&& "course".equals(course.trim()))){
			page=new Pager(pageNow,ats.queryAllTaskCount(tno));
			list = ats.queryArrangedTask(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}else if((cno==null && key!=null && course==null)||("class".equals(cno.trim()) && !"".equals(key.trim())&& "course".equals(course.trim()))){
			page=new Pager(pageNow,ats.queryTaskCount(tno,key));
			list = ats.queryArrangedTask(tno,key,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}else{
			page=new Pager(pageNow,ats.queryTaskCount(tno,cno,course));
			list = ats.queryArrangedTask(tno,course,cno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}
		//保存条件
		session.put("cno",cno);
		session.put("key",key);
		session.put("course",course);
		request.setAttribute("arrangedTask",list);
		request.setAttribute("page", page);
		return "queryarrangedtaskbycondition";
	}
	/**
	 * 删除已安排作业
	 * @return
	 */
	public String deleteArrangedTask(){
		System.out.println(Tid[0]);
		ats=new ArrangedTaskService();
		Tid=Tid[0].split(",");
		String[] tid=new String[Tid.length/2];
		String[] cno=new String[Tid.length/2];
		for(int i=0;i<Tid.length/2;i++){
			tid[i]=Tid[i*2];
			cno[i]=Tid[i*2+1];
		}
		ats.deleteArrangedTask(tid,cno);
		return "deletearrangedtask";
	}
}
