package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.StudentPojo;
import com.jss.teacher.util.DBUtil;

public class StudentDao {
	/**
	 * 查询班级的所有学生
	 */
	public List<StudentPojo> queryAllStudent(String cname,int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql="select * from Sinformation where Sclass=? limit ?,?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, cname);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<StudentPojo> list=new ArrayList<StudentPojo>();
			while(DBUtil.rs.next()){
				StudentPojo student=new StudentPojo();
				student.setSno(DBUtil.rs.getString("Sno"));
				student.setPassword(DBUtil.rs.getString("Password"));
				student.setSname(DBUtil.rs.getString("Sname"));
				student.setSsex(DBUtil.rs.getString("Ssex"));
				student.setSbirthday(DBUtil.rs.getString("Sbirthday"));
				student.setSage(DBUtil.rs.getString("Sage"));
				student.setSyear(DBUtil.rs.getString("Syear"));
				student.setSacademy(DBUtil.rs.getString("Sacademy"));
				student.setSmajor(DBUtil.rs.getString("Smajor"));
				student.setSclass(DBUtil.rs.getString("Sclass"));
				list.add(student);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 按条件查询班级里的学生
	 */
	public List<StudentPojo> queryStudent(String cname,String contidion, int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql="select * from Sinformation where Sclass=? and (Sname like ? or Sno like ?) limit ?,?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, cname);
			DBUtil.pstat.setString(2, "%"+contidion+"%");
			DBUtil.pstat.setString(3, "%"+contidion+"%");
			DBUtil.pstat.setInt(4, pageNow);
			DBUtil.pstat.setInt(5, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<StudentPojo> list=new ArrayList<StudentPojo>();
			while(DBUtil.rs.next()){
				StudentPojo student=new StudentPojo();
				student.setSno(DBUtil.rs.getString("Sno"));
				student.setPassword(DBUtil.rs.getString("Password"));
				student.setSname(DBUtil.rs.getString("Sname"));
				student.setSsex(DBUtil.rs.getString("Ssex"));
				student.setSbirthday(DBUtil.rs.getString("Sbirthday"));
				student.setSage(DBUtil.rs.getString("Sage"));
				student.setSyear(DBUtil.rs.getString("Syear"));
				student.setSacademy(DBUtil.rs.getString("Sacademy"));
				student.setSmajor(DBUtil.rs.getString("Smajor"));
				student.setSclass(DBUtil.rs.getString("Sclass"));
				list.add(student);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询班级所有人数
	 */
	public int queryAllStudentCount(String Cname){
		DBUtil.openConn();
		try{
			String sql="select count(*) as num from Sinformation where Sclass=?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cname);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int i = 0;
			if(DBUtil.rs.next()){
				i=Integer.parseInt(DBUtil.rs.getString("num"));
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 按条件查询班级所有人数
	 */
	public int queryStudentCount(String Cname,String condition){
		DBUtil.openConn();
		try{
			String sql="select count(*) as num from Sinformation where Sclass=? and Sno like ? or Sname like ?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cname);
			DBUtil.pstat.setString(2, "%"+condition+"%");
			DBUtil.pstat.setString(3, "%"+condition+"%");
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int num=0;
			while(DBUtil.rs.next()){
				num=DBUtil.rs.getInt("num");
			}
			System.out.println(num);
			return num;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 通过学生详情id查询学生提交的作业基本信息
	 */
	public StudentPojo queryStudentByStaskId(String id){
		DBUtil.openConn();
		try{
			String sql="select Stask.id,Stask.Grade, s.Sno,s.Sname,s.Sclass,t.Task_name,t.Tchapter,Course.Cname,Stask.Grade from Sinformation s, Task t,Course,Stask "
					+ "where s.Sno=Stask.Sno and t.Tid=Stask.Tid and Course.Cno=t.Cno and Stask.id=?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, id);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			StudentPojo pojo=new StudentPojo();
			while(DBUtil.rs.next()){
				pojo.setId(DBUtil.rs.getInt("id"));
				pojo.setTaskGrade(DBUtil.rs.getFloat("Grade"));
				pojo.setCourseName(DBUtil.rs.getString("Cname"));
				pojo.setSno(DBUtil.rs.getString("Sno"));
				pojo.setSname(DBUtil.rs.getString("Sname"));
				pojo.setSclass(DBUtil.rs.getString("Sclass"));
				pojo.setTaskName(DBUtil.rs.getString("Task_name"));
				pojo.setTaskChapter(DBUtil.rs.getString("Tchapter"));
				pojo.setTaskGrade(DBUtil.rs.getFloat("Grade"));
			}
			return pojo;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 通过学生作业id保存批阅作业的分数
	 * @param id
	 * @param totalScore
	 * @return
	 */
	public int saveStudentTaskScoreByStaskId(String id, String totalScore) {
		DBUtil.openConn();
		try{
			String sql="update Stask set Grade=? where id=?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setFloat(1, Float.valueOf(totalScore));
			DBUtil.pstat.setString(2, id);
			int num=DBUtil.pstat.executeUpdate();
			return num;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	
	
	public static void main(String[] args) {
		StudentDao dao=new StudentDao();
		System.out.println(dao.queryStudentByStaskId("1"));
	}
	
}
