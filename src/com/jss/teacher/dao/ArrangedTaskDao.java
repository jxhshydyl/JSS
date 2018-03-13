package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.ArrangedTaskPojo;
import com.jss.teacher.util.DBUtil;

/**
 * 查询已经安排的作业及学生已经提交的作业
 * @author Administrator
 *
 */
public class ArrangedTaskDao {
	/**
	 * 查询已经安排的作业信息
	 * @param tno
	 * @return
	 */
	public List<ArrangedTaskPojo> queryArrangeTask(String tno,int pageNow,int pageSize){
		DBUtil.openConn();
		try {
			String sql = "select Task.Task_name,Task.Tid,Task.Tchapter,Ctask.End_time,Class.Cname,Class.Cno,Course.Cname as course_name from Task,Ctask,Class,Course "
					+ "where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno ORDER BY Ctask.End_time DESC limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<ArrangedTaskPojo> list=new ArrayList<ArrangedTaskPojo>();
			while(DBUtil.rs.next()){
				ArrangedTaskPojo arrangedTask=new ArrangedTaskPojo();
				arrangedTask.setClass_name(DBUtil.rs.getString("Class.Cname"));
				arrangedTask.setCno(DBUtil.rs.getString("Class.Cno"));
				arrangedTask.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				arrangedTask.setTid(DBUtil.rs.getInt("Task.Tid"));
				arrangedTask.setCourse_name(DBUtil.rs.getString("course_name"));
				arrangedTask.setChapter(DBUtil.rs.getString("Task.Tchapter"));
				arrangedTask.setEnd_time(DBUtil.rs.getString("Ctask.End_time"));
				list.add(arrangedTask);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
/*	public static void main(String[] args) {
		System.out.println(new ArrangedTaskDao().queryArrangeTask("1"));
	}*/
	/**
	 * 根据教师编号查询该教师发布的所有作业
	 * @param tno
	 * @return
	 */
	public int queryAllTaskCount(String tno) {
		DBUtil.openConn();
		try {
			String sql = "select COUNT(*) as num from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int num=0;
			if(DBUtil.rs.next()){
				num=DBUtil.rs.getInt("num");
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 按关键字查询教师发布作业的数量
	 * @param tno
	 * @param key
	 * @return
	 */
	public int queryTaskCount(String tno, String key) {
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Task.Task_name like ? or Task.Tchapter like ?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, "%"+key+"%");
			DBUtil.pstat.setString(3, "%"+key+"%");
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int num=0;
			if(DBUtil.rs.next()){
				num=DBUtil.rs.getInt("num");
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	public List<ArrangedTaskPojo> queryArrangeTask(String tno,String key,int pageNow,int pageSize){
		DBUtil.openConn();
		try {
			String sql = "select Task.Task_name,Task.Tid,Task.Tchapter,Ctask.End_time,Class.Cname,Class.Cno,Course.Cname as course_name from Task,Ctask,Class,Course "
					+ "where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Task.Task_name like ? or Task.Tchapter like ?) ORDER BY Ctask.End_time DESC limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, "%"+key+"%");
			DBUtil.pstat.setString(3, "%"+key+"%");
			DBUtil.pstat.setInt(4, pageNow);
			DBUtil.pstat.setInt(5, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<ArrangedTaskPojo> list=new ArrayList<ArrangedTaskPojo>();
			while(DBUtil.rs.next()){
				ArrangedTaskPojo arrangedTask=new ArrangedTaskPojo();
				arrangedTask.setClass_name(DBUtil.rs.getString("Class.Cname"));
				arrangedTask.setCno(DBUtil.rs.getString("Class.Cno"));
				arrangedTask.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				arrangedTask.setTid(DBUtil.rs.getInt("Task.Tid"));
				arrangedTask.setCourse_name(DBUtil.rs.getString("course_name"));
				arrangedTask.setChapter(DBUtil.rs.getString("Task.Tchapter"));
				arrangedTask.setEnd_time(DBUtil.rs.getString("Ctask.End_time"));
				list.add(arrangedTask);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 按班级或课程查询教师已经发布的作业的数量
	 * @param tno
	 * @param cno
	 * @param course
	 * @return
	 */
	public int queryTaskCount(String tno, String cno, String course) {
		DBUtil.openConn();
		try {
			if("class".equals(cno) &&  !"course".equals(course)){
				String sql = "select count(*) as num from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Course.Cno=?)";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, course);
			}else if(!"class".equals(cno) && "course".equals(course)){
				String sql = "select count(*) as num from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Class.Cno=?)";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, cno);
			}else if(!"class".equals(cno) && !"course".equals(course)){
				String sql = "select count(*) as num from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Class.Cno=? and Course.Cno=?)";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, cno);
				DBUtil.pstat.setString(3, course);
			}
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int num=0;
			if(DBUtil.rs.next()){
				num=DBUtil.rs.getInt("num");
			}
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	public List<ArrangedTaskPojo> queryArrangeTask(String tno, String course,String cno, int pageNow, int pageSize) {
		DBUtil.openConn();
		try {
			if("class".equals(cno) &&  !"course".equals(course)){
				String sql = "select Task.Task_name,Task.Tid,Task.Tchapter,Ctask.End_time,Class.Cname,Class.Cno,Course.Cname as course_name from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Course.Cno=?) ORDER BY Ctask.End_time DESC limit ?,?";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, course);
				DBUtil.pstat.setInt(3, pageNow);
				DBUtil.pstat.setInt(4, pageSize);
			}else if(!"class".equals(cno) && "course".equals(course)){
				String sql = "select Task.Task_name,Task.Tid,Task.Tchapter,Ctask.End_time,Class.Cname,Class.Cno,Course.Cname as course_name from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Class.Cno=?) ORDER BY Ctask.End_time DESC limit ?,?";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, cno);
				DBUtil.pstat.setInt(3, pageNow);
				DBUtil.pstat.setInt(4, pageSize);
			}else if(!"class".equals(cno) && !"course".equals(course)){
				String sql = "select Task.Task_name,Task.Tid,Task.Tchapter,Ctask.End_time,Class.Cname,Class.Cno,Course.Cname as course_name from Task,Ctask,Class,Course where Task.Tno=? and Task.Tid=Ctask.Tid and Class.Cno=Ctask.Cno and Task.Cno=Course.Cno and (Class.Cno=? and Course.Cno=?) ORDER BY Ctask.End_time DESC limit ?,?";
				DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
				DBUtil.pstat.setString(1, tno);
				DBUtil.pstat.setString(2, cno);
				DBUtil.pstat.setString(3, course);
				DBUtil.pstat.setInt(4, pageNow);
				DBUtil.pstat.setInt(5, pageSize);
			}
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<ArrangedTaskPojo> list=new ArrayList<ArrangedTaskPojo>();
			while(DBUtil.rs.next()){
				ArrangedTaskPojo arrangedTask=new ArrangedTaskPojo();
				arrangedTask.setClass_name(DBUtil.rs.getString("Class.Cname"));
				arrangedTask.setCno(DBUtil.rs.getString("Class.Cno"));
				arrangedTask.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				arrangedTask.setTid(DBUtil.rs.getInt("Task.Tid"));
				arrangedTask.setCourse_name(DBUtil.rs.getString("course_name"));
				arrangedTask.setChapter(DBUtil.rs.getString("Task.Tchapter"));
				arrangedTask.setEnd_time(DBUtil.rs.getString("Ctask.End_time"));
				list.add(arrangedTask);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	public void deleteArrangedTask(String tid, String cno) {
		DBUtil.openConn();
		try {
			String sql = "delete from Ctask where Ctask.Cno=? and Tid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, cno);
			DBUtil.pstat.setString(2, tid);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
}
