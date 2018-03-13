package com.jss.teacher.service;

import java.util.List;

import com.jss.teacher.dao.TeacherDao;
import com.jss.teacher.pojo.CoursePojo;
import com.jss.teacher.pojo.TeacherPojo;

public class TeacherService {
	/**
	 * 登录
	 * @param teacher
	 * @return
	 */
	public boolean login(TeacherPojo teacher){
		TeacherDao dao=new TeacherDao();
		int count=dao.login(teacher);
		if(count==1){
			return true;
		}
		return false;
	}
	/**
	 * 查询教师所教的课程
	 * @param teacher
	 * @return
	 */
	public List<CoursePojo> queryTeacherCourse(TeacherPojo teacher) {
		TeacherDao dao=new TeacherDao();
		List<CoursePojo> list=dao.queryTeacherCourse(teacher);
		return list;
	}
	/**
	 * 查询教师所教的班级
	 * @param teacher
	 * @return
	 */
	public List<CoursePojo> queryTeacherClass(TeacherPojo teacher) {
		TeacherDao dao=new TeacherDao();
		List<CoursePojo> list=dao.queryTeacherClass(teacher);
		return list;
	}
	public List<CoursePojo> queryTeacherMajor(TeacherPojo teacher) {
		TeacherDao dao=new TeacherDao();
		List<CoursePojo> list=dao.queryTeacherMajor(teacher);
		return list;
	}
}
