package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.ClassPojo;
import com.jss.teacher.pojo.Condition_ClassPojo;
import com.jss.teacher.util.DBUtil;

public class ClassDao {
	/**
	 * 查询教师所有班级
	 * @param tno
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<ClassPojo> queryAllClass(String tno,int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql = "select * from class where Cno in (select DISTINCT(Cno) from Tclass where Tno=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<ClassPojo> list=new ArrayList<ClassPojo>();
			while(DBUtil.rs.next()){
				ClassPojo cpojo=new ClassPojo();
				cpojo.setCno(DBUtil.rs.getString("Cno"));
				cpojo.setCname(DBUtil.rs.getString("Cname"));
				cpojo.setCnum(DBUtil.rs.getInt("Cnum"));
				cpojo.setAcademy(DBUtil.rs.getString("Academy"));
				cpojo.setMajor(DBUtil.rs.getString("Major"));
				cpojo.setCtime_start(DBUtil.rs.getString("Ctime_start"));
				cpojo.setCtime_end(DBUtil.rs.getString("Ctime_end"));
				list.add(cpojo);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
	    DBUtil.closeConn();
		return null;
	}
	/**
	 * 按条件查询教师所教班级
	 * @param tno
	 * @param cc
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<ClassPojo> queryClass(String tno,Condition_ClassPojo cc,int pageNow,int pageSize) {
		DBUtil.openConn();
		String sql=null;
		try {
			sql = "select * from class where (Cno in (select DISTINCT(Cno) from Tclass where Tno=? )) and (Major=? or Cno=? or Ctime_start=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, cc.getMajor());
			DBUtil.pstat.setString(3, cc.getCno());
			DBUtil.pstat.setString(4, cc.getTime_start());
			DBUtil.pstat.setInt(5, pageNow);
			DBUtil.pstat.setInt(6, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<ClassPojo> list = new ArrayList<ClassPojo>();
			while (DBUtil.rs.next()) {
				ClassPojo cpojo = new ClassPojo();
				cpojo.setCno(DBUtil.rs.getString("Cno"));
				cpojo.setCname(DBUtil.rs.getString("Cname"));
				cpojo.setCnum(DBUtil.rs.getInt("Cnum"));
				cpojo.setAcademy(DBUtil.rs.getString("Academy"));
				cpojo.setMajor(DBUtil.rs.getString("Major"));
				cpojo.setCtime_start(DBUtil.rs.getString("Ctime_start"));
				cpojo.setCtime_end(DBUtil.rs.getString("Ctime_end"));
				list.add(cpojo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
    /**
     * 按关键字查询教师所教班级
     * @param tno
     * @param keyword
     * @param pageNow
     * @param pageSize
     * @return
     */
	public List<ClassPojo> queryClass(String tno,String keyword,int pageNow,int pageSize) {
		DBUtil.openConn();
		try {
			String sql = "select * from class where Cno LIKE ? or Cname like ?"
					+ "or Academy LIKE ? or Major LIKE ? or Ctime_start LIKE ?"
					+ "or Ctime_end LIKE ? and Cno in (select DISTINCT(Cno) from Tclass where Tno=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "%" + keyword + "%");
			DBUtil.pstat.setString(2, "%" + keyword + "%");
			DBUtil.pstat.setString(3, "%" + keyword + "%");
			DBUtil.pstat.setString(4, "%" + keyword + "%");
			DBUtil.pstat.setString(5, "%" + keyword + "%");
			DBUtil.pstat.setString(6, "%" + keyword + "%");
			DBUtil.pstat.setString(7, tno);
			DBUtil.pstat.setInt(8, pageNow);
			DBUtil.pstat.setInt(9, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<ClassPojo> list = new ArrayList<ClassPojo>();
			while (DBUtil.rs.next()) {
				ClassPojo cpojo = new ClassPojo();
				cpojo.setCno(DBUtil.rs.getString("Cno"));
				cpojo.setCname(DBUtil.rs.getString("Cname"));
				cpojo.setCnum(DBUtil.rs.getInt("Cnum"));
				cpojo.setAcademy(DBUtil.rs.getString("Academy"));
				cpojo.setMajor(DBUtil.rs.getString("Major"));
				cpojo.setCtime_start(DBUtil.rs.getString("Ctime_start"));
				cpojo.setCtime_end(DBUtil.rs.getString("Ctime_end"));
				list.add(cpojo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询教师所教班级数量
	 * @param tno
	 * @return
	 */
	public int queryAllCount(String tno){
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from class where Cno in (select DISTINCT(Cno) from Tclass where Tno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			int i=0;
			while (DBUtil.rs.next()) {
				i=DBUtil.rs.getInt("num");
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 按条件查询教师所教的班级数量
	 * @param cc
	 * @param tno
	 * @return
	 */
	public int queryCount(Condition_ClassPojo cc,String tno){
		DBUtil.openConn();
		String sql=null;
		try {
			sql = "select count(*) as num from class where (Major=? or Cno=? or Ctime_start=?) and Cno in (select DISTINCT(Cno) from Tclass where Tno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, cc.getMajor());
			DBUtil.pstat.setString(2, cc.getCno());
			DBUtil.pstat.setString(3, cc.getTime_start());
			DBUtil.pstat.setString(4, tno);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			int i=0;
			while (DBUtil.rs.next()) {
				i=DBUtil.rs.getInt("num");
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 按关键字查询教师所教班级数量
	 * @param keyword
	 * @param tno
	 * @return
	 */
	public int queryCount(String keyword,String tno){
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Class where Cno LIKE ? or Cname like ?"
					+ "or Academy LIKE ? or Major LIKE ? or Ctime_start LIKE ?"
					+ "or Ctime_end LIKE ? and Cno in (select DISTINCT(Cno) from Tclass where Tno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "%" + keyword + "%");
			DBUtil.pstat.setString(2, "%" + keyword + "%");
			DBUtil.pstat.setString(3, "%" + keyword + "%");
			DBUtil.pstat.setString(4, "%" + keyword + "%");
			DBUtil.pstat.setString(5, "%" + keyword + "%");
			DBUtil.pstat.setString(6, "%" + keyword + "%");
			DBUtil.pstat.setString(7, tno);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			int i=0;
			while (DBUtil.rs.next()) {
				i=DBUtil.rs.getInt("num");
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
}
