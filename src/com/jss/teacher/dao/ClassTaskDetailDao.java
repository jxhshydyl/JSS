package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.ClassTaskDetailPojo;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.util.DBUtil;

public class ClassTaskDetailDao {
	/**
	 * 查询某个班级所交的某一次作业
	 * @param tid
	 * @param cno
	 * @return
	 */
	public List<ClassTaskDetailPojo> queryClassTaskDetail(String tid, String cno) {
		DBUtil.openConn();
		try {
			String sql = "select Stask.id,Stask.Tstate,Stask.Sno,Sinformation.Sname,Ctask.End_time,Task.Task_name,Task.Tid "
					+ "from Stask,Sinformation,Ctask,Task "
					+ "where Stask.tid=? and "
					+ "Stask.sno in(select sno from Sinformation where Sclass=(select Cname from Class where Cno=?)) "
					+ "and Stask.Sno=Sinformation.Sno and Ctask.Tid=Stask.Tid and Ctask.Cno=? and Task.Tid=Ctask.Tid";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tid);
			DBUtil.pstat.setString(2, cno);
			DBUtil.pstat.setString(3, cno);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<ClassTaskDetailPojo> list=new ArrayList<ClassTaskDetailPojo>();
			while(DBUtil.rs.next()){
				ClassTaskDetailPojo classTask=new ClassTaskDetailPojo();
				classTask.setId(DBUtil.rs.getInt("Stask.id"));
				classTask.setEnd_time(DBUtil.rs.getString("Ctask.End_time"));
				classTask.setSname(DBUtil.rs.getString("Sinformation.Sname"));
				classTask.setSno(DBUtil.rs.getString("Stask.Sno"));
				classTask.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				classTask.setTid(DBUtil.rs.getString("Task.Tid"));
				classTask.setTstate(DBUtil.rs.getString("Stask.Tstate"));
				list.add(classTask);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	public List<QuestionPojo> queryClassTaskDetail(String sid) {
		DBUtil.openConn();
		try {
			String sql = "select s.Qid,s.Sanswer,s.Score as grade,q.Qtype,q.Qcontent,q.Qchoice,q.Qanswer,q.Qdegree,t.Score "
					+ "from STdetail s LEFT JOIN Question q on q.Qid=s.Qid "
					+ "LEFT JOIN Tdetail t on t.Qid=s.Qid and t.Tid=(select Tid from Stask where id=?) "
					+ "where s.id=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, sid);
			DBUtil.pstat.setString(2, sid);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<QuestionPojo> list=new ArrayList<QuestionPojo>();
			while(DBUtil.rs.next()){
				QuestionPojo question=new QuestionPojo();
				question.setQid(DBUtil.rs.getInt("Qid"));
				question.setGrade(DBUtil.rs.getFloat("grade"));
				question.setQtype(DBUtil.rs.getString("Qtype"));
				question.setQcontent(DBUtil.rs.getString("Qcontent"));
				//把选项通过A,B,C,D分隔开
				question.setChoice(DBUtil.rs.getString("Qchoice").trim().split("[A-Z][|:|：|、|.]"));
				question.setQanswer(DBUtil.rs.getString("Qanswer"));
				question.setQdegree(DBUtil.rs.getInt("Qdegree"));
				question.setAnswer(DBUtil.rs.getString("Sanswer"));
				question.setScore(DBUtil.rs.getFloat("Score"));
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
	 * 通过学生提交作业id和问题id保存已批阅问题分数
	 * @param id
	 * @param qid
	 * @param score
	 * @return
	 */
	public int saveSubmitTaskDetailScore(String id,String qid, String score) {
		DBUtil.openConn();
		try {
			String sql = "update STdetail set Score=? where id=? and Qid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, score);
			DBUtil.pstat.setString(2, id);
			DBUtil.pstat.setString(3, qid);
			int num = DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	public static void main(String[] args) {
		ClassTaskDetailDao dao=new ClassTaskDetailDao();
		System.out.println(dao.queryClassTaskDetail("1"));
	}
	
	
}
