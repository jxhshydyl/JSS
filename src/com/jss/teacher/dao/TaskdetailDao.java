package com.jss.teacher.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.CodePojo;
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
	public List<Object> queryTaskDetail(int tid) {
		DBUtil.openConn();
		try {
			String sql = "select * from Question where Qid in (select Qid from Tdetail where Tid=? and type=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, tid);
			DBUtil.pstat.setInt(2, 0);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<Object> list=new ArrayList<Object>();
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
			List<CodePojo> queryCode = queryCode(tid);
			list.addAll(queryCode);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	public List<CodePojo> queryCode(int tid) {
		String sql = "select * from code where Qid in (select Qid from Tdetail where Tid=? and type=?)";
		try {
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, tid);
			DBUtil.pstat.setInt(2, 1);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<CodePojo> list=new ArrayList<CodePojo>();
			while(DBUtil.rs.next()){
				CodePojo question=new CodePojo();
				question.setQid(BigInteger.valueOf(DBUtil.rs.getInt("Qid")));
				question.setQtype(DBUtil.rs.getString("Qtype"));
				question.setQchapter(DBUtil.rs.getString("Qchapter"));
				question.setQdegree(DBUtil.rs.getInt("Qdegree"));
				question.setQdescribe(DBUtil.rs.getString("Qdescribe"));
				question.setQname(DBUtil.rs.getString("Qname"));
				question.setQparagraph(DBUtil.rs.getString("Qparagraph"));
				question.setInputDescribe(DBUtil.rs.getString("Input_describe"));
				question.setExampleInput(DBUtil.rs.getString("Example_input"));
				question.setLimitTime(DBUtil.rs.getFloat("Limit_time"));
				question.setOutputDescripe(DBUtil.rs.getString("Output_descripe"));
				question.setExampleOutput(DBUtil.rs.getString("Example_output"));
				question.setTotalRightCount(DBUtil.rs.getInt("total_right_count"));
				question.setTotalSubmitCount(DBUtil.rs.getInt("total_submit_count"));
				question.setLimitMemory((float)DBUtil.rs.getInt("Limit_memory"));
				question.setCname(DBUtil.rs.getString("Cname"));
				question.setReferenceAnswer(DBUtil.rs.getString("reference_answer"));
				list.add(question);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
