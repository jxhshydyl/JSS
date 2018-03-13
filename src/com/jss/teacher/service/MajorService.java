package com.jss.teacher.service;

import com.jss.teacher.dao.MajorDao;
import com.jss.teacher.pojo.Condition_ClassPojo;

public class MajorService {
	public static void queryMajor(Condition_ClassPojo cc){
		MajorDao dao=new MajorDao();
		dao.queryMajor(cc);
	}
}
