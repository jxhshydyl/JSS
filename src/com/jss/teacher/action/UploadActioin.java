package com.jss.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.jss.teacher.pojo.QuestionPojo;
import com.jss.teacher.service.QuestionService;
import com.jss.teacher.util.ExcelUtil;

public class UploadActioin {
    private File file;  
    private String fileFileName;  
    private String msg;  
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
    public String bulkAddQuestion() throws Exception{
    	if (getFile() == null){
    		return "error";
    	}else{
    		InputStream is=new FileInputStream(getFile());
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
    			ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(is,fileFileName.split("\\.")[fileFileName.split("\\.").length-1]);
    			for (int j = 0; j < 8; j++) {
    				if (result.get(0).get(j) == null
    						|| !(result.get(0).get(j).toString()
    								.equals(list.get(j))))
    					return "error";
    			}
    			service=new QuestionService();
    			for (int i = 1; i < result.size(); i++) {
    				if((result.get(i).get(0)!=null && result.get(i).get(1)!=null&& result.get(i).get(5)!=null&& result.get(i).get(6)!=null&& result.get(i).get(7)!=null)
    						&& (!((String)result.get(i).get(0)).trim().equals("") &&!((String)result.get(i).get(1)).trim().equals("")&& !((String)result.get(i).get(5)).trim().equals("")&& !((String)result.get(i).get(6)).trim().equals("")&&!((String)result.get(i).get(7)).trim().equals("")))
    				{
    					que.setQcontent((String) result.get(i).get(0));
        				que.setQtype((String) result.get(i).get(1));
        				que.setQchoice(result.get(i).get(2).toString().trim());
        				que.setQanswer(result.get(i).get(3).toString());
        				str2 = result.get(i).get(4).toString();
        				if(str2.trim().equals("")){
        					temp2=3;
        				}else{
        					temp2 = (int) Float.parseFloat(str2);
        				}
        				que.setQdegree(temp2);
        				que.setCname((String) result.get(i).get(5));
        				que.setQchapter((String) result.get(i).get(6));
        				que.setQparagraph((String) result.get(i).get(7));
        				service.addQuestion(que);
    				}
    			}
    			return "bulk_add_question";
    		} catch (Exception e) {
    			return "bulk_add_question";
    		}
    	}
    }
}
