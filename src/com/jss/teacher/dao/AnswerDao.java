package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;
import com.jss.teacher.pojo.AnswerPojo;
import com.jss.teacher.util.DBUtil;

public class AnswerDao {
	/**
	 * 增加回复信息
	 * @param ansObj
	 * @return
	 */
	public int addAnswer(AnswerPojo ansObj) {
		DBUtil.openConn();
		try {
			String sql = "insert into Answer(Acontent,Atime,Tno,Tname,Tid,Qid,Ask_no) VALUES (?,?,?,?,?,?,?);";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, ansObj.getAcontent());
			DBUtil.pstat.setString(2, ansObj.getAtime());
			DBUtil.pstat.setString(3, ansObj.getTno());
			DBUtil.pstat.setString(4, ansObj.getTname());
			DBUtil.pstat.setInt(5, ansObj.getTid());
			DBUtil.pstat.setInt(6, ansObj.getQid());
			DBUtil.pstat.setString(7, ansObj.getAsk_no());
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 查看信息
	 * @param ano
	 * @return
	 */
	public List<AnswerPojo> queryAnswer(String ano) {
		DBUtil.openConn();
		try {
			String sql = "select * from Answer where Ask_no=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, ano);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<AnswerPojo> list=new ArrayList<AnswerPojo>();
			while(DBUtil.rs.next()){
				AnswerPojo answerPojo=new AnswerPojo();
				answerPojo.setAno(DBUtil.rs.getString("Ano"));
				answerPojo.setAcontent(DBUtil.rs.getString("Acontent"));
				answerPojo.setAtime(DBUtil.rs.getString("Atime"));
				answerPojo.setTno(DBUtil.rs.getString("Tno"));
				answerPojo.setTname(DBUtil.rs.getString("Tname"));
				answerPojo.setTid(DBUtil.rs.getInt("Tid"));
				answerPojo.setQid(DBUtil.rs.getInt("Qid"));
				answerPojo.setAsk_no(DBUtil.rs.getString("Ask_no"));
				list.add(answerPojo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
}
