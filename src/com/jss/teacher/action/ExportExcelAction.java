package com.jss.teacher.action;

import java.io.InputStream;
import java.net.URLDecoder;
import com.jss.teacher.service.ExportExcelService;
import com.jss.teacher.util.SaveCondition;

public class ExportExcelAction {
	private String filename;//导出的excel文件名
	private ExportExcelService ees;
	private InputStream inputStream; //（get,set方法省略）定义一个输入流，用于接住在Service类生成的含有EXCEL的输入流
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * 导出excel学生表
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception{
		//解决ie和google文件名的中文乱码
		filename=new String(URLDecoder.decode("学生信息表.xls","UTF-8").getBytes(), "ISO8859-1");
		ees=new ExportExcelService();
		String Cname=SaveCondition.Sclass;
		inputStream=ees.exportExcel(Cname);//得到Excel输入流
		setInputStream(inputStream);//把得到Excel输入流赋给inputStream，从而能使配置得到
        return "exportExcel";
        }
}
