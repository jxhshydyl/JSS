package com.jss.teacher.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jss.teacher.pojo.AnswerPojo;
import com.jss.teacher.pojo.MessageDetailPojo;
import com.jss.teacher.pojo.MessagePojo;
import com.jss.teacher.pojo.TeacherPojo;
import com.jss.teacher.service.AnswerService;
import com.jss.teacher.service.MessageService;
import com.jss.teacher.util.Pager;
import com.jss.teacher.util.SaveCondition;
import com.opensymphony.xwork2.ActionContext;
/**
 * 对学生的信息进行处理
 * @author Administrator
 *
 */
public class MessageAction {
	private String condition;//按条件查询信息是的条件
	private int pageNow=1;
	private String isFirst;//是否为第一次查看信息
	private String[] Ano;//删除信息是的信息id

	public String[] getAno() {
		return Ano;
	}

	public void setAno(String[] ano) {
		Ano = ano;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		if(pageNow==0){
			pageNow=1;
		}
		this.pageNow = pageNow;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	private MessageService ms;
	private AnswerService as;
	/**
	 * 查询所有信息
	 */
	public String queryAllMessage(){
		ms=new MessageService();
		List<MessagePojo> list=null;
		Pager page =null;
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();//得到教师编号
		int num=0;
		if(!"0".equals(isFirst)){
			condition=SaveCondition.message;
		}
		if(condition==null || condition.equals("")){
			num=ms.queryMessagesCount(tno);
			page=new Pager(pageNow,num);
			list=ms.queryAllMessage(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
			session.put("condition",condition);
		}else{
			SaveCondition.message=condition;
			num=ms.queryMessagesCount(tno,condition);
			page=new Pager(pageNow,num);
			list=ms.queryMessage(tno,condition,(pageNow-1)*page.getPageSize(),page.getPageSize());
			session.put("condition",null);
		}
		session.put("message", list);
		session.put("page", page);
		return "message";
	}
	/**
	 * 根据条件查询所有信息
	 */
	public String queryMessage(){
		ms=new MessageService();
		List<MessagePojo> list=null;
		Pager page =null;
		Map<String, Object> session=ActionContext.getContext().getSession();
		String tno=((TeacherPojo)session.get("teacher")).getTno();
		int num=0;
		SaveCondition.message=condition;
		if(condition==null || condition.equals("")){
			num=ms.queryMessagesCount(tno);
			page=new Pager(pageNow,num);
			list=ms.queryAllMessage(tno,(pageNow-1)*page.getPageSize(),page.getPageSize());
			session.put("condition",condition);
		}else{
			SaveCondition.message=condition;
			num=ms.queryMessagesCount(tno,condition);
			page=new Pager(pageNow,num);
			list=ms.queryMessage(tno,condition,(pageNow-1)*page.getPageSize(),page.getPageSize());
			session.put("condition",null);
		}
		session.put("message", list);
		session.put("page", page);
		return "condition_message";
	}
	/**
	 * 删除消息
	 * @return
	 */
	public String deleteMessage(){
		ms=new MessageService();
		Ano=Ano[0].split(",");
		int[] qid=new int[Ano.length];
		for(int i=0;i<Ano.length;i++){
			qid[i]=Integer.parseInt(Ano[i]);
		}
		ms.deleteMessage(qid);
		return "deletemessage";
	}
	/**
	 * 查询详细消息和回复信息
	 */
	public String queryMessageDetail(){
		ms=new MessageService();
		as=new AnswerService();
		List<MessageDetailPojo> list=ms.queryMessageDetail(Ano[0]);
		List<AnswerPojo> list1=as.queryAnswer(Ano[0]);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("messgeDetail", list);
		request.setAttribute("answer", list1);
		return "messagedetail";
	}
}
