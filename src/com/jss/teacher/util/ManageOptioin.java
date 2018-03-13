package com.jss.teacher.util;

import com.jss.teacher.dao.CourseDao;
import com.jss.teacher.pojo.AddQuestionPojo;
import com.jss.teacher.pojo.QuestionPojo;

public class ManageOptioin {
	public static QuestionPojo manageOption(String Qanswer,AddQuestionPojo aqp,QuestionPojo qp,String daan1){
		String arr[]=SaveCondition.option;
		String choice[]=SaveCondition.Qtype;
		//选项之间用\n区分
		if("1".equals(qp.getQtype()) || "2".equals(qp.getQtype())){
			String answer="";
			int choise=0;
			String str[]={aqp.getAnswer1(),aqp.getAnswer2(),aqp.getAnswer3(),aqp.getAnswer4()};
			for(int i=0;i<str.length;i++){
				if(str[i]!=null && !str[i].equals("")){
					answer=answer+arr[choise]+"："+str[i]+" ";
					choise++;
				}
			}
			String arr1[]=null;
			if(aqp.getAnswer5()!=null){
				arr1=aqp.getAnswer5().split(" ");
				if(arr1.length>=1){
					for(int i=0;i<arr1.length;i++){
						if(arr1[i]!=null && !arr1[i].equals("")){
							answer=answer+arr[choise]+"："+arr1[i]+" ";
							choise++;
						}
					}
				}
			}else{
				qp.setQanswer("");
			}
			qp.setQanswer(Qanswer);
			qp.setQchoice(answer);
		}  else if("3".equals(qp.getQtype())){
			qp.setQanswer(daan1);
			qp.setQchoice("");
		} else if ("6".equals(qp.getQtype())) {
			qp.setQanswer("");
			qp.setQchoice("");
		}else{
			qp.setQanswer("");
			qp.setQchoice("");
		}
		CourseDao dao1=new CourseDao();//通过课程编号得到课程名
		qp.setCname(dao1.queryCourse(qp.getCname()));
		qp.setQtype(choice[Integer.parseInt(qp.getQtype())-1]);
		return qp;
	}
}
