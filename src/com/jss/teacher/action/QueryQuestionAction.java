package com.jss.teacher.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import com.jss.teacher.service.QuestionService;

public class QueryQuestionAction {
	private String type;
	private String chapter;
	private String cno;
	private String num;
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	private QuestionService qs;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}
	/**
	 * 查询问题数量
	 * @return
	 */
	public String queryQuestionCount(){
		qs=new QuestionService();
		num=String.valueOf(qs.queryQuestionCount(type,cno,chapter));
		Map<String, String> map = new HashMap<String,String>();
		map.put("num", num);
		JSONArray json = new JSONArray();
		json.add(map);
		num = json.toString();
		return "queryquestioncount";
	}
}
