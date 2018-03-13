package com.jss.teacher.dao;


import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.CoursePojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.util.DBUtil;

public class TeacherDao {
/**
 * 登录
 * @param teacher
 * @return
 */
	public int login(TeacherPojo teacher) {
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Teacher where Tno=? and Password=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, teacher.getTno());
			DBUtil.pstat.setString(2, teacher.getPassword());
			DBUtil.rs= DBUtil.pstat.executeQuery();
			int count=0;
			if(DBUtil.rs.next()){
				count=DBUtil.rs.getInt("num");
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 查询教师教的所有课程
	 * @param teacher
	 * @return
	 */
	public List<CoursePojo> queryTeacherCourse(TeacherPojo teacher) {
		DBUtil.openConn();
		try {
			String sql = "select DISTINCT(Course_name),Cid from Tclass where Tno=? ";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, teacher.getTno());
			DBUtil.rs= DBUtil.pstat.executeQuery();
			List<CoursePojo> list=new ArrayList<CoursePojo>();
			while(DBUtil.rs.next()){
				CoursePojo course=new CoursePojo();
				course.setCid(DBUtil.rs.getString("Cid"));
				course.setCourse_name(DBUtil.rs.getString("Course_name"));
				list.add(course);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询教师教的所有班级
	 * @param teacher
	 * @return
	 */
	public List<CoursePojo> queryTeacherClass(TeacherPojo teacher) {
		DBUtil.openConn();
		try {
			String sql = "select DISTINCT(Cname),Cno from Tclass where Tno=? ";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, teacher.getTno());
			DBUtil.rs= DBUtil.pstat.executeQuery();
			List<CoursePojo> list=new ArrayList<CoursePojo>();
			while(DBUtil.rs.next()){
				CoursePojo course=new CoursePojo();
				course.setCno(DBUtil.rs.getString("Cno"));
				course.setCname(DBUtil.rs.getString("Cname"));
				list.add(course);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询教师所教的所有专业
	 * @param teacher
	 * @return
	 */
	public List<CoursePojo> queryTeacherMajor(TeacherPojo teacher) {
		DBUtil.openConn();
		try {
			String sql = "select DISTINCT(Major) ,Mno from Class,Major where Cno in(select DISTINCT(Cno) from Tclass where Tno=?) and Class.Major=Major.Mname";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, teacher.getTno());
			DBUtil.rs= DBUtil.pstat.executeQuery();
			List<CoursePojo> list=new ArrayList<CoursePojo>();
			while(DBUtil.rs.next()){
				CoursePojo course=new CoursePojo();
				course.setMno(DBUtil.rs.getString("Mno"));
				course.setMajor(DBUtil.rs.getString("Major"));
				list.add(course);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
}
