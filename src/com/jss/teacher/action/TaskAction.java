package com.jss.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.AutoMakePaperPara;
import com.jss.teacher.pojo.TaskPojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.TaskService;
import com.jss.teacher.util.Pager;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author Administrator
 *  根据教师编号对该教师的作业进行处理
 */
public class TaskAction {
	private AutoMakePaperPara ampp;//安排作业分数，类型，数量，班级，截止时间等参数
	private TaskPojo tp;//安排作业时的作业参数
	private String uploadFileName;//附件名称
	private File upload;//作业附件
	private TaskService ts;
	private String condition;//查询作业的条件
	private String[] Tid;//所要删除的作业编号数组
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public AutoMakePaperPara getAmpp() {
		return ampp;
	}

	public void setAmpp(AutoMakePaperPara ampp) {
		this.ampp = ampp;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	private int pageNow=1; 
	
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		if(pageNow==0){
			pageNow=1;
		}
		this.pageNow = pageNow;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public TaskPojo getTp() {
		return tp;
	}

	public void setTp(TaskPojo tp) {
		this.tp = tp;
	}

	public String[] getTid() {
		return Tid;
	}

	public void setTid(String[] tid) {
		Tid = tid;
	}
	/**
	 * 查询所有作业
	 * @return
	 */
	public String queryAllTask(){
		ts=new TaskService();
		HttpServletRequest request=ServletActionContext.getRequest();
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		List<TaskPojo> list=null;
		Pager page=null;
		if(condition==null || condition.trim().equals("")){
			condition="";
			page=new Pager(pageNow, ts.queryAllTaskCount(tno));
			list=ts.queryAllTask(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		}else{
			page=new Pager(pageNow, ts.queryTaskCount(tno,condition.trim()));
			list=ts.queryTask(tno,condition.trim(),(pageNow-1)*page.getPageSize(),page.getPageSize());
		}
		request.setAttribute("condition",condition);//保存条件，使前台能得到条件
		request.setAttribute("task", list);
		request.setAttribute("page",page);
		return "querytask";
	}
	/**
	 * 安排作业
	 * @return
	 */
	public String arrangeTask() throws Exception{
		String path="";//保存作业附件路径
		if(getUpload()!=null){//保存作业附件
			InputStream is=new FileInputStream(getUpload());
			path="C:/Users/Administrator/Desktop/"+uploadFileName;
			OutputStream os=new FileOutputStream(path);
			byte buffer[]=new byte[1024];
			int counts=0;
			while((counts=is.read(buffer))>0){
				os.write(buffer, 0, counts);
			}
			os.close();
			is.close();
		}else{//若无作业附件，则路径为空
			path="无";
		}
		ts=new TaskService();
		ts.arrangeTask(tp,path,ampp);
		return "arrangetask";
	}
	/**
	 * 删除作业
	 * @return
	 */
	public String deleteTask(){
		ts=new TaskService();
		Tid=Tid[0].split(",");//得到所选择的作业的id
		int[] qid=new int[Tid.length];
		for(int i=0;i<Tid.length;i++){
			qid[i]=Integer.parseInt(Tid[i]);
		}
		ts.deleteTask(qid);
		return "deletetask";
	}
	/**
	 * 根据课程查询作业
	 */
	public String queryTaskByCourse(){
		ts=new TaskService();
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		List<TaskPojo> list=null;
		Pager page=new Pager(pageNow, ts.queryAllTaskCount(tno));
		list=ts.queryAllTask(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
		System.out.println(list);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("task", list);
		JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
		return "querytaskbycourse";
	}
}
