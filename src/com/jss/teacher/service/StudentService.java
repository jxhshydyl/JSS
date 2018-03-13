package com.jss.teacher.service;

import java.util.List;

import com.jss.teacher.dao.StudentDao;
import com.jss.teacher.pojo.StudentPojo;

public class StudentService {
	/*
	 * 查询所有的学生信息
	 */
	public List<StudentPojo> queryAllStudents(String cname,int pageNow,int pageSize){
		StudentDao dao=new StudentDao();
		List<StudentPojo> list = dao.queryAllStudent(cname,pageNow,pageSize);
		return list;
	}
	/*
	 * 根据条件查询所有的学生信息
	 */
	public List<StudentPojo> queryStudents(String cname,String condition,int pageNow,int pageSize){
		StudentDao dao=new StudentDao();
		List<StudentPojo> list = dao.queryStudent(cname,condition,pageNow,pageSize);
		return list;
	}
	public int queryStudentsCount(String cname,String condition){
		StudentDao dao=new StudentDao();
		int num=0;
		if(condition==null||"".equals(condition)){
			num=dao.queryAllStudentCount(cname);
		}else{
			num=dao.queryStudentCount(cname,condition);
		}
		return num;
	}
	/**
	 * 通过学生提交的作业id查询学生基本作业信息
	 * @param id
	 * @return
	 */
	public StudentPojo queryStudentByStaskId(String id){
		StudentDao dao=new StudentDao();
		return dao.queryStudentByStaskId(id);
	}
	/**
	 * 通过学生作业id保存批阅作业的分数
	 * @param id
	 * @param totalScore
	 */
	public int saveStudentTaskScoreByStaskId(String id, String totalScore) {
		StudentDao dao=new StudentDao();
		int num = dao.saveStudentTaskScoreByStaskId(id,totalScore);
		return num;
	}
}
