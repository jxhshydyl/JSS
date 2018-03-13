package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TdetailPojo;
import com.jss.teacher.util.DBUtil;

public class TaskdetailDao {
	/**
	 * 增加试卷
	 * @param question
	 * @param score
	 * @param Tid
	 */
	public void addPaper(QuestionPojo question, float score,int Tid) {
		DBUtil.openConn();
		try {
			String sql = "insert into Tdetail(Tid,Qid,Score) VALUES (?,?,?);";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, Tid);
			DBUtil.pstat.setInt(2, question.getQid());
			DBUtil.pstat.setFloat(3, score);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
	/**
	 * 查询试卷
	 * @param question
	 * @param score
	 * @param Tid
	 */
	public List<QuestionPojo> queryTaskDetail(int tid) {
		DBUtil.openConn();
		try {
			String sql = "select * from Question where Qid in (select Qid from Tdetail where Tid=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, tid);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<QuestionPojo> list=new ArrayList<QuestionPojo>();
			while(DBUtil.rs.next()){
				QuestionPojo question=new QuestionPojo();
				question.setQid(DBUtil.rs.getInt("Qid"));
				question.setQtype(DBUtil.rs.getString("Qtype"));
				question.setQcontent(DBUtil.rs.getString("Qcontent"));
				//把选项通过A,B,C,D分隔开
				question.setChoice(DBUtil.rs.getString("Qchoice").trim().split("[A-Z][|:|：|、|.]"));
				question.setQanswer(DBUtil.rs.getString("Qanswer"));
				question.setQdegree(DBUtil.rs.getInt("Qdegree"));
				question.setQchapter(DBUtil.rs.getString("Qchapter"));
				question.setCname(DBUtil.rs.getString("Cname"));
				list.add(question);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 根据作业编号查询该作业的作业类型，数量和每种类型的总分数
	 * @param tid
	 * @return
	 */
	public List<TdetailPojo> queryQuestionTypeAndScore(int tid) {
		DBUtil.openConn();
		try {
			String sql = "select DISTINCT(Qtype),count(*) as count ,sum(Score) as score from Question,Tdetail where  Tid=? and Tdetail.Qid=Question.Qid GROUP BY Qtype";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, tid);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<TdetailPojo> list=new ArrayList<TdetailPojo>();
			while(DBUtil.rs.next()){
				TdetailPojo question=new TdetailPojo();
				question.setType(DBUtil.rs.getString("Qtype"));
				question.setCount(DBUtil.rs.getInt("count"));
				question.setScore(DBUtil.rs.getFloat("score"));
				list.add(question);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	public void deleteTaskQuestion(String qid, String tid) {
		DBUtil.openConn();
		try {
			String sql = "delete from Tdetail where Tid=? and Qid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tid);
			DBUtil.pstat.setString(2, qid);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
	public List<QuestionPojo> querySilimarQuestion(String qtype, String qchapter,String tid) {
		DBUtil.openConn();
		try {
			String sql = "SELECT * FROM Question where Qchapter=? and Qtype=? and Qid not in (select Qid from Tdetail where Tid=?)  ORDER BY  RAND() LIMIT ?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, qchapter);
			DBUtil.pstat.setString(2, qtype);
			DBUtil.pstat.setString(3, tid);
			DBUtil.pstat.setInt(4, 5);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<QuestionPojo> list=new ArrayList<QuestionPojo>();
			while(DBUtil.rs.next()){
				QuestionPojo question=new QuestionPojo();
				question.setQid(DBUtil.rs.getInt("Qid"));
				question.setQcontent(DBUtil.rs.getString("Qcontent"));
				question.setQchoice(DBUtil.rs.getString("Qchoice"));
				question.setQanswer(DBUtil.rs.getString("Qanswer"));
				question.setQtype(DBUtil.rs.getString("Qtype"));
				question.setQdegree(DBUtil.rs.getInt("Qdegree"));
				question.setCname(DBUtil.rs.getString("Cname"));
				question.setQchapter(DBUtil.rs.getString("Qchapter"));
				question.setChoice(DBUtil.rs.getString("Qchoice").trim().split("[A-Z][|:|：|、|.| .| 、]"));
				list.add(question);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	public int replaceSilimarQuestion(String newQid, String qid, String tid) {
		DBUtil.openConn();
		try {
			String sql = "update Tdetail set Qid=? where Qid=? and Tid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, newQid);
			DBUtil.pstat.setString(2, qid);
			DBUtil.pstat.setString(3, tid);
			int num = DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
}
