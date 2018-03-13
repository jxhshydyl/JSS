package com.jss.teacher.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.jss.teacher.dao.ClassTaskDetailDao;
import com.jss.teacher.pojo.ClassTaskDetailPojo;
import com.jss.teacher.pojo.QuestionPojo;

public class ClassTaskDetailService {

	public List<ClassTaskDetailPojo> queryClassTaskDetail(String tid, String cno) {
		ClassTaskDetailDao dao=new ClassTaskDetailDao();
		List<ClassTaskDetailPojo> list=dao.queryClassTaskDetail(tid,cno);
		return list;
		
	}

	public List<QuestionPojo> querySubmitTaskDetail(String sid) {
		ClassTaskDetailDao dao=new ClassTaskDetailDao();
		List<QuestionPojo> list=dao.queryClassTaskDetail(sid);
		return list;
	}
	/**
	 * 通过学生提交作业id和问题id保存已批阅问题分数
	 * @param id
	 * @param arrayQids 问题id集
	 * @param arrayScores 分数集
	 * @return
	 */
	public int saveSubmitTaskDetailScore(String id,JSONArray arrayQids,JSONArray arrayScores) {
		ClassTaskDetailDao dao=new ClassTaskDetailDao();
		int key=0;
		for(int i=0;i<arrayQids.size();i++){
			int num=dao.saveSubmitTaskDetailScore(id,arrayQids.getString(i),arrayScores.getString(i));
			key=key+num;
		}
		if(key==arrayQids.size()){
			return 1;
		}
		return 0;
	}
	
}
