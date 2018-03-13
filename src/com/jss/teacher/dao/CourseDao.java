package com.jss.teacher.dao;

import com.jss.teacher.util.DBUtil;

public class CourseDao {
	/**
	 * 根据课程编号查询课程名称
	 * @param Cno
	 * @return
	 */
	public String queryCourse(String Cno){
		DBUtil.openConn();
		try{
			String sql = "select * from Course where Cno=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cno);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			String Cname=null;
			while(DBUtil.rs.next()){
				Cname=DBUtil.rs.getString("Cname");
			}
			return Cname;
		}catch(Exception e){
			e.printStackTrace();
		}
	    DBUtil.closeConn();
		return null;
	}
}
