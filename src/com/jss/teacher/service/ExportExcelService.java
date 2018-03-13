package com.jss.teacher.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import com.jss.teacher.dao.ExportExcelDao;
import com.jss.teacher.util.ExcelUtil;

public class ExportExcelService {
	public InputStream exportExcel(String Cname){
		File file=new File("学生信息表.xls");
		ExportExcelDao dao=new ExportExcelDao();
		ArrayList<ArrayList<Object>> list =dao.queryStudentsExportExcel(Cname);
		ArrayList<ArrayList<Object>> result=new ArrayList<ArrayList<Object>>();
		int hang=list.size();
		int lei=list.get(0).size();
		for(int i=0;i<hang+1;i++){
			ArrayList<Object> list1=new ArrayList<Object>();
			if(i==0){
				list1.add("学号");
				list1.add("姓名");
				list1.add("性别");
				list1.add("入学日期");
				list1.add("院系");
				list1.add("专业");
				list1.add("班级");
			}else{
				for(int j=0;j<lei;j++){
					list1.add(list.get(i-1).get(j));
				}
			}
			result.add(list1);
		}
		InputStream inputStream = null;
		try {
			new FileOutputStream(file);
			inputStream=ExcelUtil.writeExcelWeb(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return inputStream;
	}
}
