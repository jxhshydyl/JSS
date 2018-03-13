package com.jss.teacher.action;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.jss.teacher.pojo.AnswerPojo;
import com.jss.teacher.service.AnswerService;

public class AnswerAction {
	private String obj;
	private AnswerService as;

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}
	public String addAnswer(){
		as=new AnswerService();
		JSONObject json = JSONObject.fromObject(obj);
		//ajax传json数据
		AnswerPojo ansObj = (AnswerPojo) JSONObject.toBean(json, AnswerPojo.class);
		as.addAnswer(ansObj);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("ano",ansObj.getAsk_no());
		return "addanswer";
	}
}
