package com.jss.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jss.teacher.pojo.CodePojo;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.service.QuestionService;
import com.jss.teacher.util.ExcelUtil;

public class UploadActioin {
	private File file;
	private String question_type;
	private String format_type;
	private String fileFileName;
	private String msg;

	public String getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	public String getFormat_type() {
		return format_type;
	}

	public void setFormat_type(String format_type) {
		this.format_type = format_type;
	}

	private QuestionService service;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String bulkAddQuestion() throws Exception {
		if ("code".equals(question_type)) {
			if (getFile() == null) {
				return "error";
			} else {
				InputStream is = new FileInputStream(getFile());
				try {
					int temp2;
					String str1 = "题目标题";
					String str2 = "题目描述";
					String str3 = "输入描述";
					String str4 = "输出描述";
					String str5 = "输入例子";
					String str6 = "输出例子";
					String str7 = "参考答案";
					String str8 = "时间限制";
					String str9 = "内存限制";
					String str10 = "难易程度";
					String str11 = "题目类型";
					String str12 = "所属课程";
					String str13 = "所属章";
					String str14 = "所属节";
					CodePojo que = new CodePojo();
					List<String> list = new ArrayList<String>();
					list.add(str1);
					list.add(str2);
					list.add(str3);
					list.add(str4);
					list.add(str5);
					list.add(str6);
					list.add(str7);
					list.add(str8);
					list.add(str9);
					list.add(str10);
					list.add(str11);
					list.add(str12);
					list.add(str13);
					list.add(str14);
					ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(is,
							fileFileName.split("\\.")[fileFileName.split("\\.").length - 1]);
					for (int j = 0; j < result.get(0).size(); j++) {
						if (result.get(0).get(j) == null || !(result.get(0).get(j).toString().equals(list.get(j))))
							return "error";
					}
					service = new QuestionService();
					for (int i = 1; i < result.size(); i++) {
						que.setQname((String) result.get(i).get(0));
						que.setQdescribe((String) result.get(i).get(1));
						que.setInputDescribe(result.get(i).get(2).toString().trim());
						que.setOutputDescripe(result.get(i).get(3).toString());
						que.setExampleInput((String) result.get(i).get(4));
						que.setExampleOutput(result.get(i).get(5).toString());
						que.setReferenceAnswer((String) result.get(i).get(6));
						if(result.get(i).get(7).toString().equals("")) {
							que.setLimitTime(0f);
						}else {
							que.setLimitTime(Float.valueOf(result.get(i).get(7).toString()));
						}
						if(result.get(i).get(8).toString().equals("")) {
							que.setLimitMemory(0f);
						}else {
							que.setLimitTime(Float.valueOf(result.get(i).get(8).toString()));
						}

						if ("".equals((String) result.get(i).get(9))) {
							que.setQdegree(3);
						} else {
							que.setQdegree((int) Float.parseFloat((String) result.get(i).get(9)));
						}
						if(result.get(i).size()==11) {
							que.setQtype((String) result.get(i).get(10));
						}
						if(result.get(i).size()==12) {
							que.setQtype((String) result.get(i).get(10));
							que.setCname((String) result.get(i).get(11));
						}
						if(result.get(i).size()==13) {
							que.setQtype((String) result.get(i).get(10));
							que.setCname((String) result.get(i).get(11));
							que.setQchapter((String) result.get(i).get(12));
						}
						if(result.get(i).size()==14) {
							que.setQtype((String) result.get(i).get(10));
							que.setCname((String) result.get(i).get(11));
							que.setQchapter((String) result.get(i).get(12));
							que.setQparagraph((String) result.get(i).get(13));
						}
						service.addCodeQuestion(que);
					}
					return "bulk_add_question";
				} catch (Exception e) {
					return "bulk_add_question";
				}
			}
		} else if ("noCode".equals(question_type)) {
			if (getFile() == null) {
				return "error";
			} else {
				InputStream is = new FileInputStream(getFile());
				try {
					int temp2;
					String str1 = "题目内容";
					String str2 = "题目类型";
					String str3 = "题目选项";
					String str4 = "题目答案";
					String str5 = "题目难度";
					String str6 = "所属课程";
					String str7 = "所属章";
					String str8 = "所属节";
					QuestionPojo que = new QuestionPojo();
					List<String> list = new ArrayList<String>();
					list.add(str1);
					list.add(str2);
					list.add(str3);
					list.add(str4);
					list.add(str5);
					list.add(str6);
					list.add(str7);
					list.add(str8);
					ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(is,
							fileFileName.split("\\.")[fileFileName.split("\\.").length - 1]);
					for (int j = 0; j < 8; j++) {
						if (result.get(0).get(j) == null || !(result.get(0).get(j).toString().equals(list.get(j))))
							return "error";
					}
					service = new QuestionService();
					for (int i = 1; i < result.size(); i++) {
						if ((result.get(i).get(0) != null && result.get(i).get(1) != null
								&& result.get(i).get(5) != null && result.get(i).get(6) != null
								&& result.get(i).get(7) != null)
								&& (!((String) result.get(i).get(0)).trim().equals("")
										&& !((String) result.get(i).get(1)).trim().equals("")
										&& !((String) result.get(i).get(5)).trim().equals("")
										&& !((String) result.get(i).get(6)).trim().equals("")
										&& !((String) result.get(i).get(7)).trim().equals(""))) {
							que.setQcontent((String) result.get(i).get(0));
							que.setQtype((String) result.get(i).get(1));
							que.setQchoice(result.get(i).get(2).toString().trim());
							que.setQanswer(result.get(i).get(3).toString());
							str2 = result.get(i).get(4).toString();
							if (str2.trim().equals("")) {
								temp2 = 3;
							} else {
								temp2 = (int) Float.parseFloat(str2);
							}
							que.setQdegree(temp2);
							que.setCname((String) result.get(i).get(5));
							que.setQchapter((String) result.get(i).get(6));
							if(result.get(i).size()==8) {
								que.setQparagraph((String) result.get(i).get(7));
							}
							service.addQuestion(que);
						}
					}
					return "bulk_add_question";
				} catch (Exception e) {
					return "bulk_add_question";
				}
			}
		}
		return "error";
	}
}
