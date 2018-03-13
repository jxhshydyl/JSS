package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.util.DBUtil;

public class QuestionDao {
	/**
	 * 查询所有问题
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<QuestionPojo> queryAllQuestion(String tno,int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql = "select * from Question where Cname in (select DISTINCT(Course_name) from Tclass where Tno=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<QuestionPojo> list=new ArrayList<QuestionPojo>();
			while(DBUtil.rs.next()){
				QuestionPojo qpojo=new QuestionPojo();
				qpojo.setQid(DBUtil.rs.getInt("Qid"));
				qpojo.setQtype(DBUtil.rs.getString("Qtype"));
				qpojo.setQcontent(DBUtil.rs.getString("Qcontent"));
				qpojo.setQchoice(DBUtil.rs.getString("Qchoice"));
				qpojo.setQanswer(DBUtil.rs.getString("Qanswer"));
				qpojo.setQdegree(DBUtil.rs.getInt("Qdegree"));
				qpojo.setQchapter(DBUtil.rs.getString("Qchapter"));
				qpojo.setCname(DBUtil.rs.getString("Cname"));
				qpojo.setQparagraph(DBUtil.rs.getString("Qparagraph"));
				list.add(qpojo);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
	    DBUtil.closeConn();
		return null;
	}
	/**
	 * 按课程条件查询问题
	 * @param Cno
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<QuestionPojo> queryQuestions(String Cno,int pageNow,int pageSize) {
		DBUtil.openConn();
		try {
			String sql = "select * FROM Question WHERE Cname=(select Cname from Course where Cno=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<QuestionPojo> list = new ArrayList<QuestionPojo>();
			while (DBUtil.rs.next()) {
				QuestionPojo qpojo=new QuestionPojo();
				qpojo.setQid(DBUtil.rs.getInt("Qid"));
				qpojo.setQtype(DBUtil.rs.getString("Qtype"));
				qpojo.setQcontent(DBUtil.rs.getString("Qcontent"));
				qpojo.setQchoice(DBUtil.rs.getString("Qchoice"));
				qpojo.setQanswer(DBUtil.rs.getString("Qanswer"));
				qpojo.setQdegree(DBUtil.rs.getInt("Qdegree"));
				qpojo.setQchapter(DBUtil.rs.getString("Qchapter"));
				qpojo.setCname(DBUtil.rs.getString("Cname"));
				qpojo.setQparagraph(DBUtil.rs.getString("Qparagraph"));
				list.add(qpojo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
		/**
		 * 按关键字查询问题
		 * @param keyword
		 * @param pageNow
		 * @param pageSize
		 * @return
		 */
	public List<QuestionPojo> queryQuestion(String tno,String keyword,int pageNow,int pageSize) {
		DBUtil.openConn();
		try {
			String sql = "select * from Question where Qcontent LIKE ? or Qtype like ? or Qchapter LIKE ? and Cname in (select DISTINCT(Course_name) from Tclass where Tno=?) limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "%" + keyword + "%");
			DBUtil.pstat.setString(2, "%" + keyword + "%");
			DBUtil.pstat.setString(3, "%" + keyword + "%");
			DBUtil.pstat.setString(4, tno);
			DBUtil.pstat.setInt(5, pageNow);
			DBUtil.pstat.setInt(6, pageSize);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			List<QuestionPojo> list = new ArrayList<QuestionPojo>();
			while (DBUtil.rs.next()) {
				QuestionPojo qpojo=new QuestionPojo();
				qpojo.setQid(DBUtil.rs.getInt("Qid"));
				qpojo.setQtype(DBUtil.rs.getString("Qtype"));
				qpojo.setQcontent(DBUtil.rs.getString("Qcontent"));
				qpojo.setQchoice(DBUtil.rs.getString("Qchoice"));
				qpojo.setQanswer(DBUtil.rs.getString("Qanswer"));
				qpojo.setQdegree(DBUtil.rs.getInt("Qdegree"));
				qpojo.setQchapter(DBUtil.rs.getString("Qchapter"));
				qpojo.setCname(DBUtil.rs.getString("Cname"));
				qpojo.setQparagraph(DBUtil.rs.getString("Qparagraph"));
				list.add(qpojo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询所有问题的数量
	 * @return
	 */
	public int queryAllQuestionCount(String tno){
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Question where Cname in (select DISTINCT(Course_name) from Tclass where Tno=?)";
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
	 * 按课程条件查询问题数量
	 * @param cc
	 * @return
	 */
	public int queryQuestionCounts(String Cno){
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Question where Cname=(select Cname from Course where Cno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cno);
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
	 * 按关键字条件查询班级数量
	 * @param keyword
	 * @return
	 */
	public int queryQuestionCount(String keyword,String tno){
		DBUtil.openConn();
		try {
			String sql = "select * from Question where Qcontent LIKE ? or Qtype like ? or Qchapter LIKE ? and Cname in (select DISTINCT(Course_name) from Tclass where Tno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "%" + keyword + "%");
			DBUtil.pstat.setString(2, "%" + keyword + "%");
			DBUtil.pstat.setString(3, "%" + keyword + "%");
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
	 * 添加问题
	 * @param qp
	 * @return
	 */
	public int addQuestion(QuestionPojo qp){
		DBUtil.openConn();
		try {
			String sql = "insert into Question(Qtype,Qcontent,Qchoice,Qanswer,Qdegree,Qchapter,Cname,Qparagraph) VALUES (?,?,?,?,?,?,?,?);";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, qp.getQtype());
			DBUtil.pstat.setString(2, qp.getQcontent());
			DBUtil.pstat.setString(3, qp.getQchoice());
			DBUtil.pstat.setString(4, qp.getQanswer());
			DBUtil.pstat.setInt(5, qp.getQdegree());
			DBUtil.pstat.setString(6, qp.getQchapter());
			DBUtil.pstat.setString(7, qp.getCname());
			DBUtil.pstat.setString(8, qp.getQparagraph());
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 根据id查询题目信息
	 * @param Qid
	 * @return
	 */
	public QuestionPojo queryQuestions(int Qid) {
		DBUtil.openConn();
		try {
			String sql = "select * from Question where Qid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, Qid);
			DBUtil.rs = DBUtil.pstat.executeQuery();
			QuestionPojo qpojo=new QuestionPojo();
			if (DBUtil.rs.next()) {
				qpojo.setQid(DBUtil.rs.getInt("Qid"));
				qpojo.setQtype(DBUtil.rs.getString("Qtype"));
				qpojo.setQcontent(DBUtil.rs.getString("Qcontent"));
				qpojo.setQchoice(DBUtil.rs.getString("Qchoice"));
				qpojo.setQanswer(DBUtil.rs.getString("Qanswer"));
				qpojo.setQdegree(DBUtil.rs.getInt("Qdegree"));
				qpojo.setQchapter(DBUtil.rs.getString("Qchapter"));
				qpojo.setCname(DBUtil.rs.getString("Cname"));
				qpojo.setQparagraph(DBUtil.rs.getString("Qparagraph"));
			}
			return qpojo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 根据题目编号修改题目信息
	 */
	public int modifyQuestion(QuestionPojo qp) {
		DBUtil.openConn();
		try {
			String sql = "update Question set Qtype=?,Qcontent=?,Qchoice=?,Qanswer=?,Qdegree=?,Qchapter=?,Cname=? where Qid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, qp.getQtype());
			DBUtil.pstat.setString(2, qp.getQcontent());
			DBUtil.pstat.setString(3, qp.getQchoice());
			DBUtil.pstat.setString(4, qp.getQanswer());
			DBUtil.pstat.setInt(5, qp.getQdegree());
			DBUtil.pstat.setString(6, qp.getQchapter());
			DBUtil.pstat.setString(7, qp.getCname());
			DBUtil.pstat.setInt(8, qp.getQid());
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 删除题目信息
	 */
	public void deleteQuestion(int qid) {
		DBUtil.openConn();
		try {
			String sql = "delete from Question where Qid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, qid);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
	public int queryQuestionCount(String type, String cno, String chapter) {
		DBUtil.openConn();
		try {
			String sql = "select count(*) as num from Question where Qtype=? and Qchapter=? and Cname=(select Cname from Course where Cno=?)";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, type);
			DBUtil.pstat.setString(2, chapter);
			DBUtil.pstat.setString(3, cno);
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
