package com.jss.teacher.dao;

import com.jss.teacher.util.DBUtil;

public class IssueTaskDao {
	/**
	 * 给班级发布作业
	 * @param tid 作业编号
	 * @param cno 班级编号
	 * @param time 截止时间
	 */
	public void issueTask(String tid, String cno, String time) {
		DBUtil.openConn();
		try {
			String sql = "insert into Ctask(Tid,Cno,End_time) VALUES (?,?,?);";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tid);
			DBUtil.pstat.setString(2, cno);
			DBUtil.pstat.setString(3, time);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
}
