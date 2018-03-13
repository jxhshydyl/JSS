package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TaskPojo;
import com.jss.teacher.util.DBUtil;

public class TaskDao {
	/**
	 * 自动组卷
	 * @param type
	 * @param count
	 * @param cno
	 * @param chapter
	 * @return
	 */
	public List<QuestionPojo> autoMakePaper(String type, int count,String cno,String chapter) {
		DBUtil.openConn();
		try {
			String sql = "SELECT * FROM Question where Qchapter=? and Qtype=? and Cname=(select Cname from Course where Cno=?)  ORDER BY  RAND() LIMIT ?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, chapter);
			DBUtil.pstat.setString(2, type);
			DBUtil.pstat.setString(3, cno);
			DBUtil.pstat.setInt(4, count);
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
	 * 安排作业
	 * @param tp
	 * @return
	 */
	public int arrangeTask(TaskPojo tp){
		DBUtil.openConn();
		try {
			String sql = "insert into Task(Task_name,Tappendixes,Tstate,Tscore,Tno,Tname,SubTime,Cno,Tchapter) VALUES (?,?,?,?,?,?,?,?,?);";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tp.getTask_name());
			DBUtil.pstat.setString(2, tp.getTappendixes());
			DBUtil.pstat.setString(3, tp.getTstate());
			DBUtil.pstat.setFloat(4, tp.getTscore());
			DBUtil.pstat.setString(5, tp.getTno());
			DBUtil.pstat.setString(6, tp.getTname());
			DBUtil.pstat.setString(7, tp.getSubtime());
			DBUtil.pstat.setString(8, tp.getCno());
			DBUtil.pstat.setString(9, tp.getTchapter());
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 通过作业编号查询作业id,以便组卷
	 * @param tno
	 * @param time
	 * @return
	 */
	public int queryTaskId(String tno, String time) {
		DBUtil.openConn();
		try {
			String sql = "select Tid from Task where Tno=? and SubTime=? ORDER BY SubTime DESC";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, time);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int Tid=0;
			if(DBUtil.rs.next()){
				Tid=DBUtil.rs.getInt("Tid");
			}
			return Tid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 查询教师所有作业的数量
	 * @param tno
	 * @return
	 */
	public int queryAllTaskCount(String tno) {
		DBUtil.openConn();
		try {
			String sql = "select Count(*) as num from Task where Tno=?";
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
	 * 查询教师所有的作业信息
	 * @param tno
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	//需修改
	public List<TaskPojo> queryAllTask(String tno, int pageNow, int pageSize) {
		DBUtil.openConn();
		try {
			String sql = "select Task.*,Course.Cname from Task,Course where Tno=? and Task.Cno=Course.Cno ORDER BY Task.SubTime DESC limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<TaskPojo> list=new ArrayList<TaskPojo>();
			while(DBUtil.rs.next()){
				TaskPojo task=new TaskPojo();
				task.setTid(DBUtil.rs.getInt("Tid"));
				task.setTask_name(DBUtil.rs.getString("Task_name"));
				String[] path=null;
				if(DBUtil.rs.getString("Tappendixes")!="无"){
					path=DBUtil.rs.getString("Tappendixes").split("/");
					task.setTappendixes(path[path.length-1]);
				}else{
					task.setTappendixes(DBUtil.rs.getString("Tappendixes"));
				}
				task.setTno(DBUtil.rs.getString("Tno"));
				task.setTname(DBUtil.rs.getString("Tname"));
				task.setTscore(DBUtil.rs.getFloat("Tscore"));
				task.setCno(DBUtil.rs.getString("Cno"));
				task.setCname(DBUtil.rs.getString("Course.Cname"));
				task.setSubtime(DBUtil.rs.getString("Subtime"));
				task.setTstate(DBUtil.rs.getString("Tstate"));
				task.setTchapter(DBUtil.rs.getString("Tchapter"));
				task.setTtype(DBUtil.rs.getString("Ttype"));
				list.add(task);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 根据条件查询教师发布的作业数量
	 * @param tno
	 * @param condition
	 * @return
	 */
	public int queryTaskCount(String tno, String condition) {
		DBUtil.openConn();
		try {
			String sql = "select Count(*) as num from Task where Tno=? and (Task_name like ? or Tstate like ? or SubTime like ? or Tchapter like ? or Task.Cno in (select Cno from Course where Cname like ?))";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, "%"+condition+"%");
			DBUtil.pstat.setString(3, "%"+condition+"%");
			DBUtil.pstat.setString(4, "%"+condition+"%");
			DBUtil.pstat.setString(5, "%"+condition+"%");
			DBUtil.pstat.setString(6, "%"+condition+"%");
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
	 * 根据条件查询教师发布的作业信息
	 * @param tno
	 * @param condition
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<TaskPojo> queryTask(String tno, String condition,
			int pageNow, int pageSize) {
		DBUtil.openConn();
		try {
			String sql = "select Task.*,Course.Cname from Task,Course where Tno=? and Task.Cno=Course.Cno and (Task_name like ? or Tstate like ? or SubTime like ? or Tchapter like ? or Task.Cno in (select Cno from Course where Cname like ?)) ORDER BY Task.SubTime DESC limit ?,?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setString(2, "%"+condition+"%");
			DBUtil.pstat.setString(3, "%"+condition+"%");
			DBUtil.pstat.setString(4, "%"+condition+"%");
			DBUtil.pstat.setString(5, "%"+condition+"%");
			DBUtil.pstat.setString(6, "%"+condition+"%");
			DBUtil.pstat.setInt(7, pageNow);
			DBUtil.pstat.setInt(8, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<TaskPojo> list=new ArrayList<TaskPojo>();
			while(DBUtil.rs.next()){
				TaskPojo task=new TaskPojo();
				task.setTid(DBUtil.rs.getInt("Tid"));
				task.setTask_name(DBUtil.rs.getString("Task_name"));
				String[] path=null;
				if(DBUtil.rs.getString("Tappendixes")!="无"){
					path=DBUtil.rs.getString("Tappendixes").split("/");
					task.setTappendixes(path[path.length-1]);
				}else{
					task.setTappendixes(DBUtil.rs.getString("Tappendixes"));
				}
				task.setTno(DBUtil.rs.getString("Tno"));
				task.setTname(DBUtil.rs.getString("Tname"));
				task.setTscore(DBUtil.rs.getFloat("Tscore"));
				task.setCno(DBUtil.rs.getString("Course.Cname"));
				task.setSubtime(DBUtil.rs.getString("Subtime"));
				task.setTstate(DBUtil.rs.getString("Tstate"));
				task.setTchapter(DBUtil.rs.getString("Tchapter"));
				list.add(task);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 根据作业编号删除作业
	 * @param tid
	 */
	public void deleteTask(int tid) {
		DBUtil.openConn();
		try {
			String sql = "delete Task,Tdetail,Ctask,Ask,Answer from Task "
					+ "LEFT JOIN Tdetail on Tdetail.Tid=Task.Tid "
					+ "LEFT JOIN Ctask on Task.Tid=Ctask.Tid "
					+ "LEFT JOIN Ask on Task.Tid=Ask.Tid "
					+ "LEFT JOIN Answer on Task.Tid=Answer.Tid  where Task.Tid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, tid);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
	/**
	 * 根据作业编号查询作业信息
	 * @param tid
	 * @return
	 */
	public TaskPojo queryTask(String tid) {
		DBUtil.openConn();
		try {
			String sql = "select Task.*,Course.Cname from Task,Course where Tid=? and Task.Cno=Course.Cno";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tid);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			TaskPojo task=new TaskPojo();
			if(DBUtil.rs.next()){
				task.setTid(DBUtil.rs.getInt("Tid"));
				task.setTask_name(DBUtil.rs.getString("Task_name"));
				String[] path=null;
				if(DBUtil.rs.getString("Tappendixes")!="无"){
					path=DBUtil.rs.getString("Tappendixes").split("/");
					task.setTappendixes(path[path.length-1]);
				}else{
					task.setTappendixes(DBUtil.rs.getString("Tappendixes"));
				}
				task.setTno(DBUtil.rs.getString("Tno"));
				task.setTname(DBUtil.rs.getString("Tname"));
				task.setTscore(DBUtil.rs.getFloat("Tscore"));
				task.setCno(DBUtil.rs.getString("Course.Cname"));
				task.setSubtime(DBUtil.rs.getString("Subtime"));
				task.setTstate(DBUtil.rs.getString("Tstate"));
				task.setTchapter(DBUtil.rs.getString("Tchapter"));
			}
			return task;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 更新作业的状态
	 * @param tid
	 * @return 
	 * @return
	 */
	public int updateTaskState(String tid) {
		DBUtil.openConn();
		try {
			String sql = "update Task set Tstate=? where Tid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "已发布");
			DBUtil.pstat.setString(2, tid);
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 更新作业分数
	 * @param totalscore
	 * @return
	 */
	public int updateTaskScore(float totalscore,String tid) {
		DBUtil.openConn();
		try {
			String sql = "update Task set Tscore=? where Tid=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setFloat(1, totalscore);
			DBUtil.pstat.setString(2, tid);
			int num=DBUtil.pstat.executeUpdate();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
}
