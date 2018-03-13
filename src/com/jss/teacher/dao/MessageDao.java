package com.jss.teacher.dao;

import java.util.ArrayList;
import java.util.List;
import com.jss.teacher.pojo.MessageDetailPojo;
import com.jss.teacher.pojo.MessagePojo;
import com.jss.teacher.util.DBUtil;

public class MessageDao {
	/**
	 * 查询所有提问信息
	 */
	public List<MessagePojo> queryAllMessage(String tno,int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql="select a.Ano,a.Acontent,a.Atime,a.Sno,a.Sname,Task.Task_name,q.Qcontent,t.Tname,a.Astate "
					+ "from Ask as a,Task,Question as q,Teacher as t "
					+ "where a.Tid=Task.Tid and a.Qid=q.Qid and a.Tno=t.Tno and a.Tno=? limit ?,?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, tno);
			DBUtil.pstat.setInt(2, pageNow);
			DBUtil.pstat.setInt(3, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<MessagePojo> list=new ArrayList<MessagePojo>();
			while(DBUtil.rs.next()){
				MessagePojo message=new MessagePojo();
				message.setAno(DBUtil.rs.getInt("a.Ano"));
				message.setAcontent(DBUtil.rs.getString("a.Acontent"));
				message.setAtime(DBUtil.rs.getString("a.Atime"));
				message.setSno(DBUtil.rs.getString("a.Sno"));
				message.setSname(DBUtil.rs.getString("a.Sname"));
				message.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				message.setQcontent(DBUtil.rs.getString("q.Qcontent"));
				message.setTname(DBUtil.rs.getString("t.Tname"));
				message.setAstate(DBUtil.rs.getString("a.Astate"));
				list.add(message);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 按条件查询所有提问信息
	 */
	public List<MessagePojo> queryMessage(String tno,String contidion, int pageNow,int pageSize){
		DBUtil.openConn();
		try{
			String sql="select a.Ano,a.Acontent,a.Atime,a.Sno,a.Sname,Task.Task_name,q.Qcontent,t.Tname,a.Astate "
					+ "from Ask as a,Task,Question as q,Teacher as t "
					+ "where a.Tid=Task.Tid and a.Qid=q.Qid and a.Tno=t.Tno and a.Tno=? and (a.Acontent like ? or a.Sno like ? or a.Sname like ? or a.Astate like ?) limit ?,?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "1");
			DBUtil.pstat.setString(2, "%"+contidion+"%");
			DBUtil.pstat.setString(3, "%"+contidion+"%");
			DBUtil.pstat.setString(4, "%"+contidion+"%");
			DBUtil.pstat.setString(5, "%"+contidion+"%");
			DBUtil.pstat.setInt(6, pageNow);
			DBUtil.pstat.setInt(7, pageSize);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<MessagePojo> list=new ArrayList<MessagePojo>();
			while(DBUtil.rs.next()){
				MessagePojo message=new MessagePojo();
				message.setAno(DBUtil.rs.getInt("a.Ano"));
				message.setAcontent(DBUtil.rs.getString("a.Acontent"));
				message.setAtime(DBUtil.rs.getString("a.Atime"));
				message.setSno(DBUtil.rs.getString("a.Sno"));
				message.setSname(DBUtil.rs.getString("a.Sname"));
				message.setTask_name(DBUtil.rs.getString("Task.Task_name"));
				message.setQcontent(DBUtil.rs.getString("q.Qcontent"));
				message.setTname(DBUtil.rs.getString("t.Tname"));
				message.setAstate(DBUtil.rs.getString("a.Astate"));
				list.add(message);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
	/**
	 * 查询所有提问信息数量
	 * @param tno
	 * @return
	 */
	public int queryAllMessageCount(String tno) {
		DBUtil.openConn();
		try{
			String sql="select count(*) as num from Ask where Tno=?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "1");
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int i = 0;
			if(DBUtil.rs.next()){
				i=Integer.parseInt(DBUtil.rs.getString("num"));
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 按条件查询信息数量
	 * @param tno
	 * @param condition
	 * @return
	 */
	public int queryMessageCount(String tno, String condition) {
		DBUtil.openConn();
		try{
			String sql="select count(*) as num from Ask where Tno=? and (Acontent like ? or Sno like ? or Sname like ? or Astate like ?)";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, "1");
			DBUtil.pstat.setString(2, "%"+condition+"%");
			DBUtil.pstat.setString(3, "%"+condition+"%");
			DBUtil.pstat.setString(4, "%"+condition+"%");
			DBUtil.pstat.setString(5, "%"+condition+"%");
			DBUtil.rs=DBUtil.pstat.executeQuery();
			int i = 0;
			if(DBUtil.rs.next()){
				i=Integer.parseInt(DBUtil.rs.getString("num"));
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return 0;
	}
	/**
	 * 删除信息
	 * @param Ano
	 */
	public void deleteMessage(int Ano) {
		DBUtil.openConn();
		try {
			String sql = "DELETE Ask,Answer from Ask LEFT JOIN Answer ON Ask.Ano=Answer.Ask_no WHERE Ask.Ano=?";
			DBUtil.pstat = DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setInt(1, Ano);
			DBUtil.pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.closeConn();
	}
	/**
	 *查询消息详细信息
	 * @param ano
	 * @return 
	 */
	public List<MessageDetailPojo> queryMessageDetail(String Ano) {
		DBUtil.openConn();
		try{
			String sql="select a1.Ano,a1.Acontent,a1.Atime,a1.Sno,a1.Sname,a1.Tno,a1.Qid,a1.Tid,q.Qcontent,t.Tname from Ask as a1,Question as q,Teacher as t where a1.Ano=? and q.Qid=a1.Qid and a1.Tno=t.Tno";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Ano);
			DBUtil.rs=DBUtil.pstat.executeQuery();
			List<MessageDetailPojo> list=new ArrayList<MessageDetailPojo>();
			while(DBUtil.rs.next()){
				MessageDetailPojo messageDetail=new MessageDetailPojo();
				messageDetail.setAno(DBUtil.rs.getInt("a1.Ano"));
				messageDetail.setAsk_content(DBUtil.rs.getString("a1.Acontent"));
				messageDetail.setAsk_time(DBUtil.rs.getString("a1.Atime"));
				messageDetail.setSno(DBUtil.rs.getString("a1.Sno"));
				messageDetail.setTno(DBUtil.rs.getString("a1.Tno"));
				messageDetail.setQid(DBUtil.rs.getString("a1.Qid"));
				messageDetail.setTid(DBUtil.rs.getString("a1.Tid"));
				messageDetail.setSname(DBUtil.rs.getString("a1.Sname"));
				messageDetail.setQcontent(DBUtil.rs.getString("q.Qcontent"));
				messageDetail.setTname(DBUtil.rs.getString("t.Tname"));
				list.add(messageDetail);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
}
