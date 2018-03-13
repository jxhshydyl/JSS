package com.jss.teacher.dao;

import com.jss.teacher.pojo.Condition_ClassPojo;
import com.jss.teacher.util.DBUtil;

public class MajorDao {
	/**
	 * 根据专业编号查询专业名称
	 * @param cc
	 */
	public void queryMajor(Condition_ClassPojo cc){
		DBUtil.openConn();
		try{
			String sql = "select * from Major where Mno=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, cc.getMajor());
			DBUtil.rs = DBUtil.pstat.executeQuery();
			while(DBUtil.rs.next()){
				cc.setMajor(DBUtil.rs.getString("Mname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	    DBUtil.closeConn();
	}
}
