package com.jss.teacher.action;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;

public class DownloadAction {
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	private InputStream inputStream; //（get,set方法省略）定义一个输入流，用于接住在Service类生成的含有EXCEL的输入流
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * 下载问题模板
	 * @return
	 * @throws Exception
	 */
	public String downloadTemplate() throws Exception{
		filename=new String("批量导入问题模板.rar".getBytes("utf-8"),"ISO8859-1"); 
		InputStream in=ServletActionContext.getServletContext().getResourceAsStream("/excel/批量导入问题模板.rar");
		setInputStream(in);  
		return "downloadtemplate";
	}
}
