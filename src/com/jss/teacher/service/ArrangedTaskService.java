package com.jss.teacher.service;

import java.util.List;
import com.jss.teacher.dao.ArrangedTaskDao;
import com.jss.teacher.pojo.ArrangedTaskPojo;
/**
 * 查询已经安排的作业及学生已经提交的作业
 * @author Administrator
 *
 */
public class ArrangedTaskService {
	/**
	 * 根据教师编号查询已经安排的作业信息
	 * @param tno
	 * @return
	 */
	public List<ArrangedTaskPojo> queryArrangedTask(String tno,int pageNow,int pageSize){
		ArrangedTaskDao dao=new ArrangedTaskDao();
		List<ArrangedTaskPojo> list = dao.queryArrangeTask(tno, pageNow, pageSize);
		return list;
	}
	/**
	 * 查询所有已经安排的任务的数量
	 * @param tno
	 * @return
	 */
	public int queryAllTaskCount(String tno) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		int num=dao.queryAllTaskCount(tno);
		return num;
	}
	/**
	 *按关键字查询教师已经发布的作业的数量
	 * @param tno
	 * @param key
	 * @return
	 */
	public int queryTaskCount(String tno, String key) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		int num=dao.queryTaskCount(tno,key);
		return num;
	}
	/**
	 * 按关键字查询教师已经发布的作业的信息
	 * @param tno
	 * @param key
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<ArrangedTaskPojo> queryArrangedTask(String tno, String key, int pageNow, int pageSize) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		List<ArrangedTaskPojo> list = dao.queryArrangeTask(tno,key, pageNow, pageSize);
		return list;
	}
	/**
	 * 按班级或课程查询教师已经发布的作业的数量
	 * @param tno
	 * @param cno
	 * @param course
	 * @return
	 */
	public int queryTaskCount(String tno, String cno, String course) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		int num=dao.queryTaskCount(tno,cno,course);
		return num;
	}
	/**
	 * 按班级或课程查询教师已经发布的作业的信息
	 * @param tno
	 * @param key
	 * @param cno
	 * @param i
	 * @param pageSize
	 * @return
	 */
	public List<ArrangedTaskPojo> queryArrangedTask(String tno, String course,String cno, int pageNow, int pageSize) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		List<ArrangedTaskPojo> list = dao.queryArrangeTask(tno,course,cno,pageNow,pageSize);
		return list;
	}
	/**
	 * 根据班级编号和作业编号删除已经安排的作业
	 * @param tid 作业编号
	 * @param cno 班级编号
	 */
	public void deleteArrangedTask(String[] tid,String[] cno) {
		ArrangedTaskDao dao=new ArrangedTaskDao();
		for(int i=0;i<tid.length;i++){
			dao.deleteArrangedTask(tid[i],cno[i]);
		}
		
	}
}
