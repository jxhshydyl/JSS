package com.jss.teacher.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jss.teacher.dao.TaskDao;
import com.jss.teacher.dao.TaskdetailDao;
import com.jss.teacher.pojo.AutoMakePaperPara;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.pojo.TaskPojo;

public class TaskService {
	/**
	 * 安排作业
	 * @param ampp  保存题目类型,单个题目分数,题目数量（前台所选择的用','分开，与分数，类型一一对应）
	 * @param tp    作业信息
	 * @param path  作业附件保存路径（若无附件为空）
	 */
	public void arrangeTask(TaskPojo tp,String path,AutoMakePaperPara ampp) {
		String[] str={"单选题","多选题","填空题","判断题","简答题","代码题"};
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=dateFormat.format(new Date());//得到安排作业的时间
		tp.setSubtime(time);
		tp.setTappendixes(path);
		if(ampp.getCno()!=null){
			tp.setTstate("已发布");
		}else{
			tp.setTstate("未发布");
		}
		TaskDao dao=new TaskDao();
		TaskdetailDao dao1=new TaskdetailDao();
		String count=ampp.getCount();//得到数量的字符串
		String score=ampp.getScore();//得到分数的字符串
		String type=ampp.getTypes();//得到类型的字符串
		int Tid=0;//保存新作业的id
		if(count!=null){//如果在安排作业时选择了作业类型
			String[] counts=count.split(",");
			String[] scores=score.split(",");
			String[] types=type.split(",");
			float total_score=0;
			for(int i=0;i<counts.length;i++){//计算作业总分
				if(scores[i].trim().equals("")){
					scores[i]="0";
				}
				if(counts[i].trim().equals("")){
					counts[i]="0";
				}
				//得到总分
				total_score+=Float.parseFloat(scores[i].trim())*Integer.parseInt(counts[i].trim());
			}
			tp.setTscore(total_score);
			dao.arrangeTask(tp);//把新的作业插入数据库
			Tid=dao.queryTaskId(tp.getTno(),time);//得到新作业的id
			if(Tid!=0){//确保上行代码出现异常，而数据不出错
				for(int i=0;i<counts.length;i++){
					if(types[i].trim().equals("5")){//随机抽取代码题
						
					}else{//随机抽取选择，填空，判断，简答等题目
						//根据类型随机抽取题目
						List<QuestionPojo> list = dao.autoMakePaper(str[Integer.parseInt(types[i].trim())],Integer.parseInt(counts[i].trim()),tp.getCno(),tp.getTchapter());
						for(int j=0;j<list.size();j++){
							//根据作业的Tid保存随机抽取的题目信息
							dao1.addPaper(list.get(j),Float.parseFloat(scores[i].trim()),Tid);
						}
					}
				}
			}
		}else{//如果在安排作业时没有选择作业类型（既只有附件，没有题目）
			dao.arrangeTask(tp);//把新的作业插入数据库
			Tid=dao.queryTaskId(tp.getTno(),time);//得到新作业的id
		}
		if(ampp.getCno()!=null && Tid!=0){//选择了要发布作业的班级
			IssueTaskService its=new IssueTaskService();
			its.issueTask(String.valueOf(Tid), ampp.getEnd_time(), ampp.getCno());
		}
	}
	/**
	 * 根据教师查询所有作业数量
	 * @param string
	 * @return
	 */
	public int queryAllTaskCount(String tno) {
		TaskDao dao=new TaskDao();
		int num=dao.queryAllTaskCount(tno);
		return num;
	}
	/**
	 * 根据教师查询所有作业,并分页
	 * @param tno
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<TaskPojo> queryAllTask(String tno,int pageNow, int pageSize) {
		TaskDao dao=new TaskDao();
		List<TaskPojo> list=dao.queryAllTask(tno,pageNow,pageSize);
		return list;
	}
	/**
	 * 根据教师编号和条件查询作业数量
	 * @param tno
	 * @param condition
	 * @return
	 */
	public int queryTaskCount(String tno, String condition) {
		TaskDao dao=new TaskDao();
		int num=dao.queryTaskCount(tno,condition);
		return num;
	}
	/**
	 * 根据教师编号和条件查询作业，并分页
	 * @param tno
	 * @param condition
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<TaskPojo> queryTask(String tno, String condition, int pageNow,
			int pageSize) {
		TaskDao dao=new TaskDao();
		List<TaskPojo> list=dao.queryTask(tno,condition,pageNow,pageSize);
		return list;
	}
	/**
	 * 根据教师编号删除作业
	 * @param tid
	 */
	public void deleteTask(int[] tid) {
		TaskDao dao=new TaskDao();
		for(int i=0;i<tid.length;i++){
			dao.deleteTask(tid[i]);
		}
	}
	/**
	 * 根据作业编号查询作业信息
	 * @param tid
	 */
	public TaskPojo queryTask(String tid) {
		TaskDao dao=new TaskDao();
		TaskPojo task = dao.queryTask(tid);
		return task;
	}
	/**
	 * 根据作业编号更新作业的状态
	 * @param tid
	 * @return
	 */
	public int updateTaskState(String tid) {
		TaskDao dao=new TaskDao();
		int num = dao.updateTaskState(tid);
		return num;
	}
	public int updateTaskScore(float totalscore,String tid) {
		TaskDao dao=new TaskDao();
		int num=dao.updateTaskScore(totalscore,tid);
		return num;
	}
}
