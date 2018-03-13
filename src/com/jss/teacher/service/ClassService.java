package com.jss.teacher.service;

import java.util.List;
import com.jss.teacher.dao.ClassDao;
import com.jss.teacher.pojo.ClassPojo;
import com.jss.teacher.pojo.Condition_ClassPojo;

public class ClassService {
	/**
	 *查询班级总数 
	 */
	public int queryAllCount(String tno){
		ClassDao dao=new ClassDao();
		int num=dao.queryAllCount(tno);
		return num;
	}
	/**
	 *按条件查询班级总数 
	 */
	public int queryCount(Condition_ClassPojo cc,String tno){
		ClassDao dao=new ClassDao();
		int num=0;
		if("class".equals(cc.getCno()) && "major".equals(cc.getMajor()) && "".equals(cc.getTime_start()) && "".equals(cc.getKeyword().trim())){
			num=dao.queryAllCount(tno);
		}else if(!"".equals(cc.getKeyword().trim())){
			num=dao.queryCount(cc.getKeyword(),tno);
		}else{
			num=dao.queryCount(cc,tno);
		}
		return num;
	}
	/**
	 * 查询所有的班级信息
	 */
	public List<ClassPojo> queryAllClass(int pageNow,int pageSize,String tno){
		ClassDao dao=new ClassDao();
		List<ClassPojo> list=dao.queryAllClass(tno,pageNow,pageSize);
		return list;
	}
	/**
	 * 根据条件查询所有的班级信息
	 */
	public List<ClassPojo> queryClass(Condition_ClassPojo cc,int pageNow,int pageSize,String tno){
		ClassDao dao=new ClassDao();
		List<ClassPojo> list = null;
		if("class".equals(cc.getCno()) && "major".equals(cc.getMajor()) && "".equals(cc.getTime_start()) && "".equals(cc.getKeyword().trim())){
			list=dao.queryAllClass(tno,pageNow,pageSize);
		}else if(!"".equals(cc.getKeyword().trim())){
			list=dao.queryClass(tno,cc.getKeyword(),pageNow,pageSize);
		}else{
			list=dao.queryClass(tno,cc,pageNow,pageSize);
		}
		return list;
	}
}
