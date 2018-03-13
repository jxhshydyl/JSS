package com.jss.teacher.service;

import java.util.ArrayList;

import com.jss.teacher.dao.IssueTaskDao;

public class IssueTaskService {
	/**
	 * 给班级发布作业
	 * @param tid
	 * @param end_time
	 * @param cno
	 */
	public void issueTask(String tid, String end_time, String cno) {
		String[] cnos=cno.split(",");
		String[] end_times=end_time.split(",");
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> list1=new ArrayList<String>();
		for(int i=0;i<end_times.length;i++){
			if(!end_times[i].trim().equals("")){
				list.add(end_times[i].trim());
				list1.add(cnos[i].trim());
			}
		}
		IssueTaskDao dao=new IssueTaskDao();
		for(int i=0;i<list.size();i++){
			dao.issueTask(tid,list1.get(i),list.get(i));
		}
	}
}
