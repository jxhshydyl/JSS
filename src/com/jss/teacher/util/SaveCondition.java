package com.jss.teacher.util;

import com.jss.teacher.pojo.Condition_ClassPojo;
import com.jss.teacher.pojo.Condition_QuestionPojo;

public class SaveCondition {
	public static Condition_ClassPojo cc;//查询班级的条件
	public static Condition_QuestionPojo cq;//查询问题的条件
	public static String major_no;//专业编号
	public static int cnum;//班级总人数
	public static String Sclass;//班级名称
	public static String[] option={"A","B","C","D","E","F"};//选择题对应的A,B,C,D
	public static String[] Qtype={"单选题","多选题","判断题","填空题","简答题","代码题"};//对应的是题目的类型单选题，多选题，填空题
    public static String message;//查询信息时的条件
}
